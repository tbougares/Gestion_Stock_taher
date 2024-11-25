package GestionStocke.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import GestionStocke.DTO.CreditDto;
import GestionStocke.Service.CreditService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit")
public class CreditController {
	
	private final CreditService creditService ;
	
	@GetMapping("findByClientId")
	public ResponseEntity<?> findAllByClientId(@RequestParam Integer clientId) {
		List<CreditDto> creditDto = this.creditService.findAllByClientId(clientId);
		return new ResponseEntity<>(creditDto , HttpStatus.OK) ;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody CreditDto creditDto , @PathVariable Integer id) {
		CreditDto response = this.creditService.update(creditDto , id);
		return new ResponseEntity<>(response , HttpStatus.OK) ;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		CreditDto creditDto = this.creditService.findById(id);
		return new ResponseEntity<>(creditDto , HttpStatus.OK) ;
	}
	
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<CreditDto> creditDto = this.creditService.findAll();
		return new ResponseEntity<>(creditDto , HttpStatus.OK) ;
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) {

		this.creditService.deleteById(id);
	}
	
	

}
