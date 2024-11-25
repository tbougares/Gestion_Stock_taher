package GestionStocke.repostory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.Credit;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Integer>{
	
	@Query("SELECT c FROM Credit c WHERE c.commandeClient.client.id = :clientId")
    List<Credit> findByClientId(Integer clientId);
}
