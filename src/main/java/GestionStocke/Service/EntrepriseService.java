package GestionStocke.Service;

import java.util.List;
import java.util.Optional;

import GestionStocke.DTO.EntrepriseDto;

public interface EntrepriseService {

	public EntrepriseDto save(EntrepriseDto entrepriseDto);
	public List<EntrepriseDto> findAll();
	public EntrepriseDto findById(Integer id);
	public void deleteById(Integer id);
}
