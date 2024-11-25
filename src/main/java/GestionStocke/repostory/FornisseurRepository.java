package GestionStocke.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.Fornisseur;


@Repository
public interface FornisseurRepository extends JpaRepository<Fornisseur, Integer> {
	//kima clientreposotory
}
