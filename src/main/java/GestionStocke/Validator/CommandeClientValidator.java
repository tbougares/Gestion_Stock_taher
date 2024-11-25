package GestionStocke.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import GestionStocke.DTO.CommandClientDto;

public class CommandeClientValidator {
	
	
	public static List<String>  commandeClientValidate (CommandClientDto commandClientDto) {
		List<String> erros=new ArrayList<>();
		if(commandClientDto ==null) {
			erros.add("Veuillez entre le coordoneende client");
			return erros;
		}
		if((commandClientDto.getDateCommande())== null) {
			erros.add("Tu dois entre le date de commande");
		}
		if(!StringUtils.hasLength(commandClientDto.getEtatCommande().toString())) {
			erros.add("Tu dois entre le etat de commande");
		}
		if((commandClientDto.getClient())== null || (commandClientDto.getClient().getId())== null) {
			erros.add("Tu dois entre le code de client");
		}
		return erros;
	}

}
