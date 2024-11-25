package GestionStocke.Service.ServiceImplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GestionStocke.DTO.FornisseurDto;
import GestionStocke.Exception.EntityNotFoundException;
import GestionStocke.Exception.ErrorCodes;
import GestionStocke.Exception.InvalidEntityException;
import GestionStocke.Exception.InvalidOperationException;
import GestionStocke.Service.FournisseurService;
import GestionStocke.Validator.FournisseurValidator;
import GestionStocke.entity.CommandeClient;
import GestionStocke.entity.Fornisseur;
import GestionStocke.repostory.CommandeFornisseurRepository;
import GestionStocke.repostory.FornisseurRepository;
import java.util.stream.Collectors;

@Service
public class FournisseurServiceImplements implements FournisseurService {
	
	
	private FornisseurRepository fournisseurRepository;
	  private CommandeFornisseurRepository commandeFournisseurRepository;

	  @Autowired
	  public FournisseurServiceImplements(FornisseurRepository fournisseurRepository,
			CommandeFornisseurRepository commandeFournisseurRepository) {
		super();
		this.fournisseurRepository = fournisseurRepository;
		this.commandeFournisseurRepository = commandeFournisseurRepository;
	}
	  @Override
		public FornisseurDto save(FornisseurDto fornisseurDto) {
			// TODO Auto-generated method stub
		  List<String> errors = FournisseurValidator.fournisseurValidate(fornisseurDto);
		    if (!errors.isEmpty()) {
		      //log.error("Fournisseur is not valid {}", dto);
		      throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
		    }

		    return FornisseurDto.fromEntity(
		        fournisseurRepository.save(
		            FornisseurDto.toEntity(fornisseurDto)
		        )
		    );
		}
	@Override
	public List<FornisseurDto> findAll() {
		// TODO Auto-generated method stub
		return fournisseurRepository.findAll().stream()
		        .map(FornisseurDto::fromEntity)
		        .collect(Collectors.toList());
	}


	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
		      //log.error("Fournisseur ID is null");
		      return;
		    }
			
			/*
			  List<CommandeClient> commandeFournisseur =commandeFournisseurRepository.findById(id); 
			  if (!commandeFournisseur.isEmpty()) { throw new
			  InvalidOperationException("Impossible de supprimer un fournisseur qui a deja des commandes"
			 , ErrorCodes.FOURNISSEUR_ALREADY_IN_USE); }
			 */
		    fournisseurRepository.deleteById(id);
		  }

	@Override
	public FornisseurDto findById(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
		      //log.error("Fournisseur ID is null");
		      return null;
		    }
		    return fournisseurRepository.findById(id)
		        .map(FornisseurDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		                "Aucun fournisseur avec l'ID = " + id + " n' ete trouve dans la BDD",
		                ErrorCodes.FOURNISSEUR_NOT_FOUND));
	}
	
}
