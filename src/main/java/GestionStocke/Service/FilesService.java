package GestionStocke.Service;


import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import GestionStocke.entity.File;


public interface FilesService {

	
	public File save(MultipartFile file) throws IOException;
	public File findById (Long id);
	public Stream<File> findAll();

}
