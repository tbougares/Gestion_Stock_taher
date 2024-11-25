package GestionStocke.DTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import GestionStocke.entity.Ventes;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VentesDto {

	private Integer id;
	
private String code;
	
	private Instant dateVente;
	
	private String commenntaier;
	
	private List<LigneVenteDto> LigneVentes;

	private Integer idEntreprise;

	  public static VentesDto fromEntity(Ventes vente) {
	    if (vente == null) {
	      return null;
	    }
	    return VentesDto.builder()
	        .id(vente.getId())
	        .code(vente.getCode())
	        .commenntaier(vente.getCommenntaier())
	        .idEntreprise(vente.getIdEntreprise())
	        .build();
	  }

	  public static Ventes toEntity(VentesDto dto) {
	    if (dto == null) {
	      return null;
	    }
	    Ventes ventes = new Ventes();
	    ventes.setId(dto.getId());
	    ventes.setCode(ventes.getCode());
	    ventes.setCommenntaier(dto.getCommenntaier());
	    ventes.setIdEntreprise(dto.getIdEntreprise());
	    return ventes;
	  }
	
}
