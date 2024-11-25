package GestionStocke.repostory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.LigneCommandeFornisseur;


@Repository
public interface LigneCommandeFornisseurRepository extends JpaRepository<LigneCommandeFornisseur, Integer> {
	 List<LigneCommandeFornisseur> findAllByCommandeFournisseurId(Integer idCommande);

	  List<LigneCommandeFornisseur> findAllByArticleId(Integer idCommande);
}
