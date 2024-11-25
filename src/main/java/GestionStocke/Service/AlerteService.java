package GestionStocke.Service;

import GestionStocke.entity.Alerte;
import GestionStocke.entity.Article;

import java.util.List;

public interface AlerteService {
    void creerAlerte(Article article, String message)   ;

    List<Alerte> findAll();

    Alerte findAlerteById(Integer id);
}
