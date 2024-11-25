package GestionStocke.DTO;

import java.math.BigDecimal;

import GestionStocke.entity.Article;
import GestionStocke.entity.Category;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ArticleDto {
	private Integer id;
	private String codeArticle;
	
	private String designation;
	
	private BigDecimal prixUnitaire;
	
	private BigDecimal tauxTva;
	
	private BigDecimal prixUniteaireTtc;
	
	private String photo;
	
	private CategoryDto category;
	
	private  String quantite;

	private Integer seuilQuantite ;

	private String codeBarre;


	public static ArticleDto fromEntity(Article article) {
		return ArticleDto.builder()
				.id(article.getId())
				.codeArticle(article.getCodeArticle())
				.designation(article.getDesignation())
				.photo(article.getPhoto())
				.prixUnitaire(article.getPrixUnitaire())
				.prixUniteaireTtc(article.getPrixUniteaireTtc())
				.tauxTva(article.getTauxTva())
				.seuilQuantite(article.getSeuilQuantite())
				.codeBarre(article.getCodeBarre())
				.quantite(article.getQuantite())
				.category(CategoryDto.fromEntity(article.getCategory()))
				.build();
				
	
	}
	public static Article toEntity (ArticleDto articleDto) {
		Article article =new Article();
		article.setId(articleDto.getId());		
		article.setCodeArticle(articleDto.getCodeArticle());
		article.setDesignation(articleDto.getDesignation());
		article.setPhoto(articleDto.getPhoto());
		article.setPrixUnitaire(articleDto.getPrixUnitaire());
		article.setPrixUniteaireTtc(articleDto.getPrixUniteaireTtc());
		article.setTauxTva(articleDto.getTauxTva());
        article.setSeuilQuantite(articleDto.getSeuilQuantite());
		article.setCodeBarre(articleDto.getCodeBarre());
		article.setCategory(CategoryDto.toEntity(articleDto.getCategory()));
		article.setQuantite(articleDto.getQuantite());

		return article;
	}

}
