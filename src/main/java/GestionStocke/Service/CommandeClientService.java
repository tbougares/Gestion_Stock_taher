package GestionStocke.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import GestionStocke.DTO.CommandClientDto;
import GestionStocke.DTO.LigneCommaneClientDto;
import GestionStocke.entity.EtatCommande;
@Service

public interface CommandeClientService {
	
	
	  CommandClientDto save(CommandClientDto dto);

	  CommandClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

	  CommandClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

	  CommandClientDto updateClient(Integer idCommande, Integer idClient);

	  CommandClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle);

	  // Delete article ==> delete LigneCommandeClient
	  CommandClientDto deleteArticle(Integer idCommande, Integer idLigneCommande);

	  CommandClientDto findById(Integer id);

	  CommandClientDto findByCode(String code);

	  List<CommandClientDto> findAll();

	  List<LigneCommaneClientDto> findAllLignesCommandesClientByCommandeClientId(Integer idCommande);

	  void delete(Integer id);

}
