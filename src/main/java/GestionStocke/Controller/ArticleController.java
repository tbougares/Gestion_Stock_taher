package GestionStocke.Controller;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import GestionStocke.DTO.ArticleDto;
import GestionStocke.DTO.CategoryDto;
import GestionStocke.DTO.LigneCommandeFournisseurDto;
import GestionStocke.DTO.LigneCommaneClientDto;
import GestionStocke.DTO.LigneVenteDto;
import GestionStocke.File.FileFilter;
import GestionStocke.Service.ArticleService;
import GestionStocke.Service.FileUpload;
import GestionStocke.Service.FilesService;
import GestionStocke.entity.Article;
import GestionStocke.entity.Category;
import GestionStocke.repostory.Articlerepository;
import GestionStocke.repostory.CategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

@RestController
@RequestMapping("articles")
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {

	private final ArticleService articleService;
	
	private final CategoryRepository categoryRepository ;
	

	FileUpload fileUpload;
	
	//@Autowired
	Articlerepository Articlerepository;

	//1private final FilesService fileService;

	 private FileFilter fileFilter;

    public ArticleController(ArticleService articleService, CategoryRepository categoryRepository,FileUpload FileUpload) {
        this.articleService = articleService;
        this.fileUpload = FileUpload;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/images/{fileName:.+}")
	    public ResponseEntity<?> serveFile(@PathVariable String fileName) {
	        try {
	            Resource file = fileUpload.loadFileAsResource(fileName,"Articles");
	            return ResponseEntity.ok().body(file);
	        } catch (Exception e) {
	        	return new ResponseEntity<String>("Not Found",HttpStatus.NOT_FOUND);
	        }
	    }
	@GetMapping("/afficher")
	public String article() {
		
		
		return "test bonjour";
	}
	
	/*
	 *   @RequestParam("prixUnitaire") double prixUnitaire,@RequestParam("tauxTva") double tauxTva,
			 @RequestParam("codeArticle") String codeArticle,@RequestParam("designation") String designation,
	         @RequestParam("prixUniteaireTtc") double prixUniteaireTtc,  @RequestParam("category") String categoryFormData
	 */
	//@PostMapping("/AddArticle")
	@PostMapping(value={"/AddArticle"} ,  consumes={MediaType.MULTIPART_FORM_DATA_VALUE} ) 
	 public ResponseEntity<?> save(
			 @ModelAttribute  ArticleDto articleDto,
			 @RequestPart("file") MultipartFile file  ,
			 @RequestParam Integer categoryId 
	    ) throws IOException  {		
		
	    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    String fileName =  timestamp+"_"+file.getOriginalFilename();
		String uploadDir = "Articles/";
		fileUpload.saveFile(uploadDir, fileName, file);
		articleDto.setPhoto(fileName);
		 Category category = categoryRepository.findById(categoryId).orElseThrow();
		 articleDto.setCategory(CategoryDto.fromEntity(category)); 
		//articleDto.setCategory(articleDto.getCategory()); 
	      return ResponseEntity
	                .accepted().body(articleService.save(articleDto));      
	    }

	@GetMapping("/get/ByCode/{codeBarre}")
	public List<ArticleDto> getArticleByCodeBarre(@PathVariable String codeBarre) {
		return articleService.getArticleByCodeBarre(codeBarre);
	}
	
	@GetMapping("/id_article/{idArticle}")
	public ResponseEntity<ArticleDto> findById(
            @PathVariable("idArticle") Integer id
    ) {
        return ResponseEntity.ok(articleService.findById(id));
    }
	@GetMapping("/{codeArticle}")
	public ResponseEntity<ArticleDto> findByCodeArticle(
            @PathVariable("codeArticle") String codeArticle
    ) {
        return ResponseEntity.ok(articleService.findByCodeArticle(codeArticle));
    }
	
	@GetMapping("/all")
	public ResponseEntity<List<ArticleDto>> findAll(
    ) {
        return ResponseEntity.ok(articleService.findAll());
    }
	
	
	// Rechercher des articles par plusieurs modèles
    @PostMapping("/searchByModeles")
    public ResponseEntity<List<ArticleDto>> searchByModeles(@RequestBody List<String> modeles) {
        List<ArticleDto> articles = articleService.searchByModeles(modeles);
        return ResponseEntity.ok(articles); // Retourne la liste des DTOs
    }
	
	
	
	@GetMapping("/historique/vente/{idArticle}")
	public ResponseEntity<List<LigneVenteDto>> findHistoriqueVentes(
			@PathVariable("idArticle") Integer idArticle
    ) {
        return ResponseEntity.ok(articleService.findHistoriqueVentes(idArticle));
    }
	@GetMapping("/historique/commandeclient/{idArticle}")
	public ResponseEntity<List<LigneCommaneClientDto>> findHistoriaueCommandeClient(@PathVariable("idArticle") Integer idArticle){
		
		return ResponseEntity.ok(articleService.findHistoriaueCommandeClient(idArticle));      
		
	}
	@GetMapping("/historique/commandefournisseur/{idArticle}")
	public ResponseEntity<List<LigneCommandeFournisseurDto>> findHistoriqueCommandeFournisseur(@PathVariable("idArticle") Integer idArticle){
		
		return ResponseEntity.ok(articleService.findHistoriqueCommandeFournisseur(idArticle));
		
	}
	@GetMapping("/filter/category/{idCategory}")
	public ResponseEntity<List<ArticleDto>> findAllArticleByIdCategory(@PathVariable("idCategory") Integer idCategory){
		
		return ResponseEntity.ok(articleService.findAllArticleByIdCategory(idCategory));
		
	}
	@DeleteMapping("/delete/{idArticle}")
	public void delete(@PathVariable("idArticle") Integer id) {
		
		
		articleService.delete(id);
	}

}
