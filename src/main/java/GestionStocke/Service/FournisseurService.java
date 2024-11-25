package GestionStocke.Service;

import java.util.List;

import GestionStocke.DTO.FornisseurDto;

public interface FournisseurService {
	FornisseurDto save(FornisseurDto dto);

	FornisseurDto findById(Integer id);

	  List<FornisseurDto> findAll();

	  void delete(Integer id);
}
