package GestionStocke.Service;

import java.util.List;

import GestionStocke.DTO.CreditDto;

public interface CreditService {

	public CreditDto save(CreditDto creditDto);
	public List<CreditDto> findAll();
	public CreditDto findById(Integer id);
	public CreditDto update(CreditDto creditDto ,Integer id);
	public void deleteById(Integer id);
	public void deleteAll();
	public List<CreditDto> findAllByClientId(Integer clientId); 
}
