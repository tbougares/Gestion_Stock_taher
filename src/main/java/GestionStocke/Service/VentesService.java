package GestionStocke.Service;

import java.util.List;
import java.util.Optional;

import GestionStocke.DTO.VentesDto;
import GestionStocke.entity.Ventes;

public interface VentesService {
	
	public VentesDto save(VentesDto dto);

	public VentesDto findById(Integer id);

	public VentesDto findByCode(String code);

	public List<VentesDto> findAll();

	public void delete(Integer id);

}
