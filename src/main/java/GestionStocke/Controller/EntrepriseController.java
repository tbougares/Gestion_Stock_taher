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

import GestionStocke.DTO.EntrepriseDto;
import GestionStocke.Service.EntrepriseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/entreprises")
@RequiredArgsConstructor
public class EntrepriseController {
	
	
private final EntrepriseService entrepriseService;

	
	@GetMapping("/afficher")
	public String article() {
		
		
		return "test bonjour";
	}
	@PostMapping("/create")
	 public ResponseEntity<?> save(
	            @RequestBody @Valid  EntrepriseDto  dto
	    ) {
	        return ResponseEntity
	                .accepted().body(entrepriseService.save(dto));
	    }
	@GetMapping("/{idEntreprise}")
	public ResponseEntity<EntrepriseDto> findById(
            @PathVariable("idEntreprise") Integer id
    ) {
        return ResponseEntity.ok(entrepriseService.findById(id));
    }
	
	
	@GetMapping("/all")
	public ResponseEntity<List<EntrepriseDto>> findAll(
    ) {
        return ResponseEntity.ok(entrepriseService.findAll());
    }
	
	@DeleteMapping("/delete/{idEntreprise}")
	public void delete(@PathVariable("idEntreprise") Integer id) {
		
		if (id==null) {
			return ;
		}
		entrepriseService.deleteById(id);
	}
}
