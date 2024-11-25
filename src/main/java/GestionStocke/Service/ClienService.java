package GestionStocke.Service;

import java.util.List;

import GestionStocke.DTO.ClientDto;

public interface ClienService {
	public ClientDto saveClient(ClientDto clientDto);
	public List<ClientDto> findAllClient();
	public ClientDto findClientById(Integer id);
	public void deleteClientById(Integer id);
	public void deleteAllClient();
}
