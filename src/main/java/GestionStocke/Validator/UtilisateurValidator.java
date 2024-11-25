package GestionStocke.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import GestionStocke.DTO.UtilisateurDto;

public class UtilisateurValidator {
	
	public static List<String>  utilisateurValidate (UtilisateurDto utilisateurDto) {
		List<String> erros=new ArrayList<>();
		if(utilisateurDto ==null) {
			erros.add("Veuillez entre le coordoneende fournisseur");
			return erros;
		}
		if(!StringUtils.hasLength(utilisateurDto.getNom())) {
			erros.add("Tu dois entre un nom au fournisseur");
		}
		if(!StringUtils.hasLength(utilisateurDto.getPhoto())) {
			erros.add("Tu dois entre un photo au fournisseur");
		}
		if((!StringUtils.hasLength(utilisateurDto.getPrenom()))) {
			erros.add("Tu dois entre le prenom au fournisseur");
		}
		if((!StringUtils.hasLength(utilisateurDto.getTel()))) {
			erros.add("Tu dois entre le téléphone de fournisseur");
		}
		if((!StringUtils.hasLength(utilisateurDto.getEmail()))) {
			erros.add("Tu dois entre le Email de fournisseur");
		}
		if((!StringUtils.hasLength(utilisateurDto.getMoteDePasse()))) {
			erros.add("Tu dois entre mot de passe");
		}
		if((!(utilisateurDto.getDateDeNaissance()== null))) {
			erros.add("Tu dois entre le date de naissence");
		}
		erros.addAll(AddresseValidator.addresseValidate(utilisateurDto.getAdresse()));
		return erros;
	}

}
