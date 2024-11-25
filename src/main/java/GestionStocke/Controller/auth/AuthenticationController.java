package GestionStocke.Controller.auth;

import java.security.Principal;
import java.util.Optional;

import GestionStocke.entity.Utilisateur;
import GestionStocke.repostory.UtilisateurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	
	private final AuthenticationService service;
	private final UtilisateurRepository userRepository ;

	
	 @PostMapping("/register")
	  public ResponseEntity<Object> register(@RequestBody RegisterRequest registrationRequest ,  final HttpServletRequest request) {

		 if (userRepository.existsByEmail(registrationRequest.getEmail())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Erreur : l'email d'utilisateur est déjà utilisé !!!"));
			}

		 var response = service.register(registrationRequest , request);
		
		 return ResponseEntity.ok(response)  ; 
	  }
	         
	 
	  @PostMapping("/authenticate")
	  public ResponseEntity<Object> authenticate( @RequestBody AuthenticationRequest request ) {
		  Optional<Utilisateur> optionalUser  = userRepository.findByEmail(request.getEmail());
		  if (optionalUser.isPresent()) {
		        return ResponseEntity.ok(service.authenticate(request));
		    } else {  
		        return ResponseEntity
		                .badRequest()
		                .body(new MessageResponse("Utilisateur non trouvé !"));
		    }
	  }


	@PatchMapping("/change-password")
	public  ResponseEntity<?> changePassword( @RequestBody ChangePasswordRequest request ,  Principal connectedUser){
		service.changePassword(request ,connectedUser );
		return ResponseEntity.ok().build();
	}





}
