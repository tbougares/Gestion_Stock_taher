package GestionStocke.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
@Builder
public class Adress {
	private String Adreesse1;
	private String Adreesse2;
	
	private String Ville;
	
	private String codePostal;
	
	private String pays;
	
	
	
	
	

}
