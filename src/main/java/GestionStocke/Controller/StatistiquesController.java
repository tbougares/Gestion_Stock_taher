package GestionStocke.Controller;

import GestionStocke.Service.CommandeClientService;
import GestionStocke.Service.CreditService;
import GestionStocke.Service.ServiceImplement.StatistiquesServiceImpl;
import GestionStocke.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatistiquesController {
    private final StatistiquesServiceImpl statistiquesService;

    @Autowired
    public StatistiquesController(StatistiquesServiceImpl statistiquesService) {
        this.statistiquesService = statistiquesService;
    }


    // API pour le Stock Total
    @GetMapping("/total-stock")
    public ResponseEntity<BigDecimal> getTotalStock() {
        BigDecimal totalStock = statistiquesService.getTotalStock();
        return ResponseEntity.ok(totalStock);
    }

    // API pour les Articles en dessous du Seuil de Stock
    @GetMapping("/low-stock-articles")
    public ResponseEntity<List<Article>> getLowStockArticles() {
        List<Article> lowStockArticles = statistiquesService.getLowStockArticles();
        return ResponseEntity.ok(lowStockArticles);
    }

    // API pour les Articles les plus Vendus
    @GetMapping("/top-selling-articles")
    public ResponseEntity<List<Map<String, Object>>> getTopSellingArticles() {
        List<Map<String, Object>> topSellingArticles = statistiquesService.getTopSellingArticles();
        return ResponseEntity.ok(topSellingArticles);
    }

    // API pour le Montant Total des Commandes
    @GetMapping("/total-commandes")
    public ResponseEntity<BigDecimal> getTotalCommandes() {
        BigDecimal totalCommandes = statistiquesService.getTotalCommandes();
        return ResponseEntity.ok(totalCommandes);
    }


    // API pour les Crédits en Cours
    @GetMapping("/total-remaining-credits")
    public ResponseEntity<BigDecimal> getTotalRemainingCredits() {
        BigDecimal totalRemainingCredits = statistiquesService.getTotalRemainingCredits();
        return ResponseEntity.ok(totalRemainingCredits);
    }

    // API pour le Stock par Catégorie
    @GetMapping("/stock-by-category")
    public ResponseEntity<Map<String, BigDecimal>> getStockByCategory() {
        Map<String, BigDecimal> stockByCategory = statistiquesService.getStockByCategory();
        return ResponseEntity.ok(stockByCategory);
    }

}
