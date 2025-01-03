package GestionStocke.Service;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUpload {
	 public static void saveFile(String uploadDir, String fileName,
		        MultipartFile multipartFile) throws IOException {
		
			   Path uploadPath = Paths.get(uploadDir);

		        if (!Files.exists(uploadPath)) {
		            Files.createDirectories(uploadPath);
		        }

		        try (InputStream inputStream = multipartFile.getInputStream()) {
		            Path filePath = uploadPath.resolve(fileName);
		            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		        } catch (IOException ioe) {
		            throw new IOException("Could not save image file: " + fileName, ioe);
		        }
		        
		    }
	 
	  public Resource loadFileAsResource(String fileName,String directory) throws MalformedURLException {
	        Path filePath = Paths.get(directory).resolve(fileName).normalize();
	        Resource resource = new UrlResource(filePath.toUri());
	        if (resource.exists()) {
	            return resource;
	        } else {
	            throw new RuntimeException("File not found: " + fileName);
	        }
	    }
}
