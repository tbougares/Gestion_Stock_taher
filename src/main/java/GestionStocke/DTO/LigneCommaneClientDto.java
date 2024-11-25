package GestionStocke.DTO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import GestionStocke.entity.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneCommaneClientDto {
	 

	 private Integer id;

	  private ArticleDto article;

	  @JsonIgnore
	  private CommandClientDto commandeClient;

	  private Integer quantite;

	  private double prixUnitaire;

	  private Integer idEntreprise;

	  public static LigneCommaneClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
	    if (ligneCommandeClient == null) {
	      return null;
	    }
	    return LigneCommaneClientDto.builder()
	        .id(ligneCommandeClient.getId())
	        .article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
	        .quantite(ligneCommandeClient.getQuantite())
	        .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
	        .idEntreprise(ligneCommandeClient.getIdEntreprise())
	        .build();
	  }

	  public static LigneCommandeClient toEntity(LigneCommaneClientDto dto) {
	    if (dto == null) {
	      return null;
	    }

	    LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
	    ligneCommandeClient.setId(dto.getId());
	    ligneCommandeClient.setArticle(ArticleDto.toEntity(dto.getArticle()));
	    ligneCommandeClient.setPrixUnitaire(dto.getPrixUnitaire());
	    ligneCommandeClient.setQuantite(dto.getQuantite());
	    ligneCommandeClient.setIdEntreprise(dto.getIdEntreprise());
	    return ligneCommandeClient;
	  }

}

