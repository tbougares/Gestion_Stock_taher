package GestionStocke.DTO;

import java.util.List;

import GestionStocke.entity.Fornisseur;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FornisseurDto {
	private Integer id;
	private String nom,prenom,photo,Tel;
	
	private String email;
	
	private AdressDto adresse;
	
	private Integer idEntreprise;
	
	private List<LigneCommandeFournisseurDto> commandeFornisseurs;

	public static FornisseurDto fromEntity(Fornisseur fournisseur) {
		return FornisseurDto.builder()
				.id(fournisseur.getId())
				.nom(fournisseur.getNom())
				.prenom(fournisseur.getPrenom())
				.photo(fournisseur.getPhoto())
				.email(fournisseur.getEmail())
				.Tel(fournisseur.getTel())
				.idEntreprise(fournisseur.getIdEntreprise())
				.adresse(AdressDto.fromEntity(fournisseur.getAdresse()))
				.build();
				
	
	}
	public static Fornisseur toEntity (FornisseurDto fournisseyrDto) {
		Fornisseur fournisseur =new Fornisseur();
		fournisseur.setId(fournisseyrDto.getId());
		fournisseur.setNom(fournisseyrDto.getNom());
		fournisseur.setPrenom(fournisseyrDto.getPrenom());
		fournisseur.setPhoto(fournisseyrDto.getPhoto());
		fournisseur.setEmail(fournisseyrDto.getEmail());
		fournisseur.setTel(fournisseyrDto.getTel());
	    fournisseur.setIdEntreprise(fournisseyrDto.getIdEntreprise());

		fournisseur.setAdresse(AdressDto.toEntity(fournisseyrDto.getAdresse()));

		
		return fournisseur;
	}

}
