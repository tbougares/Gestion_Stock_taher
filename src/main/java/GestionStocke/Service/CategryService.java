package GestionStocke.Service;

import java.util.List;

import GestionStocke.DTO.CategoryDto;

public interface CategryService {
	public CategoryDto saveCategory(CategoryDto categoryDto);
	public List<CategoryDto> findAllCategory();
	CategoryDto findById(Integer id);
	public void deleteCategoryById(Integer id);
	public void deleteAllCategory();
	public CategoryDto findByCode(String code);
	

}
