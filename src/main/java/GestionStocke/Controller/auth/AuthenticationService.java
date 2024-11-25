package GestionStocke.Controller.auth;


import java.io.IOException;
import java.security.Principal;

import GestionStocke.entity.Utilisateur;
import GestionStocke.repostory.UtilisateurRepository;
import GestionStocke.token.Token;
import GestionStocke.token.TokenRepository;
import GestionStocke.token.TokenType;
import GestionStocke.utiles.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UtilisateurRepository repository ;
	private final PasswordEncoder passwordEncoder ;
	private final JwtService jwtService ;
	private final AuthenticationManager authenticationManager ;
	private final TokenRepository tokenRepository ;

	
	public AuthenticationResponse authenticate(AuthenticationRequest request ) {
		 authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
						)
				);
		 var user = repository.findByEmail(request.getEmail())
				 .orElseThrow(); 		
		 var jwtToken = jwtService.generateToken(user);
         var refreshToken = jwtService.generateRefreshToken(user);
		 revokedAllUserTokens(user);
		 saveUserToken(user, jwtToken);
		 return AuthenticationResponse.builder()
					.accessToken(jwtToken)
					.refreshToken(refreshToken)	
					.id(user.getId())
		            .firstname(user.getNom())
		            .lastname(user.getPrenom())
		            .email(user.getEmail())
		            .role(user.getRole())
		            .sexe(user.getSexe())
					.build();
							
	}

	
	public static String jwtGenerated;
	
	public AuthenticationResponse register(RegisterRequest request  ,  final HttpServletRequest requestss) {
		var user = Utilisateur.builder()
				.nom(request.getFirstname())
				.prenom(request.getLastname())
				.email(request.getEmail())
				.moteDePasse(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole())
				.sexe(request.getSexe())
				.build();
		var savedUSer= repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		jwtGenerated =jwtToken ;
		var refreshToken = jwtService.generateRefreshToken(user);
		saveUserToken(savedUSer, jwtToken);
		return AuthenticationResponse.builder()
				.accessToken(jwtToken)
				.refreshToken(refreshToken)
				.id(savedUSer.getId())
	            .firstname(savedUSer.getNom())
	            .lastname(savedUSer.getPrenom())
	            .email(savedUSer.getEmail())
	            .role(savedUSer.getRole())
	            .sexe(savedUSer.getSexe())
				.build();
	}
	
	private void revokedAllUserTokens(Utilisateur user) {
		var validUserTokens =tokenRepository.findAllValidTokensByUser(user.getId());
		if(validUserTokens.isEmpty()) {
			return ;
		}
		
		validUserTokens.forEach(t -> {
			t.setExpired(true);
			t.setRevoked(true);
			
		});
		tokenRepository.saveAll(validUserTokens);
	}

	private void saveUserToken(Utilisateur user, String jwtToken) {
		var token = Token.builder()
				.user(user)
				.token(jwtToken)
				.tokenType(TokenType.BEARER)
				.expired(false)
				.revoked(false)
				.build();
		tokenRepository.save(token);
	}

	public void refreshToken(
			HttpServletRequest request, 
			HttpServletResponse response
	 ) throws IOException{
		
		final String authHeader =request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userEmail; 
		if(authHeader == null || !authHeader.startsWith("Bearer ") ) {
			return;
		}
		refreshToken=authHeader.substring(7);
		userEmail=jwtService.extractUsername(refreshToken);
		
		 if(userEmail != null ) {
			var user =this.repository.findByEmail(userEmail)
					.orElseThrow();
			if (jwtService.isTokenValid(refreshToken, user)) {
				var accessToken = jwtService.generateToken(user);
				 revokedAllUserTokens(user);
				 saveUserToken(user, accessToken);
				var authResponse = AuthenticationResponse.builder()
						 .accessToken(accessToken)
						 .refreshToken(refreshToken)
						 .build();
			new ObjectMapper().writeValue(response.getOutputStream(), authResponse);			
				
			}
		}
	}

	public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
		var user = (Utilisateur) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
		// check if the current password is correct
		if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
			throw new IllegalStateException("Wrong password");
		}
		// check if the two new passwords are the same
		if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
			throw new IllegalStateException("Les mots de passe ne sont pas les mêmes");
		}
		// update the password
		user.setMoteDePasse(passwordEncoder.encode(request.getNewPassword()));

		// save the new password
		repository.save(user);
	}
}
