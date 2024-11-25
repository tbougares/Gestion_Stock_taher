package GestionStocke.DTO;

import java.math.BigDecimal;

import GestionStocke.entity.CommandeFornisseur;
import GestionStocke.entity.LigneCommandeFornisseur;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneCommandeFournisseurDto {
	private Integer id;

	  private ArticleDto article;

	  private CommandeFornisseur commandeFournisseur;

	  private Integer quantite;

	  private BigDecimal prixUnitaire;

	  private Integer idEntreprise;

	public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFornisseur ligneCommande) {
		return LigneCommandeFournisseurDto.builder()
				.id(ligneCommande.getId())
				.article(ArticleDto.fromEntity(ligneCommande.getArticle()))
				.quantite(ligneCommande.getQuantite())
				.prixUnitaire(ligneCommande.getPrixUnitaire())
		        .idEntreprise(ligneCommande.getIdEntreprise())

				.build();
				
	
	}
	public static LigneCommandeFornisseur toEntity (LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
		LigneCommandeFornisseur ligneCommande =new LigneCommandeFornisseur();
		ligneCommande.setId(ligneCommandeFournisseurDto.getId());
		ligneCommande.setArticle(ArticleDto.toEntity(ligneCommandeFournisseurDto.getArticle()));
		ligneCommande.setQuantite(ligneCommandeFournisseurDto.getQuantite());
		ligneCommande.setPrixUnitaire(ligneCommandeFournisseurDto.getPrixUnitaire());
		ligneCommande.setIdEntreprise(ligneCommandeFournisseurDto.getIdEntreprise());


		
		return ligneCommande;
	}

}
