package GestionStocke.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import GestionStocke.DTO.AdressDto;

public class AddresseValidator {
	
	
	public static List<String>  addresseValidate (AdressDto addressDto) {
		List<String> erros=new ArrayList<>();
		
		if(addressDto ==null) {
			erros.add("Veuillez entre le coordoneende adresse");
			return erros;
		}
		if(!StringUtils.hasLength(addressDto.getAdreesse1())) {
			erros.add("Tu dois entre un addresss");
		}
		if(!StringUtils.hasLength(addressDto.getVille())) {
			erros.add("Tu dois entre un ville");
		}
		if(!StringUtils.hasLength(addressDto.getPays())) {
			erros.add("Tu dois entre un pays");
		}
		if(!StringUtils.hasLength(addressDto.getCodePostal())) {
			erros.add("Tu dois entre un code Postal");
		}
		

		
		return erros;
	}
}
