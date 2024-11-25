package GestionStocke.DTO;


import java.util.Set;
import GestionStocke.entity.Credit;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreditDto {

	private Integer id;
	private double reste;
	private CommandClientDto commandeClient;

    public static CreditDto fromEntity(Credit credit) {
	    if (credit == null) {
	      return null;
	    }
	    return CreditDto.builder()
	        .id(credit.getId())
	        .reste(credit.getReste())
	        .commandeClient(CommandClientDto.fromEntity(credit.getCommandeClient()))

	        .build();

	  }

	  public static Credit toEntity(CreditDto dto) {
	    if (dto == null) {
	      return null;
	    }
	    Credit credit = new Credit();
	    credit.setId(dto.getId());
	    credit.setReste(dto.getReste());
	    credit.setCommandeClient(CommandClientDto.toEntity(dto.getCommandeClient()));

	    return credit;
	    
	  }

}
