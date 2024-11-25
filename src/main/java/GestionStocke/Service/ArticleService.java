package GestionStocke.Service;

import java.util.List;
import java.util.Optional;

import GestionStocke.DTO.ArticleDto;
import GestionStocke.DTO.LigneCommandeFournisseurDto;
import GestionStocke.DTO.LigneCommaneClientDto;
import GestionStocke.DTO.LigneVenteDto;
import GestionStocke.entity.Article;

public interface ArticleService  {
   	 ArticleDto save(ArticleDto dto);

	  ArticleDto findById(Integer id);

	  ArticleDto findByCodeArticle(String codeArticle);

	  List<ArticleDto> findAll();

	  List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);

	  List<LigneCommaneClientDto> findHistoriaueCommandeClient(Integer idArticle);

	  List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);

	  List<ArticleDto> findAllArticleByIdCategory(Integer idCategory);

	  void delete(Integer id);

	List<ArticleDto> getArticleByCodeBarre(String codeBarre);
}
