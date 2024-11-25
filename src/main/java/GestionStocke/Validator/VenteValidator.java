package GestionStocke.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import GestionStocke.DTO.VentesDto;

public class VenteValidator {
	
	public static List<String>  venteValidate (VentesDto venteDto) {
		List<String> erros=new ArrayList<>();
		if(venteDto ==null) {
			erros.add("Veuillez entre le coordoneende fournisseur");
			return erros;
		}
		if(!StringUtils.hasLength(venteDto.getDateVente().toString())) {
			erros.add("Tu dois entre un date de vente");
		}
		if(!StringUtils.hasLength(venteDto.getCode())) {
			erros.add("Tu dois entre un code");
		}
		
		return erros;
	}

}
