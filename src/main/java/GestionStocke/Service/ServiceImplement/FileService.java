package GestionStocke.Service.ServiceImplement;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import GestionStocke.entity.File;
import GestionStocke.repostory.FileRepsitory;
import GestionStocke.Service.FilesService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService implements FilesService {

	
	private final FileRepsitory fileRepsitory;
	@Override
	public File save(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		

		String fileName = StringUtils.cleanPath(file.getOriginalFilename()); 
		File fileDB = new File();
		fileDB.setData(file.getBytes());
		fileDB.setType(file.getContentType());
		fileDB.setName(fileName);
		return fileRepsitory.save(fileDB);
	}

	

	@Override
	public Stream<File> findAll() {
		// TODO Auto-generated method stub
		return fileRepsitory.findAll().stream();	
	}



	@Override
	public File findById(Long id) {
		// TODO Auto-generated method stub
		return fileRepsitory.findById(id).get();
	}

}
