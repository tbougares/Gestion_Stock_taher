package GestionStocke.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.Entreprise;


@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {

}
