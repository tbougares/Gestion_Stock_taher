package GestionStocke.Service.ServiceImplement;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import GestionStocke.utiles.BarcodeGenerator;
import com.google.zxing.WriterException;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import GestionStocke.DTO.ArticleDto;
import GestionStocke.DTO.LigneCommandeFournisseurDto;
import GestionStocke.DTO.LigneCommaneClientDto;
import GestionStocke.DTO.LigneVenteDto;
import GestionStocke.Exception.EntityNotFoundException;
import GestionStocke.Exception.ErrorCodes;
import GestionStocke.Exception.InvalidEntityException;
import GestionStocke.Exception.InvalidOperationException;
import GestionStocke.Service.ArticleService;
import GestionStocke.Specification.ArticleSpecification;
import GestionStocke.Validator.ArticleValidator;
import GestionStocke.entity.Article;
import GestionStocke.entity.LigneCommandeClient;
import GestionStocke.entity.LigneCommandeFornisseur;
import GestionStocke.entity.LigneVente;
import GestionStocke.entity.ModeleVoiture;
import GestionStocke.repostory.Articlerepository;
import GestionStocke.repostory.CategoryRepository;
import GestionStocke.repostory.LigneCommandeFornisseurRepository;
import GestionStocke.repostory.LigneCommmandeClientRepository;
import GestionStocke.repostory.LigneVenteRepository;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImplements implements ArticleService {

	  private final Articlerepository articleRepository;
	  private final LigneVenteRepository venteRepository;
	  private final LigneCommandeFornisseurRepository commandeFournisseurRepository;
	  private final LigneCommmandeClientRepository commandeClientRepository;



	@Override
	public ArticleDto save(ArticleDto dto) {
		// Validation de l'article
		List<String> errors = ArticleValidator.articleValidate(dto);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
		}

		// Vérifier si le code-barre existe déjà pour l'article
		if (dto.getCodeBarre() == null || dto.getCodeBarre().isEmpty()) {
			// Si le code-barre n'existe pas, en générer un nouveau
			String codeBarre = generateUniqueCodeBarre();
			dto.setCodeBarre(codeBarre);
		}
		// Si le code-barre existe, il est conservé tel quel

		// Sauvegarder l'article
		ArticleDto savedArticle = ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(dto)));

		// Générez et enregistrez l'image du code-barre si nécessaire
		if (savedArticle.getCodeBarre() != null && !savedArticle.getCodeBarre().isEmpty()) {
			try {
				BarcodeGenerator.generateBarcodeImage(
						savedArticle.getCodeBarre(),
						"C:\\Users\\ASUS\\eclipse-workspace\\code" + savedArticle.getId() + ".png"
				);
			} catch (WriterException | IOException e) {
				e.printStackTrace();
				// Gérer l'erreur si la génération d'image échoue
			}
		} else {
			throw new IllegalStateException("Le code-barre n'a pas été généré correctement.");
		}

		return savedArticle;
	}

	private String generateUniqueCodeBarre() {
		// Implémentation de la génération d'un code-barre unique (exemple : UUID)

		return "CODE" + System.currentTimeMillis(); // Exemple simplifié
	}


	@Override
	public List<ArticleDto> getArticleByCodeBarre(String codeBarre) {

		List<Article> articles = this.articleRepository.findByCodeBarre(codeBarre);
		return articles.stream()
				.map(ArticleDto::fromEntity)
				.collect(Collectors.toList());
	}

	  @Override
	  public ArticleDto findById(Integer id) {
	    if (id == null) {
	      //log.error("Article ID is null");
	      return null;
	    }

	    return articleRepository.findById(id).map(ArticleDto::fromEntity).orElseThrow(() ->
	        new EntityNotFoundException(
	            "Aucun article avec l'ID = " + id + " n' ete trouve dans la BDD",
	            ErrorCodes.ARTICLE_NOT_FOUND)
	    );
	  }

	  @Override
	  public ArticleDto findByCodeArticle(String codeArticle) {
	    if (!StringUtils.hasLength(codeArticle)) {
	      //log.error("Article CODE is null");
	      return null;
	    }

	    return articleRepository.findByCodeArticle(codeArticle)
	        .map(ArticleDto::fromEntity)
	        .orElseThrow(() ->
	            new EntityNotFoundException(
	                "Aucun article avec le CODE = " + codeArticle + " n' ete trouve dans la BDD",
	                ErrorCodes.ARTICLE_NOT_FOUND)
	        );
	  }

	  @Override
	  public List<ArticleDto> findAll() {
	    return articleRepository.findAll().stream()
	        .map(ArticleDto::fromEntity)
	        .collect(Collectors.toList());
	  }
		@Override
		// Recherche des articles par plusieurs modèles de voiture
	    public List<ArticleDto> searchByModeles(List<String> modeles) {
	        // Applique la spécification pour rechercher par plusieurs modèles
	        Specification<Article> spec = ArticleSpecification.hasModeles(modeles);
	        List<Article> articles = articleRepository.findAll(spec);

	        // Convertir les entités en DTOs
	        return articles.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }

	    // Méthode pour convertir un Article en ArticleDTO
	    private ArticleDto convertToDTO(Article article) {
	        // Mapper les champs simples
	    	ModelMapper modelMapper = new ModelMapper();
	        ArticleDto articleDTO = modelMapper.map(article, ArticleDto.class);

	        // Mapper les modèles de voiture manuellement si nécessaire
	        articleDTO.setModelesVoiture(
	            article.getModelesVoiture().stream()
	                .map(ModeleVoiture::getNomModele) // Récupère uniquement le nom des modèles
	                .collect(Collectors.toList())
	        );

	        return articleDTO;
	    }
/*
	  @Override
	  public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
	    return venteRepository.findAllByArticleId(idArticle).stream()
	        .map(LigneVenteDto::fromEntity)
	        .collect(Collectors.toList());
	  }*/

	  @Override
	  public List<LigneCommaneClientDto> findHistoriaueCommandeClient(Integer idArticle) {
	    return commandeClientRepository.findAllByArticleId(idArticle).stream()
	        .map(LigneCommaneClientDto::fromEntity)
	        .collect(Collectors.toList());
	  }

	  @Override
	  public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
	    return commandeFournisseurRepository.findAllByArticleId(idArticle).stream()
	        .map(LigneCommandeFournisseurDto::fromEntity)
	        .collect(Collectors.toList());
	  }

	  @Override
	  public List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) {
	    return articleRepository.findAllByCategoryId(idCategory).stream()
	        .map(ArticleDto::fromEntity)
	        .collect(Collectors.toList());
	  }

	  @Override
	  public void delete(Integer id) {
	    if (id == null) {
	      //log.error("Article ID is null");
	      return;
	    }
	    List<LigneCommandeClient> ligneCommandeClients = commandeClientRepository.findAllByArticleId(id);
	    if (!ligneCommandeClients.isEmpty()) {
	      throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans des commandes client", ErrorCodes.ARTICLE_ALREADY_IN_USE);
	    }
	    List<LigneCommandeFornisseur> ligneCommandeFournisseurs = commandeFournisseurRepository.findAllByArticleId(id);
	    if (!ligneCommandeFournisseurs.isEmpty()) {
	      throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans des commandes fournisseur",
	          ErrorCodes.ARTICLE_ALREADY_IN_USE);
	    }
	    List<LigneVente> ligneVentes = venteRepository.findAllByArticleId(id);
	    if (!ligneVentes.isEmpty()) {
	      throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans des ventes",
	          ErrorCodes.ARTICLE_ALREADY_IN_USE);
	    }
	    articleRepository.deleteById(id);
	  }



	@Override
	public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
		// TODO Auto-generated method stub
		return venteRepository.findAllByArticleId(idArticle).stream()
		        .map(LigneVenteDto::fromEntity)
		        .collect(Collectors.toList());
	}


}
