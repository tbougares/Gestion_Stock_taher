package GestionStocke.Specification;
import org.springframework.data.jpa.domain.Specification;

import GestionStocke.entity.Article;
import GestionStocke.entity.ModeleVoiture;
import jakarta.persistence.criteria.*;
import java.util.List;

public class ArticleSpecification {

    // Recherche par plusieurs modèles de voiture
    public static Specification<Article> hasModeles(List<String> modeles) {
        return (root, query, builder) -> {
            if (modeles == null || modeles.isEmpty()) {
                return builder.conjunction(); // Pas de filtre si la liste est vide
            }

            // Création de la jointure entre Article et ModeleVoiture
            Join<Article, ModeleVoiture > join = root.join("modelesVoiture");

            // Retourne une condition : le modèle doit être dans la liste
            return join.get("nomModele").in(modeles);
        };
    }
}
