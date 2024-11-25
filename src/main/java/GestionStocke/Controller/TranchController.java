package GestionStocke.Controller;

import GestionStocke.DTO.TranchDto;
import GestionStocke.Service.TranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tranch")
public class TranchController {

    private final TranchService tranchService ;

    @PostMapping("")
    public TranchDto create(@RequestBody TranchDto tranchDto , @RequestParam Integer creditId) {
    TranchDto response = tranchService.save(tranchDto , creditId)     ;
    return response ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id , @RequestBody TranchDto tranchDto) {
       TranchDto response = tranchService.update(id , tranchDto);
    return new ResponseEntity<>(response , HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public TranchDto findById(@PathVariable Integer id) {
        TranchDto tranchDto = tranchService.findById(id);
    return tranchDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)  {
        tranchService.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/findByCreditId")
    public ResponseEntity<?> findbyCreditId(@RequestParam("creditId") Integer creditId) {
        List<TranchDto> response =   tranchService.findByCreditId(creditId)  ;
    return new ResponseEntity<>(response , HttpStatus.OK);
    }

}
