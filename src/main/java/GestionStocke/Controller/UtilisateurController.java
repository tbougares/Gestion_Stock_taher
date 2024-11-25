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

import GestionStocke.DTO.UtilisateurDto;
import GestionStocke.Service.UtilisateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

	
	@GetMapping("/afficher")
	public String article() {
		
		
		return "test bonjour";
	}
	@PostMapping("/create")
	 public ResponseEntity<?> save(
	            @RequestBody @Valid  UtilisateurDto  dto
	    ) {
	        return ResponseEntity
	                .accepted().body(utilisateurService.saveUtilisateur(dto));
	    }
	@GetMapping("/{idUtilisateur}")
	public ResponseEntity<UtilisateurDto> findById(
            @PathVariable("idUtilisateur") Integer id
    ) {
        return ResponseEntity.ok(utilisateurService.findUtilisateurById(id));
    }
	 @GetMapping("/find/{email}")
	 public ResponseEntity<UtilisateurDto> findByEmail(@PathVariable("email") String email) {
		 
	        return ResponseEntity.ok(utilisateurService.findUtilisateurByEmail(email));

	 }
	
	
	@GetMapping("/all")
	public ResponseEntity<List<UtilisateurDto>> findAll(
    ) {
        return ResponseEntity.ok(utilisateurService.findAll());
    }
	
	@DeleteMapping("/delete/{idUtilisateur}")
	public void delete(@PathVariable("idUtilisateur") Integer id) {
		
		if (id==null) {
			return ;
		}
		utilisateurService.deleteUtilisateurById(id);
	}
}
