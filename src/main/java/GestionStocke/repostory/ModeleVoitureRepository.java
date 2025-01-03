package GestionStocke.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.ModeleVoiture;

@Repository
public interface ModeleVoitureRepository extends JpaRepository<ModeleVoiture, Integer>{

}
