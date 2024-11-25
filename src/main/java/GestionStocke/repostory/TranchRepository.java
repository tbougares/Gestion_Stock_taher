package GestionStocke.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import GestionStocke.entity.Tranch;

import java.util.List;

@Repository
public interface TranchRepository extends JpaRepository<Tranch, Integer>{
   List<Tranch> findByCreditId(@Param("creditId") Integer creditId );
}
