package GestionStocke.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import GestionStocke.DTO.CategoryDto;

public class CategoryValidator {

	
	public static List<String> categoryValidate(CategoryDto categoryDto) {
		
		List<String> erros=new ArrayList<>();
		
		if(categoryDto == null || !StringUtils.hasLength(categoryDto.getCode())) {
			erros.add("Veuillez entre un code ");
		}
		
		return erros;
	}
}
