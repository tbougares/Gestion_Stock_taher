package GestionStocke.Service.ServiceImplement;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import GestionStocke.DTO.ArticleDto;
import GestionStocke.DTO.CommandFornisseurDto;
import GestionStocke.DTO.LigneCommandeFournisseurDto;
import GestionStocke.DTO.MvtStkDto;
import GestionStocke.Exception.EntityNotFoundException;
import GestionStocke.Exception.ErrorCodes;
import GestionStocke.Exception.InvalidEntityException;
import GestionStocke.Exception.InvalidOperationException;
import GestionStocke.Service.CommandeFournisseurService;
import GestionStocke.Service.MvtStkService;
import GestionStocke.Validator.CommandeFournisseurValidator;
import GestionStocke.entity.Article;
import GestionStocke.entity.CommandeFornisseur;
import GestionStocke.entity.EtatCommande;
import GestionStocke.entity.Fornisseur;
import GestionStocke.entity.LigneCommandeFornisseur;
import GestionStocke.entity.SourceMvtStk;
import GestionStocke.entity.TypeMvtStk;
import GestionStocke.repostory.Articlerepository;
import GestionStocke.repostory.CommandeFornisseurRepository;
import GestionStocke.repostory.FornisseurRepository;
import GestionStocke.repostory.LigneCommandeFornisseurRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommandeFournisseurServiceImplements implements CommandeFournisseurService {

	
	private CommandeFornisseurRepository commandeFournisseurRepository;
	  private LigneCommandeFornisseurRepository ligneCommandeFournisseurRepository;
	  private FornisseurRepository fournisseurRepository;
	  private Articlerepository articleRepository;
	  private MvtStkService mvtStkService;
	@Override
	public CommandFornisseurDto save(CommandFornisseurDto dto) {
		// TODO Auto-generated method stub
		
		List<String> errors = CommandeFournisseurValidator.commandeFournisseurValidate(dto);

	    if (!errors.isEmpty()) {
	      log.error("Commande fournisseur n'est pas valide");
	      throw new InvalidEntityException("La commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
	    }

	    if (dto.getId() != null && dto.isCommandeLivree()) {
	      throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
	    }

	    Optional<Fornisseur> fournisseur = fournisseurRepository.findById(dto.getFornisseur().getId());
	    if (fournisseur.isEmpty()) {
	      log.warn("Fournisseur with ID {} was not found in the DB", dto.getFornisseur().getId());
	      throw new EntityNotFoundException("Aucun fournisseur avec l'ID" + dto.getFornisseur().getId() + " n'a ete trouve dans la BDD",
	          ErrorCodes.FOURNISSEUR_NOT_FOUND);
	    }

	    List<String> articleErrors = new ArrayList<>();

	    if (dto.getLigneCommandeFournisseurs() != null) {
	      dto.getLigneCommandeFournisseurs().forEach(ligCmdFrs -> {
	        if (ligCmdFrs.getArticle() != null) {
	          Optional<Article> article = articleRepository.findById(ligCmdFrs.getArticle().getId());
	          if (article.isEmpty()) {
	            articleErrors.add("L'article avec l'ID " + ligCmdFrs.getArticle().getId() + " n'existe pas");
	          }
	        } else {
	          articleErrors.add("Impossible d'enregister une commande avec un aticle NULL");
	        }
	      });
	    }

	    if (!articleErrors.isEmpty()) {
	      log.warn("");
	      throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
	    }
	    dto.setDateCommande(Instant.now());
	    CommandeFornisseur savedCmdFrs = commandeFournisseurRepository.save(CommandFornisseurDto.toEntity(dto));

	    if (dto.getLigneCommandeFournisseurs() != null) {
	      dto.getLigneCommandeFournisseurs().forEach(ligCmdFrs -> {
	        LigneCommandeFornisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmdFrs);
	        ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFrs);
	        ligneCommandeFournisseur.setIdEntreprise(savedCmdFrs.getIdEntreprise());
	        LigneCommandeFornisseur saveLigne = ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);

	        effectuerEntree(saveLigne);
	      });	}
	    return CommandFornisseurDto.fromEntity(savedCmdFrs);
	}

	@Override
	public CommandFornisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandFornisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande,
			BigDecimal quantite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandFornisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandFornisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandFornisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandFornisseurDto findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandFornisseurDto findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommandFornisseurDto> findAll() {
		// TODO Auto-generated method stub
		return commandeFournisseurRepository.findAll().stream()
		        .map(CommandFornisseurDto::fromEntity)
		        .collect(Collectors.toList());
	}

	@Override
	public List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(
			Integer idCommande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
		      log.error("Commande fournisseur ID is NULL");
		      return;
		    }
		commandeFournisseurRepository.deleteById(id);
	}
	 private void effectuerEntree(LigneCommandeFornisseur lig) {
		    MvtStkDto mvtStkDto = MvtStkDto.builder()
		        .article(ArticleDto.fromEntity(lig.getArticle()))
		        .dateMvt(Instant.now())
		        .typeMvtStock(TypeMvtStk.Entree)
		        .sourceMvt(SourceMvtStk.COMMANDE_FOURNISSEUR)
		        .quantite(lig.getQuantite())
		        .idEntreprise(lig.getIdEntreprise())
		        .build();
		    mvtStkService.entreeStock(mvtStkDto);
		  }
}
