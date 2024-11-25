package GestionStocke.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import GestionStocke.DTO.CommandFornisseurDto;

public class CommandeFournisseurValidator {
	
	
	public static List<String>  commandeFournisseurValidate (CommandFornisseurDto commandfournisseurDto) {
		List<String> erros=new ArrayList<>();
		if(commandfournisseurDto ==null) {
			erros.add("Veuillez entre le coordoneende client");
			return erros;
		}
		if((commandfournisseurDto.getDateCommande())== null) {
			erros.add("Tu dois entre le date de commande");
		}
		if(!StringUtils.hasLength(commandfournisseurDto.getEtatCommande().toString())) {
			erros.add("Tu dois entre le etat de commande");
		}
		if((commandfournisseurDto.getFornisseur())== null || (commandfournisseurDto.getFornisseur().getId())== null) {
			erros.add("Tu dois entre le code de client");
		}
		return erros;
	}

}
