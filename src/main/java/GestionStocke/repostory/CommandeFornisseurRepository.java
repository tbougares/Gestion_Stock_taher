package GestionStocke.repostory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.CommandeClient;
import GestionStocke.entity.CommandeFornisseur;

@Repository
public interface CommandeFornisseurRepository extends JpaRepository<CommandeFornisseur, Integer> {
	Optional<CommandeFornisseur> findCommandeFournisseurByCode(String code);

	  //List<CommandeFornisseur> findAllByFornisseurId(Integer id);
}
