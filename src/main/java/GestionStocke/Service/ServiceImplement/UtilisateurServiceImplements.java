package GestionStocke.Service.ServiceImplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GestionStocke.DTO.UtilisateurDto;
import GestionStocke.Exception.ErrorCodes;
import GestionStocke.Exception.InvalidEntityException;
import GestionStocke.Service.UtilisateurService;
import GestionStocke.Validator.UtilisateurValidator;
import GestionStocke.entity.Utilisateur;
import GestionStocke.repostory.UtilisateurRepository;

@Service
public class UtilisateurServiceImplements implements UtilisateurService {

	private UtilisateurRepository utilisateurrepository;


	public UtilisateurServiceImplements(UtilisateurRepository utilisateurrepository) {
		super();
		this.utilisateurrepository = utilisateurrepository;
	}


	@Override
	public UtilisateurDto saveUtilisateur(UtilisateurDto utilisateur) {
		// TODO Auto-generated method stub
		 List<String> errors = UtilisateurValidator.utilisateurValidate(utilisateur);
		    if (!errors.isEmpty()) {
		      //log.error("Utilisateur is not valid {}", dto);
		      throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
		    }

			/*
			 * if(userAlreadyExists(dto.getEmail())) { throw new
			 * InvalidEntityException("Un autre utilisateur avec le meme email existe deja",
			 * ErrorCodes.UTILISATEUR_ALREADY_EXISTS, Collections.
			 * singletonList("Un autre utilisateur avec le meme email existe deja dans la BDD"
			 * )); }
			 * 
			 * 
			 * dto.setMoteDePasse(passwordEncoder.encode(dto.getMoteDePasse()));
			 */
		    return UtilisateurDto.fromEntity(
		        utilisateurrepository.save(
		            UtilisateurDto.toEntity(utilisateur)
		        )
		    );
	}


	@Override
	public List<UtilisateurDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UtilisateurDto findUtilisateurById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteUtilisateurById(Integer id) {
		if(!utilisateurrepository.existsById(id)){
		   throw new IllegalArgumentException("Utilisateur not found by id " + id )	;
		}
		utilisateurrepository.deleteById(id);
	}


	@Override
	public void deleteUtilisateur() {
		// TODO Auto-generated method stub

	}


	@Override
	public UtilisateurDto findUtilisateurByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
