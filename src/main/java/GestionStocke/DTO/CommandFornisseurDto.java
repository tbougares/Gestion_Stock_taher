package GestionStocke.DTO;

import java.time.Instant;
import java.util.List;

import GestionStocke.entity.CommandeFornisseur;
import GestionStocke.entity.EtatCommande;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommandFornisseurDto {
	private Integer id;
    private String code;
	
	private Instant dateCommande;
	
	private EtatCommande etatCommande;
	
	
	private FornisseurDto fornisseur;
	
	
	private Integer idEntreprise;

	  private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

	  public static CommandFornisseurDto fromEntity(CommandeFornisseur commandeFournisseur) {
	    if (commandeFournisseur == null) {
	      return null;
	    }
	    return CommandFornisseurDto.builder()
	        .id(commandeFournisseur.getId())
	        .code(commandeFournisseur.getCode())
	        .dateCommande(commandeFournisseur.getDateCommande())
	        .fornisseur(FornisseurDto.fromEntity(commandeFournisseur.getFornisseur()))
	        .etatCommande(commandeFournisseur.getEtatCommande())
	        .idEntreprise(commandeFournisseur.getIdEntreprise())
	        .build();
	  }

	  public static CommandeFornisseur toEntity(CommandFornisseurDto dto) {
	    if (dto == null) {
	      return null;
	    }
	    CommandeFornisseur commandeFournisseur = new CommandeFornisseur();
	    commandeFournisseur.setId(dto.getId());
	    commandeFournisseur.setCode(dto.getCode());
	    commandeFournisseur.setDateCommande(dto.getDateCommande());
	    commandeFournisseur.setFornisseur(FornisseurDto.toEntity(dto.getFornisseur()));
	    commandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
	    commandeFournisseur.setEtatCommande(dto.getEtatCommande());
	    return commandeFournisseur;
	  }

	  public boolean isCommandeLivree() {
	    return EtatCommande.LIVREE.equals(this.etatCommande);
	  }

}
