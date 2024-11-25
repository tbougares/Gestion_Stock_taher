package GestionStocke.DTO;


import java.time.LocalDate;
import java.util.Date;
import GestionStocke.entity.Tranch;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Builder
@Data
public class TranchDto {

	private Integer id;
	
	private double montantAPayee;
	private LocalDate dateDePaiement;
    private CreditDto credit;    
    public static TranchDto fromEntity(Tranch tranch) {
	    if (tranch == null) {
	      return null;
	    }
	    return TranchDto.builder()
	        .id(tranch.getId())
	        .montantAPayee(tranch.getMontantAPayee())
	        .dateDePaiement(tranch.getDateDePaiement())
	        .credit(CreditDto.fromEntity(tranch.getCredit()))
	       
	        .build();

	  }

	  public static Tranch toEntity(TranchDto dto) {
	    if (dto == null) {
	      return null;
	    }
	    Tranch tranch = new Tranch();
	    tranch.setId(dto.getId());
	    tranch.setMontantAPayee(dto.getMontantAPayee());
	    tranch.setDateDePaiement(dto.getDateDePaiement());

	    tranch.setCredit(CreditDto.toEntity(dto.getCredit()));

	    return tranch;
	  }


}
