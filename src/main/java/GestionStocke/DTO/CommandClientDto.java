package GestionStocke.DTO;

import java.time.Instant;
import java.util.List;

import GestionStocke.entity.CommandeClient;
import GestionStocke.entity.EtatCommande;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommandClientDto {
	private Integer id;
	private String code;
	private Instant dateCommande;
	private EtatCommande etatCommande;
	private ClientDto client;
	private List<LigneCommaneClientDto> ligneCommandeClients;
	private CreditDto credit;
	private double montant;
	private boolean contientCredit  ;

	  public static CommandClientDto fromEntity(CommandeClient commandeClient) {
	    if (commandeClient == null) {
	      return null;
	    }
	    return CommandClientDto.builder()
	        .id(commandeClient.getId())
	        .code(commandeClient.getCode())
	        .dateCommande(commandeClient.getDateCommande())
	        .etatCommande(commandeClient.getEtatCommande())
	        .client(ClientDto.fromEntity(commandeClient.getClient()))
	        .contientCredit(commandeClient.isContientCredit())
	        .montant(commandeClient.getMontant())
	        .build();

	  }

	  public static CommandeClient toEntity(CommandClientDto dto) {
	    if (dto == null) {
	      return null;
	    }
	    CommandeClient commandeClient = new CommandeClient();
	    commandeClient.setId(dto.getId());
	    commandeClient.setCode(dto.getCode());
	    commandeClient.setClient(ClientDto.toEntity(dto.getClient()));
	    commandeClient.setDateCommande(dto.getDateCommande());
	    commandeClient.setEtatCommande(dto.getEtatCommande());
	    commandeClient.setMontant(dto.getMontant());
	    commandeClient.setContientCredit(dto.isContientCredit());
	    
	    return commandeClient;
	  }

	  public boolean isCommandeLivree() {
	    return EtatCommande.LIVREE.equals(this.etatCommande);
	  }
}
