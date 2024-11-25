package GestionStocke.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import GestionStocke.DTO.FornisseurDto;

public class FournisseurValidator {
	
	public static List<String>  fournisseurValidate (FornisseurDto fournisseurDto) {
		List<String> erros=new ArrayList<>();
		if(fournisseurDto ==null) {
			erros.add("Veuillez entre le coordoneende fournisseur");
			return erros;
		}
		if(!StringUtils.hasLength(fournisseurDto.getNom())) {
			erros.add("Tu dois entre un nom au fournisseur");
		}
		if(!StringUtils.hasLength(fournisseurDto.getPhoto())) {
			erros.add("Tu dois entre un photo au fournisseur");
		}
		if((!StringUtils.hasLength(fournisseurDto.getPrenom()))) {
			erros.add("Tu dois entre le prenom au fournisseur");
		}
		if((!StringUtils.hasLength(fournisseurDto.getTel()))) {
			erros.add("Tu dois entre le téléphone de fournisseur");
		}
		if((!StringUtils.hasLength(fournisseurDto.getEmail()))) {
			erros.add("Tu dois entre le Email de fournisseur");
		}
		erros.addAll(AddresseValidator.addresseValidate(fournisseurDto.getAdresse()));
		return erros;
	}

}
