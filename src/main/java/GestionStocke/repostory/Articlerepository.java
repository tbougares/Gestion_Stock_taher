package GestionStocke.repostory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.Article;
@Repository
public interface Articlerepository extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {
	  Optional<Article> findArticleByCodeArticle(String codeArticle);

	List<Article> findByCodeBarre(String codeBarre);

	 
	Optional<Article> findByCodeArticleAndDesignationIgnoreCase(String code,String designation);
	Optional<Article> findByCodeArticle(String codeArticle);
	List<Article> findAllByCategoryId(Integer idCategory);
//nzid desi liste
	Optional<Article> findByDesignationIgnoreCase(String designation);
	//liste de vente de chaque article ten5adam f vente

	
}
