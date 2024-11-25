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

import GestionStocke.DTO.VentesDto;
import GestionStocke.Service.VentesService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/ventes")
public class VentesController {

	
    private final VentesService ventesService;

	@PostMapping("/create")
	 public  ResponseEntity<VentesDto> save(@RequestBody VentesDto dto){
		
		return ResponseEntity
                .accepted().body(ventesService.save(dto));
	}

	  @GetMapping("/{idVente}")
	  public ResponseEntity<VentesDto> findById(@PathVariable("idVente") Integer id){

		  
		  return ResponseEntity
	                .accepted().body(ventesService.findById(id));

	  }
	  @GetMapping("/{codeVente}")
	  public ResponseEntity<VentesDto> findByCode(@PathVariable("codeVente") String code){
		  return ResponseEntity
	                .accepted().body(ventesService.findByCode(code));
	  }

	  @GetMapping("/all")
	  public ResponseEntity<List<VentesDto>> findAll(){
		  return ResponseEntity
	                .accepted().body(ventesService.findAll());
	  }

	  @DeleteMapping("/delete/{idVente}")
	  public void delete(@PathVariable("idVente") Integer id){
		   ventesService.delete(id);
	  }
}
