package GestionStocke.Service;

import java.math.BigDecimal;
import java.util.List;

import GestionStocke.DTO.CommandFornisseurDto;
import GestionStocke.DTO.LigneCommandeFournisseurDto;
import GestionStocke.entity.EtatCommande;

public interface CommandeFournisseurService {

	CommandFornisseurDto save(CommandFornisseurDto dto);

	CommandFornisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

	CommandFornisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

	CommandFornisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur);

	CommandFornisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);

	  // Delete article ==> delete LigneCommandeFournisseur
	CommandFornisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande);

	CommandFornisseurDto findById(Integer id);

	CommandFornisseurDto findByCode(String code);

	  List<CommandFornisseurDto> findAll();

	  List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande);

	  void delete(Integer id);
}
