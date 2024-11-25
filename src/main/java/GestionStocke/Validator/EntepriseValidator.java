package GestionStocke.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import GestionStocke.DTO.EntrepriseDto;

public class EntepriseValidator {
	
	public static List<String>  enteprisseValidate (EntrepriseDto entrepriseDto) {
		List<String> erros=new ArrayList<>();
		if(entrepriseDto ==null) {
			erros.add("Veuillez entre le coordoneende client");
			return erros;
		}
		if(StringUtils.hasLength(entrepriseDto.getNom())) {
			erros.add("Tu dois entre un nom au entreprise");
		}
		if(StringUtils.hasLength(entrepriseDto.getPhoto())) {
			erros.add("Tu dois entre un photo au client");
		}
		if((StringUtils.hasLength(entrepriseDto.getCodeFiscal()))) {
			erros.add("Tu dois entre le code fiscal de entreprise");
		}
		if((StringUtils.hasLength(entrepriseDto.getNumTel()))) {
			erros.add("Tu dois entre le téléphone de enteprise");
		}
		if((StringUtils.hasLength(entrepriseDto.getEmail()))) {
			erros.add("Tu dois entre le Email de enteprise");
		}
		if((StringUtils.hasLength(entrepriseDto.getSteWeb()))) {
			erros.add("Tu dois entre le site de enteprise");
		}
		erros.addAll(AddresseValidator.addresseValidate(entrepriseDto.getAdresse()));
		return erros;
	}

}
