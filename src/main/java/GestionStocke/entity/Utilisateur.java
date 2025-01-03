package GestionStocke.entity;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

import GestionStocke.token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Utilisateur extends AuditModel implements UserDetails {
	private String nom,
			prenom,
			photo,
			Tel ;

	private String sexe ;
	@Email
	@NotBlank
	private String email;
	private Instant dateDeNaissance;
	
	@Embedded
	private Adress adresse;
	
	private String moteDePasse;


	@Enumerated(EnumType.STRING)
	private Role role ;


	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties("user")
	private List<Token> tokens ;

	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getAuthorities();
	}

	@Override
	public String getPassword() {
		return moteDePasse;
	}


}
