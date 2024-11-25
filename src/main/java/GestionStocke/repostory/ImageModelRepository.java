package GestionStocke.repostory;

import GestionStocke.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageModelRepository extends JpaRepository<ImageModel, Integer> {

    ImageModel findByUserId(String userId);

}
