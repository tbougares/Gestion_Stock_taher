package GestionStocke.repostory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.Ventes;

@Repository
public interface VentesRepository extends JpaRepository<Ventes, Integer> {
	  Optional<Ventes> findVentesByCode(String code);

}
