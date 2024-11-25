package GestionStocke.Service.ServiceImplement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import GestionStocke.DTO.CategoryDto;
import GestionStocke.Exception.EntityNotFoundException;
import GestionStocke.Exception.ErrorCodes;
import GestionStocke.Exception.InvalidEntityException;
import GestionStocke.Exception.InvalidOperationException;
import GestionStocke.Service.CategryService;
import GestionStocke.Validator.CategoryValidator;
import GestionStocke.entity.Article;
import GestionStocke.entity.Category;
import GestionStocke.repostory.Articlerepository;
import GestionStocke.repostory.CategoryRepository;

@Service
public class CategoryServiceImplements implements CategryService {

	private CategoryRepository categoryrepository;
	private Articlerepository articleRepository;

	
@Autowired
	public CategoryServiceImplements(CategoryRepository categoryrepository, Articlerepository articleRepository) {
		super();
		this.categoryrepository = categoryrepository;
		this.articleRepository = articleRepository;
	}


	@Override
	public CategoryDto saveCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		List<String> errors = CategoryValidator.categoryValidate(categoryDto);
		if (!errors.isEmpty()) {
		
			throw new InvalidEntityException("La category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
	    }
	    return CategoryDto.fromEntity(
	        categoryrepository.save(CategoryDto.toEntity(categoryDto))
	    );	
	    }


	@Override
	public List<CategoryDto> findAllCategory() {
		// TODO Auto-generated method stub
		return categoryrepository.findAll().stream()
		        .map(CategoryDto::fromEntity)
		        .collect(Collectors.toList());
	}


	


	@Override
	public void deleteCategoryById(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
		      //log.error("Category ID is null");
		      return;
		    }
		    
			List<Article> articles = articleRepository.findAllByCategoryId(id);
		    if (!articles.isEmpty()) {
		      throw new InvalidOperationException("Impossible de supprimer cette categorie qui est deja utilise",
		          ErrorCodes.CATEGORY_ALREADY_IN_USE);
		    }
		    categoryrepository.deleteById(id);
		  }
	


	@Override
	public void deleteAllCategory() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public CategoryDto findByCode(String code) {// TODO Auto-generated method stub
		if (!StringUtils.hasLength(code)) {
		      //log.error("Category CODE is null");
		      return null;
		    }
		    return categoryrepository.findCategoryByCode(code)
		        .map(CategoryDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucune category avec le CODE = " + code + " n' ete trouve dans la BDD",
		            ErrorCodes.CATEGORY_NOT_FOUND)
		        );
	}


	@Override
	public CategoryDto findById(Integer id) {
		// TODO Auto-generated method stub
	
		    return categoryrepository.findById(id)
		        .map(CategoryDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucune category avec l'ID = " + id + " n' ete trouve dans la BDD",
		            ErrorCodes.CATEGORY_NOT_FOUND)
		        );
	}


}
