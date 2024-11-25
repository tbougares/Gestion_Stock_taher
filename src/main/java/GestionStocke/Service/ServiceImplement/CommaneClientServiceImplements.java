package GestionStocke.Service.ServiceImplement;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import GestionStocke.Service.AlerteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import GestionStocke.DTO.ArticleDto;
import GestionStocke.DTO.CommandClientDto;
import GestionStocke.DTO.CreditDto;
import GestionStocke.DTO.LigneCommaneClientDto;
import GestionStocke.DTO.MvtStkDto;
import GestionStocke.DTO.TranchDto;
import GestionStocke.Exception.EntityNotFoundException;
import GestionStocke.Exception.ErrorCodes;
import GestionStocke.Exception.InvalidEntityException;
import GestionStocke.Exception.InvalidOperationException;
import GestionStocke.Service.CommandeClientService;
import GestionStocke.Service.MvtStkService;
import GestionStocke.Validator.CommandeClientValidator;
import GestionStocke.entity.Article;
import GestionStocke.entity.Client;
import GestionStocke.entity.CommandeClient;
import GestionStocke.entity.Credit;
import GestionStocke.entity.EtatCommande;
import GestionStocke.entity.LigneCommandeClient;
import GestionStocke.entity.SourceMvtStk;
import GestionStocke.entity.Tranch;
import GestionStocke.entity.TypeMvtStk;
import GestionStocke.repostory.Articlerepository;
import GestionStocke.repostory.ClientRepository;
import GestionStocke.repostory.CommandeClientRepository;
import GestionStocke.repostory.CreditRepository;
import GestionStocke.repostory.LigneCommmandeClientRepository;
import GestionStocke.repostory.TranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
public class CommaneClientServiceImplements implements CommandeClientService {

	
	  private final CommandeClientRepository commandeClientRepository;
	  private final LigneCommmandeClientRepository ligneCommandeClientRepository;
	  private final ClientRepository clientRepository;
	  private final Articlerepository articleRepository;
	  private final MvtStkService mvtStkService;
	  private final CreditRepository creditRepository ;
	  private final AlerteService alerteService ;
	  private final TranchRepository tranchRepository ;
	  

	  @Override
	  public CommandClientDto save(CommandClientDto dto) {
	      List<String> errors = CommandeClientValidator.commandeClientValidate(dto);
	      if (!errors.isEmpty()) {
	          throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
	      }
	      if (dto.getId() != null && dto.isCommandeLivree()) {
	          throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
	      }

	      Optional<Client> client = clientRepository.findById(dto.getClient().getId());
	      if (client.isEmpty()) {
	          throw new EntityNotFoundException("Aucun client avec l'ID " + dto.getClient().getId() + " n'a ete trouve dans la BDD", ErrorCodes.CLIENT_NOT_FOUND);
	      }		 
	      

	      
	      

	      List<String> articleErrors = new ArrayList<>();
	      if (dto.getLigneCommandeClients() != null) {
	          dto.getLigneCommandeClients().forEach(ligCmdClt -> {
	              if (ligCmdClt.getArticle() != null) {
	                  Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
	                  if (article.isEmpty()) {
	                      articleErrors.add("L'article avec l'ID " + ligCmdClt.getArticle().getId() + " n'existe pas");
	                  }
	              } else {
	                  articleErrors.add("Impossible d'enregister une commande avec un aticle NULL");
	              }
	          });
	      }
	      


	      if (!articleErrors.isEmpty()) {
	          throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
	      }


		  // Calculer le montant total de la commande
		  double montantTotal = 0.0;
		  if (dto.getLigneCommandeClients() != null) {
			  for (LigneCommaneClientDto ligneCommande : dto.getLigneCommandeClients()) {
				  
				  montantTotal += ligneCommande.getQuantite() * ligneCommande.getPrixUnitaire();
				  // Mettre à jour les quantités des articles
	                ArticleDto articleDto = ligneCommande.getArticle();
	                Optional<Article> articleOpt = articleRepository.findById(articleDto.getId());
	                if (articleOpt.isPresent()) {
	                    Article article = articleOpt.get();
	                    int nouvelleQuantite = Integer.parseInt(article.getQuantite()) - ligneCommande.getQuantite();
	                    if (nouvelleQuantite < 0) {
	                        throw new InvalidOperationException("Quantité insuffisante pour l'article " + article.getId(), ErrorCodes.ARTICLE_QUANTITY_INSUFFICIENT);
	                    }else if (nouvelleQuantite < article.getSeuilQuantite()) {
							// Créer une alerte si la quantité est inférieure au seuil
							alerteService.creerAlerte(article, "La quantité de l'article " + article.getCodeArticle() + " est inférieure au seuil minimal.");
						}
	                    article.setQuantite(String.valueOf(nouvelleQuantite));
	                    articleRepository.save(article);
	                } 
				
			  }
		  }

		  dto.setMontant(montantTotal);
		  dto.setDateCommande(Instant.now());

		  CommandeClient savedCmdClt = commandeClientRepository.save(CommandClientDto.toEntity(dto));

	      if (dto.getLigneCommandeClients() != null) {
	          dto.getLigneCommandeClients().forEach(ligCmdClt -> {
	        	  
	              LigneCommandeClient ligneCommandeClient = LigneCommaneClientDto.toEntity(ligCmdClt); 
	              ligneCommandeClient.setCommandeClient(savedCmdClt);
	              LigneCommandeClient savedLigneCmd = ligneCommandeClientRepository.save(ligneCommandeClient);
	              effectuerSortie(savedLigneCmd);
	              
	          });
	      }

	      if (dto.isContientCredit()) {
	          CreditDto cdto = dto.getCredit();
	          Credit credit = CreditDto.toEntity(cdto);
	          credit.setCommandeClient(savedCmdClt);
	          Credit savedCredit = creditRepository.save(credit);


	          savedCmdClt.setCredit(savedCredit);
	          commandeClientRepository.save(savedCmdClt);
	      }

	      return CommandClientDto.fromEntity(savedCmdClt);
	  }

	@Override
	public CommandClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandClientDto updateClient(Integer idCommande, Integer idClient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandClientDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandClientDto findById(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
		      //log.error("Commande client ID is NULL");
		      return null;
		    }
		    return commandeClientRepository.findById(id)
		        .map(CommandClientDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucune commande client n'a ete trouve avec l'ID " + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
		        ));
	}

	@Override
	public CommandClientDto findByCode(String code) {
		// TODO Auto-generated method stub
		if (!StringUtils.hasLength(code)) {
		      //log.error("Commande client CODE is NULL");
		      return null;
		    }
		    return commandeClientRepository.findCommandeClientByCode(code)
		        .map(CommandClientDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucune commande client n'a ete trouve avec le CODE " + code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
		        ));
	}

	@Override
	public List<CommandClientDto> findAll() {
		// TODO Auto-generated method stub
		 return commandeClientRepository.findAll().stream()
			        .map(CommandClientDto::fromEntity)
			        .collect(Collectors.toList());
			  }

	@Override
    public void delete(Integer id) {
	   if (id == null) {
	//      log.error("Commande client ID is NULL");
		     return;
		   }
			    List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByCommandeClientId(id);
			    if (!ligneCommandeClients.isEmpty()) {
			      throw new InvalidOperationException("Impossible de supprimer une commande client deja utilisee",
			          ErrorCodes.COMMANDE_CLIENT_ALREADY_IN_USE);
			    }
			    commandeClientRepository.deleteById(id);
			  }

	@Override
	public List<LigneCommaneClientDto> findAllLignesCommandesClientByCommandeClientId(Integer idCommande) {
		// TODO Auto-generated method stub
		return null;
	}

	private void effectuerSortie(LigneCommandeClient lig) {
	    MvtStkDto mvtStkDto = MvtStkDto.builder()
	        .article(ArticleDto.fromEntity(lig.getArticle()))
	        .dateMvt(Instant.now())
	        .typeMvtStock(TypeMvtStk.Sortie)
	        .sourceMvt(SourceMvtStk.COMMANDE_CLIENT)
	        .quantite(lig.getQuantite())
	        .idEntreprise(lig.getIdEntreprise())
	        .build();
	    mvtStkService.sortieStock(mvtStkDto);
	  }

}
