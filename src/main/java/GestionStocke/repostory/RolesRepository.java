package GestionStocke.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.Roles;


@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

}
