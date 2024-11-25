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

import GestionStocke.DTO.ClientDto;
import GestionStocke.Service.ClienService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClienService clientService;

	
	@GetMapping("/afficher")
	public String article() {
		
		
		return "test bonjour";
	}
	@PostMapping("/create")
	 public ResponseEntity<?> save(
	            @RequestBody @Valid  ClientDto  dto
	    ) {
	        return ResponseEntity
	                .accepted().body(clientService.saveClient(dto));
	    }
	@GetMapping("/{idClient}")
	public ResponseEntity<ClientDto> findById(
            @PathVariable("idClient") Integer id
    ) {
        return ResponseEntity.ok(clientService.findClientById(id));
    }
	
	
	@GetMapping("/all")
	public ResponseEntity<List<ClientDto>> findAll(
    ) {
        return ResponseEntity.ok(clientService.findAllClient());
    }
	
	@DeleteMapping("/delete/{idClient}")
	public void delete(@PathVariable("idClient") Integer id) {
		
		if (id==null) {
			return ;
		}
		clientService.deleteClientById(id);
	}
}

