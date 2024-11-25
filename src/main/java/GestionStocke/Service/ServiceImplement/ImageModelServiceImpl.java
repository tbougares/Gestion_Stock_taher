package GestionStocke.Service.ServiceImplement;

import java.io.IOException;
import java.util.Optional;

import GestionStocke.entity.ImageModel;
import GestionStocke.repostory.ImageModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ImageModelServiceImpl {

    private final ImageModelRepository imageModelRepository ;


    // Enregistrer une image pour un utilisateur
    public ImageModel processSingleFile(MultipartFile file, String userId) {
        try {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes(),
                    userId
            );

            return imageModelRepository.save(imageModel);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier", e);
        }
    }


    // Récupérer toutes les images pour un utilisateur donné
    public ImageModel getImagesByUserId(String userId) {
        return imageModelRepository.findByUserId(userId);
    }


    // Supprimer une image par userId
    public void deleteImageById(Integer id) {
        imageModelRepository.deleteById(id);
    }

    // recupérer l'image par id
    public Optional<ImageModel> getImageById(Integer id) {
        return imageModelRepository.findById(id);
    }


    public ImageModel updateImage(Integer id, MultipartFile updatedFile) {
        // Recherche de l'image existante par ID
        ImageModel existingImage = imageModelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + id));

        // Mettre à jour le contenu du fichier si un nouveau fichier est fourni
        if (updatedFile != null) {
            try {
                existingImage.setPicByte(updatedFile.getBytes());
                existingImage.setName(updatedFile.getOriginalFilename());
            } catch (Exception e) {
                throw new RuntimeException("Failed to update image content", e);
            }
        }

        // Enregistrer l'image mise à jour dans la base de données
        return imageModelRepository.save(existingImage);
    }

}
