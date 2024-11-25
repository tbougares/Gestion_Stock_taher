package GestionStocke.repostory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.Client;
import GestionStocke.entity.CommandeClient;
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	Optional<Client> findByNomAndPrenom(String nom,String prenom);
	Optional<Client> findByEmail(String email);
	//Optional<List<CommandeClient>> findByCodeClient(String code); lzem Tytbdle f commandeclient reposotory
}
