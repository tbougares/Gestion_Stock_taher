package GestionStocke.repostory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	  // JPQL query
	  @Query(value = "select u from Utilisateur u where u.email = :email")
	  Optional<Utilisateur> findUtilisateurByEmail(@Param("email") String email);

	Optional<Utilisateur> findByEmail(String email);
	Boolean existsByEmail(String email);




}
