package GestionStocke.repostory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.LigneVente;

@Repository
public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {
	  List<LigneVente> findAllByArticleId(Integer idArticle);

	  List<LigneVente> findAllByVenteId(Integer id);
				

}
