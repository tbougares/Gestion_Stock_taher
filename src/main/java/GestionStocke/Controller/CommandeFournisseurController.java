package GestionStocke.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GestionStocke.DTO.CommandFornisseurDto;
import GestionStocke.Service.CommandeFournisseurService;
import GestionStocke.entity.EtatCommande;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commandesFournisseurs")
public class CommandeFournisseurController {

    private final CommandeFournisseurService commandeFournisseurService;

	
	@GetMapping("/afficher")
	public String article() {
		
		
		return "test bonjour";
	}
	@PostMapping("/create")
	 public ResponseEntity<?> save(
	            @RequestBody @Valid  CommandFornisseurDto   dto
	    ) {
	        return ResponseEntity
	                .accepted().body(commandeFournisseurService.save(dto));
	    }
	@PatchMapping( "/update/etat/{idCommande}/{etatCommande}")
	  ResponseEntity<CommandFornisseurDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande){
		return ResponseEntity.ok(commandeFournisseurService.updateEtatCommande(idCommande, etatCommande));
	}
	
	@PatchMapping("/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
	  ResponseEntity<CommandFornisseurDto> updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,
	      @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite ){
		return ResponseEntity.ok(commandeFournisseurService.updateQuantiteCommande(idCommande, idLigneCommande,quantite));

	  }

	  
	  @PatchMapping("/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
	  ResponseEntity<CommandFornisseurDto> updateArticle(@PathVariable("idCommande") Integer idCommande,
	      @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle){
			return ResponseEntity.ok(commandeFournisseurService.updateArticle(idCommande, idLigneCommande, idArticle));
		}

	@GetMapping("/{idCommandeFournisseur}")
	public ResponseEntity<CommandFornisseurDto> findById(
            @PathVariable("idCommandeFournisseur") Integer id
    ) {
        return ResponseEntity.ok(commandeFournisseurService.findById(id));
    }
	
	
	@GetMapping("/all")
	public ResponseEntity<List<CommandFornisseurDto>> findAll(
    ) {
        return ResponseEntity.ok(commandeFournisseurService.findAll());
    }
	
	@DeleteMapping("/delete/{idCommandeFournisseur}")
	public void delete(@PathVariable("idCommandeFournisseur") Integer id) {
		
		if (id==null) {
			return ;
		}
		commandeFournisseurService.delete(id);
	}
}
