package GestionStocke.Service.ServiceImplement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GestionStocke.DTO.ClientDto;
import GestionStocke.Exception.EntityNotFoundException;
import GestionStocke.Exception.ErrorCodes;
import GestionStocke.Exception.InvalidEntityException;
import GestionStocke.Exception.InvalidOperationException;
import GestionStocke.Service.ClienService;
import GestionStocke.Validator.ClientValidator;
import GestionStocke.entity.Client;
import GestionStocke.entity.CommandeClient;
import GestionStocke.repostory.ClientRepository;
import GestionStocke.repostory.CommandeClientRepository;

@Service
public class ClientServiceImplements implements ClienService {

	private ClientRepository clientRepository;
	  private CommandeClientRepository commandeClientRepository;

	  @Autowired
	  public void ClientServiceImpl(ClientRepository clientRepository, CommandeClientRepository commandeClientRepository) {
	    this.clientRepository = clientRepository;
	    this.commandeClientRepository = commandeClientRepository;
	  }

	@Override
	public ClientDto saveClient(ClientDto clientDto) {
		// TODO Auto-generated method stub
		List<String> errors =ClientValidator.clientValidate(clientDto);
		if(!errors.isEmpty()) {
			throw new InvalidEntityException("le client n est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
		}
		return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(clientDto)));
	}

	@Override
	public List<ClientDto> findAllClient() {
		// TODO Auto-generated method stub
		return clientRepository.findAll().stream()
				.map(ClientDto::fromEntity)
				.collect(Collectors.toList());
	}

	

	@Override
	public void deleteClientById(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
		      //log.error("Client ID is null");
		      return;
		    }
		    List<CommandeClient> commandeClients = commandeClientRepository.findAllByClientId(id);
		    if (!commandeClients.isEmpty()) {
		      throw new InvalidOperationException("Impossible de supprimer un client qui a deja des commande clients",
		          ErrorCodes.CLIENT_ALREADY_IN_USE);
		    }
		    clientRepository.deleteById(id);
		  }
	

	@Override
	public void deleteAllClient() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClientDto findClientById(Integer id) {
		// TODO Auto-generated method stub
		 if (id == null) {
		      //log.error("Client ID is null");
		      return null;
		    }
		    return clientRepository.findById(id)
		        .map(ClientDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucun Client avec l'ID = " + id + " n' ete trouve dans la BDD",
		            ErrorCodes.CLIENT_NOT_FOUND)
		        );
	}
	
}
