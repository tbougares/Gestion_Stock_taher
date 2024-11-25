package GestionStocke.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GestionStocke.DTO.CategoryDto;
import GestionStocke.Service.CategryService;
import jakarta.validation.Valid;   



@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategryService categoryService;

	
	public CategoryController(CategryService categoryService) {
	super();
	this.categoryService = categoryService;
}
	@GetMapping("/afficher")
	public String article() {
		
		
		return "test bonjour";
	}
	@PostMapping("/create")
	 public ResponseEntity<?> save(
	            @RequestBody @Valid  CategoryDto dto
	    ) {
	        return ResponseEntity
	                .accepted().body(categoryService.saveCategory(dto));
	    }
	@GetMapping("/{idCategory}")
	public ResponseEntity<CategoryDto> findById(
            @PathVariable("idCategory") Integer id
    ) {
        return ResponseEntity.ok(categoryService.findById(id));
    }
	@GetMapping("/filter/{codeCategory}")
	public ResponseEntity<CategoryDto> findByCodeArticle(
            @PathVariable("codeCategory") String codeCategory
    ) {
        return ResponseEntity.ok(categoryService.findByCode(codeCategory));
    }
	
	@GetMapping("/all")
	public ResponseEntity<List<CategoryDto>> findAll(
    ) {
        return ResponseEntity.ok(categoryService.findAllCategory());
    }
	
	@DeleteMapping("/delete/{idCategory}")
	public void delete(@PathVariable("idCategory") Integer id) {
		
		if (id==null) {
			return ;
		}
		categoryService.deleteCategoryById(id);
	}
}
