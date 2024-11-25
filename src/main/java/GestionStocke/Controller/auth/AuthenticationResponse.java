package GestionStocke.Controller.auth;

import GestionStocke.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor 
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthenticationResponse {
	/*
	 * ces les deux propriétés qui seront renvoyées au front-end  ou a l'utilisateur
	 * */
	
	   private String accessToken;
	   private String refreshToken;
	  // Ajout des attributs spécifiques de User
	    private Integer id;
	    private String firstname;
	    private String lastname;
	    private String email;
	    private Role role;
	    private String sexe ;
		


	
}
