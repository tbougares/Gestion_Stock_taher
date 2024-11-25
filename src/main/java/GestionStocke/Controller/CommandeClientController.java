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
import GestionStocke.DTO.CommandClientDto;
import GestionStocke.Service.CommandeClientService;
import GestionStocke.entity.EtatCommande;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commandesClients")
public class CommandeClientController {

    private final CommandeClientService commandeClientService;

	
	@GetMapping("/afficher")
	public String article() {
		
		
		return "test bonjour";
	}
	@PostMapping("/create")
	 public ResponseEntity<?> save(
	            @RequestBody @Valid  CommandClientDto   dto
	    ) {
	        return ResponseEntity
	                .accepted().body(commandeClientService.save(dto));
	    }
	
	
	@PatchMapping( "/update/etat/{idCommande}/{etatCommande}")
	  ResponseEntity<CommandClientDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande){
		return ResponseEntity.ok(commandeClientService.updateEtatCommande(idCommande, etatCommande));
	}
	
	@PatchMapping("/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
	  ResponseEntity<CommandClientDto> updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,
	      @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite ){
		return ResponseEntity.ok(commandeClientService.updateQuantiteCommande(idCommande, idLigneCommande,quantite));

	  }

	  
	  @PatchMapping("/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
	  ResponseEntity<CommandClientDto> updateArticle(@PathVariable("idCommande") Integer idCommande,
	      @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle){
			return ResponseEntity.ok(commandeClientService.updateArticle(idCommande, idLigneCommande, idArticle));
		}

	@GetMapping("/{idCommandeClient}")
	public ResponseEntity<CommandClientDto> findById(
            @PathVariable("idCommandeClient") Integer id
    ) {
        return ResponseEntity.ok(commandeClientService.findById(id));
    }
	
	
	@GetMapping("/all")
	public ResponseEntity<List<CommandClientDto>> findAll(
    ) {
        return ResponseEntity.ok(commandeClientService.findAll());
    }
	
	@DeleteMapping("/delete/{idCommandeClient}")
	public void delete(@PathVariable("idCommandeClient") Integer id) {
		
		if (id==null) {
			return ;
		}
		commandeClientService.delete(id);
	}
}
