package GestionStocke.Controller;

import GestionStocke.Service.AlerteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alerte")
@RequiredArgsConstructor
public class AlerteController {

        private final AlerteService alerteService ;

        @GetMapping("")
        public ResponseEntity<?> findAllAlerte(){
           return ResponseEntity.ok(alerteService.findAll());
        }

        @GetMapping("/{id}")
         public ResponseEntity<?> findAlerteById(@PathVariable Integer id){
          return    ResponseEntity.ok(alerteService.findAlerteById(id))   ;
        }
}
