package GestionStocke.Validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import GestionStocke.DTO.MvtStkDto;

public class MvskValidator {
	
	
	public static List<String>  mvskValidate (MvtStkDto mvskDto) {
		List<String> errors = new ArrayList<>();
	    if (mvskDto == null) {
	      errors.add("Veuillez renseigner la date du mouvenent");
	      errors.add("Veuillez renseigner la quantite du mouvenent");
	      errors.add("Veuillez renseigner l'article");
	      errors.add("Veuillez renseigner le type du mouvement");

	      return errors;
	    }
	    if (mvskDto.getDateMvt() == null) {
	      errors.add("Veuillez renseigner la date du mouvenent");
	    }
		if (mvskDto.getQuantite() == null || mvskDto.getQuantite().compareTo(0) == 0) {
	      errors.add("Veuillez renseigner la quantite du mouvenent");
	    }
	    if (mvskDto.getArticle() == null || mvskDto.getArticle().getId() == null) {
	      errors.add("Veuillez renseigner l'article");
	    }
	    if (!StringUtils.hasLength(mvskDto.getTypeMvtStock().name())) {
	      errors.add("Veuillez renseigner le type du mouvement");
	    }

	    return errors;
	}

}
