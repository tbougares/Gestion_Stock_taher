package GestionStocke.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GestionStocke.DTO.MvtStkDto;
import GestionStocke.Service.MvtStkService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Mvts")
public class MvtStkController {

	
	  private final MvtStkService service;

	  @GetMapping("/stockreel/{idArticle}")
	  public ResponseEntity<BigDecimal> stockReelArticle(@PathVariable("idArticle") Integer idArticle){

		  return ResponseEntity.ok(service.stockReelArticle(idArticle));

}

	  @GetMapping("/filter/article/{idArticle}")
	  public ResponseEntity<List<MvtStkDto>> mvtStkArticle(@PathVariable("idArticle") Integer idArticle){

		  return ResponseEntity.ok(service.mvtStkArticle(idArticle));

}

	  @PostMapping("/entree")
	  public ResponseEntity<MvtStkDto> entreeStock(@RequestBody MvtStkDto dto){

		  return ResponseEntity.ok(service.entreeStock(dto));


}

	  @PostMapping("/sortie")
	  public ResponseEntity<MvtStkDto> sortieStock(@RequestBody MvtStkDto dto){
		  
		  return ResponseEntity.ok(service.sortieStock(dto));


}

	  @PostMapping("/correctionpos")
	  ResponseEntity<MvtStkDto> correctionStockPos(@RequestBody MvtStkDto dto){

		  
		  return ResponseEntity.ok(service.correctionStockPos(dto));


}

	  @PostMapping("/correctionneg")
	  ResponseEntity<MvtStkDto> correctionStockNeg(@RequestBody MvtStkDto dto){

		  return ResponseEntity.ok(service.correctionStockNeg(dto));


}
}
