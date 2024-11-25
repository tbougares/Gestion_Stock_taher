package GestionStocke.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import GestionStocke.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	private Integer id;
    private String  code;
	private String designation;
	private Integer idEntreprise;

	@JsonIgnore
	private List<ArticleDto> articles;


	public static CategoryDto fromEntity(Category category) {
		return CategoryDto.builder()
				.id(category.getId())
				.code(category.getCode())
				.designation(category.getDesignation())
				.idEntreprise(category.getIdEntreprise())

				.build();
				
	
	}
	public static Category toEntity (CategoryDto categoryDto) {
		Category category =new Category();
		category.setId(categoryDto.getId());
		category.setCode(categoryDto.getCode());
		category.setIdEntreprise(categoryDto.getIdEntreprise());

		category.setDesignation(categoryDto.getDesignation());
		
		return category;
	}
	
	
	
	
	/*
	private CategoryDto fromEntity(Category category) {
		if(category == null) {
			return null;
		}
		return CategoryDto.builder()
				.id(category.getId())
				.code(category.getCode())
				.designation(category.getDesignation())
				 
				.build();
	}*/
}
