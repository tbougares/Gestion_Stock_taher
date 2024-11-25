package GestionStocke.repostory;

import org.springframework.data.jpa.repository.JpaRepository;

import GestionStocke.entity.File;

public interface FileRepsitory extends JpaRepository<File, Long> {

	
	@SuppressWarnings("unchecked")
	File save(File fiels);
}
