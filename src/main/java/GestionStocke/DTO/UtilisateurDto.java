package GestionStocke.DTO;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import GestionStocke.entity.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UtilisateurDto {

	
	
	private Integer id;

	  private String nom;

	  private String prenom;

	  private String email;

	  private Instant dateDeNaissance;

	  private String moteDePasse;
	  
	  private String Tel;

	  private AdressDto adresse;

	  private String photo;

	  private EntrepriseDto entreprise;

	  private List<RolesDto> roles;

	  public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
	    if (utilisateur == null) {
	      return null;
	    }

	    return UtilisateurDto.builder()
	        .id(utilisateur.getId())
	        .nom(utilisateur.getNom())
	        .prenom(utilisateur.getPrenom())
	        .email(utilisateur.getEmail())
	        .moteDePasse(utilisateur.getMoteDePasse())
	        .dateDeNaissance(utilisateur.getDateDeNaissance())
	        .adresse(AdressDto.fromEntity(utilisateur.getAdresse()))
	        .photo(utilisateur.getPhoto())
	        .Tel(utilisateur.getTel())

	        .build();
	  }

	  public static Utilisateur toEntity(UtilisateurDto dto) {
	    if (dto == null) {
	      return null;
	    }

	    Utilisateur utilisateur = new Utilisateur();
	    utilisateur.setId(dto.getId());
	    utilisateur.setNom(dto.getNom());
	    utilisateur.setPrenom(dto.getPrenom());
	    utilisateur.setEmail(dto.getEmail());
	    utilisateur.setMoteDePasse(dto.getMoteDePasse());
	    utilisateur.setTel(dto.getTel());
	    utilisateur.setDateDeNaissance(dto.getDateDeNaissance());
	    utilisateur.setAdresse(AdressDto.toEntity(dto.getAdresse()));
	    utilisateur.setPhoto(dto.getPhoto());

	    return utilisateur;
	  }
	}

