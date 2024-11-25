package GestionStocke.DTO;

import java.math.BigDecimal;
import java.time.Instant;

import GestionStocke.entity.MvtStk;
import GestionStocke.entity.SourceMvtStk;
import GestionStocke.entity.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MvtStkDto {
	private Integer id;
private Instant dateMvt;
	
	private ArticleDto article;
	
	private Integer quantite;
	  private SourceMvtStk sourceMvt;

	private TypeMvtStk typeMvtStock;

	private Integer idEntreprise;

	  public static MvtStkDto fromEntity(MvtStk mvtStk) {
	    if (mvtStk == null) {
	      return null;
	    }

	    return MvtStkDto.builder()
	        .id(mvtStk.getId())
	        .dateMvt(mvtStk.getDateMvt())
	        .quantite(mvtStk.getQuantite())
	        .article(ArticleDto.fromEntity(mvtStk.getArticle()))
	        .typeMvtStock(mvtStk.getTypeMvtStock())
	        .sourceMvt(mvtStk.getSourceMvt())
	        .idEntreprise(mvtStk.getIdEntreprise())
	        .build();
	  }

	  public static MvtStk toEntity(MvtStkDto dto) {
	    if (dto == null) {
	      return null;
	    }

	    MvtStk mvtStk = new MvtStk();
	    mvtStk.setId(dto.getId());
	    mvtStk.setDateMvt(dto.getDateMvt());
	    mvtStk.setQuantite(dto.getQuantite());
	    mvtStk.setArticle(ArticleDto.toEntity(dto.getArticle()));
	    mvtStk.setTypeMvtStock(dto.getTypeMvtStock());
	    mvtStk.setSourceMvt(dto.getSourceMvt());
	    mvtStk.setIdEntreprise(dto.getIdEntreprise());
	    return mvtStk;
	  }
}