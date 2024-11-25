package GestionStocke.DTO;

import java.math.BigDecimal;

import GestionStocke.entity.LigneVente;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneVenteDto {
    
	private Integer id;
	private VentesDto vente;
	
	private BigDecimal quantite;
	
	private BigDecimal prixUniteur;
	
	private ArticleDto article;

	private Integer idEntreprise;

	  public static LigneVenteDto fromEntity(LigneVente ligneVente) {
	    if (ligneVente == null) {
	      return null;
	    }

	    return LigneVenteDto.builder()
	        .id(ligneVente.getId())
	        .vente(VentesDto.fromEntity(ligneVente.getVente()))
	        .article(ArticleDto.fromEntity(ligneVente.getArticle()))
	        .quantite(ligneVente.getQuantite())
	        .prixUniteur(ligneVente.getPrixUniteur())
	        .idEntreprise(ligneVente.getIdEntreprise())
	        .build();
	  }

	  public static LigneVente toEntity(LigneVenteDto dto) {
	    if (dto == null) {
	      return null;
	    }
	    LigneVente ligneVente = new LigneVente();
	    ligneVente.setId(dto.getId());
	    ligneVente.setVente(VentesDto.toEntity(dto.getVente()));
	    ligneVente.setArticle(ArticleDto.toEntity(dto.getArticle()));
	    ligneVente.setQuantite(dto.getQuantite());
	    ligneVente.setPrixUniteur(dto.getPrixUniteur());
	    ligneVente.setIdEntreprise(dto.getIdEntreprise());
	    return ligneVente;
	  }

	}