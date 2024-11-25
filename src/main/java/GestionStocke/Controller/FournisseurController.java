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

import GestionStocke.DTO.FornisseurDto;
import GestionStocke.Service.FournisseurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fournisseurs")
public class FournisseurController {

    private final FournisseurService fournisseurService;

	
	@GetMapping("/afficher")
	public String article() {
		
		
		return "test bonjour";
	}
	@PostMapping("/create")
	 public ResponseEntity<?> save(
	            @RequestBody @Valid  FornisseurDto  dto
	    ) {
	        return ResponseEntity
	                .accepted().body(fournisseurService.save(dto));
	    }
	@GetMapping("/{idFournisseur}")
	public ResponseEntity<FornisseurDto> findById(
            @PathVariable("idFournisseur") Integer id
    ) {
        return ResponseEntity.ok(fournisseurService.findById(id));
    }
	
	
	@GetMapping("/all")
	public ResponseEntity<List<FornisseurDto>> findAll(
    ) {
        return ResponseEntity.ok(fournisseurService.findAll());
    }
	
	@DeleteMapping("/delete/{idFournisseur}")
	public void delete(@PathVariable("idFournisseur") Integer id) {
		
		if (id==null) {
			return ;
		}
		fournisseurService.delete(id);
	}
}

