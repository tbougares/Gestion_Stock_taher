package GestionStocke.Service.ServiceImplement;

import GestionStocke.Service.AlerteService;
import GestionStocke.entity.Alerte;
import GestionStocke.entity.Article;
import GestionStocke.repostory.AlerteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlerteServiceImpl implements AlerteService {

    private final AlerteRepository alerteRepository ;
    @Override
    public void creerAlerte(Article article, String message) {
        Alerte alerte = new Alerte();
        alerte.setArticle(article);
        alerte.setMessage(message);
        alerteRepository.save(alerte);
    }

    @Override
    public List<Alerte> findAll() {
        return alerteRepository.findAll();
    }

    @Override
    public Alerte findAlerteById(Integer id) {
       Alerte al = alerteRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Alerte not found by id ")) ;
        al.setRead(true);
        alerteRepository.save(al);
        return al;
    }
}
