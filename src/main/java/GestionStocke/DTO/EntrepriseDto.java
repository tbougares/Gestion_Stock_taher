package GestionStocke.DTO;

import java.util.List;

import GestionStocke.entity.Entreprise;
import jakarta.persistence.Embedded;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EntrepriseDto {
	private Integer id;
	  private String nom;

	  private String description;

	  @Embedded
	  private AdressDto adresse;

	  private String codeFiscal;

	  private String photo;

	  private String email;

	  private String numTel;

	  private String steWeb;
	  
	private List<UtilisateurDto>utilisateurs;

	
	public static EntrepriseDto fromEntity(Entreprise entreprise) {
		return EntrepriseDto.builder()
				.id(entreprise.getId())
				.nom(entreprise.getNom())
				.description(entreprise.getDescription())
				.photo(entreprise.getPhoto())
				.codeFiscal(entreprise.getCodeFiscal())
				.email(entreprise.getEmail())
				.steWeb(entreprise.getSteWeb())
				.numTel(entreprise.getNumTel())
				.adresse(AdressDto.fromEntity(entreprise.getAdresse()))
				.build();
				
	
	}
	public static Entreprise toEntity (EntrepriseDto entrepriseDto) {
		Entreprise entreprise =new Entreprise();
		entreprise.setId(entrepriseDto.getId());
		entreprise.setNom(entrepriseDto.getNom());
		entreprise.setDescription(entrepriseDto.getDescription());
		entreprise.setPhoto(entrepriseDto.getPhoto());
		entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
		entreprise.setEmail(entrepriseDto.getEmail());
		entreprise.setNumTel(entrepriseDto.getNumTel());
		entreprise.setSteWeb(entrepriseDto.getSteWeb());
		entreprise.setAdresse(AdressDto.toEntity(entrepriseDto.getAdresse()));

		
		return entreprise;
	}

}
