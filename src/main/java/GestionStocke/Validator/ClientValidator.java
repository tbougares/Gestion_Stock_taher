package GestionStocke.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import GestionStocke.DTO.ClientDto;

public class ClientValidator {
	
	
	public static List<String>  clientValidate (ClientDto clientDto) {
		List<String> erros=new ArrayList<>();
		if(clientDto ==null) {
			erros.add("Veuillez entre le coordoneende client");
			return erros;
		}
		if(!StringUtils.hasLength(clientDto.getNom())) {
			erros.add("Tu dois entre un nom au client");
		}
		if(!StringUtils.hasLength(clientDto.getPhoto())) {
			erros.add("Tu dois entre un photo au client");
		}
		if(!(StringUtils.hasLength(clientDto.getPrenom()))) {
			erros.add("Tu dois entre le prenom au client");
		}
		if(!(StringUtils.hasLength(clientDto.getTel()))) {
			erros.add("Tu dois entre le téléphone de client");
		}
		if(!(StringUtils.hasLength(clientDto.getEmail()))) {
			erros.add("Tu dois entre le Email de client");
		}
		erros.addAll(AddresseValidator.addresseValidate(clientDto.getAdresse()));
		return erros;
	}

}
