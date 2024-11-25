package GestionStocke.repostory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.Article;
import GestionStocke.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	//Optional<Category> findByDesignation(String designation);
	Optional<List<Article>> findAllByDesignation(String designation);//recherche de article qi apartan au category?
	Optional<Category> findCategoryByCode(String code);
	
	
}
