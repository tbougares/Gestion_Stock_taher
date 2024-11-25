package GestionStocke.repostory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.LigneCommandeClient;
@Repository
public interface LigneCommmandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {
	//LigneCommandeClient findByArticleAndCommandeClient(Article article,CommandeClient commandeClient);//retourne qteCommande
	
	 List<LigneCommandeClient> findAllByCommandeClientId(Integer id);

	  List<LigneCommandeClient> findAllByArticleId(Integer id);
}
