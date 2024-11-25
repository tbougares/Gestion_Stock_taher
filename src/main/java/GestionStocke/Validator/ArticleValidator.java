package GestionStocke.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import GestionStocke.DTO.ArticleDto;

public class ArticleValidator {
	
	public static List<String>  articleValidate (ArticleDto articleDto) {
		List<String> erros=new ArrayList<>();
		if(articleDto ==null) {
			erros.add("Veuillez entre le coordoneende article");
			return erros;
		}
		if(!StringUtils.hasLength(articleDto.getDesignation())) {
			erros.add("Tu dois entre un designation au code");
		}
		if(!StringUtils.hasLength(articleDto.getPhoto())) {
			erros.add("Tu dois entre un photo");
		}
		if((articleDto.getPrixUnitaire())==null) {
			erros.add("Tu dois entre le prix unitaire");
		}
		if((articleDto.getTauxTva())==null) {
			erros.add("Tu dois entre un Taux Tva");
		}
		if((articleDto.getCategory())==null || articleDto.getCategory().getId() == null) {
			erros.add("Tu dois selectionner un Category");
		}
		
		return erros;
	}

}
