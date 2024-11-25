package GestionStocke.Service.ServiceImplement;

import GestionStocke.entity.Article;
import GestionStocke.entity.CommandeClient;
import GestionStocke.entity.Credit;
import GestionStocke.entity.LigneVente;
import GestionStocke.repostory.Articlerepository;
import GestionStocke.repostory.CommandeClientRepository;
import GestionStocke.repostory.CreditRepository;
import GestionStocke.repostory.LigneVenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatistiquesServiceImpl {
    @Autowired
    private Articlerepository articleRepository;

    @Autowired
    private LigneVenteRepository ligneVenteRepository;
    @Autowired
    private CommandeClientRepository commandeClientRepository;
    @Autowired
    private CreditRepository creditRepository;



    // Service pour le Stock Total
    public BigDecimal getTotalStock() {
        return articleRepository.findAll().stream()
                .map(article -> new BigDecimal(article.getQuantite()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //  Service pour les Articles en dessous du Seuil de Stock
    public List<Article> getLowStockArticles() {
        return articleRepository.findAll().stream()
                .filter(article -> Integer.parseInt(article.getQuantite()) < article.getSeuilQuantite())
                .collect(Collectors.toList());
    }


    // Service pour les Articles les plus Vendus
    public List<Map<String, Object>> getTopSellingArticles() {
        return ligneVenteRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        lv -> lv.getArticle().getDesignation(),
                        Collectors.summingInt(lv -> lv.getQuantite().intValue()) // Conversion en int
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("article", entry.getKey());
                    map.put("quantiteVendue", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());
    }




    //  Service pour le Montant Total des Commandes


    public BigDecimal getTotalCommandes() {
        return commandeClientRepository.findAll().stream()
                .map(cc -> BigDecimal.valueOf(cc.getMontant())) // Conversion en BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }




    // Service pour les Crédits en Cours

    public BigDecimal getTotalRemainingCredits() {
        return creditRepository.findAll().stream()
                .map(credit -> BigDecimal.valueOf(credit.getReste())) // Conversion en BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /*
    Service pour le Stock par Catégorie
     But: Obtenir le stock total par catégorie d'articles.
    */
    public Map<String, BigDecimal> getStockByCategory() {
        return articleRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        article -> article.getCategory().getDesignation(),
                        Collectors.reducing(BigDecimal.ZERO, article -> new BigDecimal(article.getQuantite()), BigDecimal::add)
                ));
    }



}
