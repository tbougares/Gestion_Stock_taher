package GestionStocke.DTO;

import GestionStocke.entity.Adress;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdressDto {


	private String Adreesse1;
	private String Adreesse2;
	
	private String Ville;
	
	private String codePostal;
	
	private String pays;
	
	public static AdressDto fromEntity(Adress adresse) {
	    if (adresse == null) {
	      return null;
	    }

	    return AdressDto.builder()
	        .Adreesse1(adresse.getAdreesse1())
	        .Adreesse2(adresse.getAdreesse1())
	        .codePostal(adresse.getCodePostal())
	        .Ville(adresse.getVille())
	        .pays(adresse.getPays())
	        .build();
	  }

	  public static Adress toEntity(AdressDto adresseDto) {
	    if (adresseDto == null) {
	      return null;
	    }
	    Adress adresse = new Adress();
	    adresse.setAdreesse1(adresseDto.getAdreesse1());
	    adresse.setAdreesse2(adresseDto.getAdreesse2());
	    adresse.setCodePostal(adresseDto.getCodePostal());
	    adresse.setVille(adresseDto.getVille());
	    adresse.setPays(adresseDto.getPays());
	    return adresse;
	  }
}
