package GestionStocke.Service.ServiceImplement;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import GestionStocke.DTO.EntrepriseDto;
import GestionStocke.DTO.RolesDto;
import GestionStocke.DTO.UtilisateurDto;
import GestionStocke.Exception.EntityNotFoundException;
import GestionStocke.Exception.ErrorCodes;
import GestionStocke.Exception.InvalidEntityException;
import GestionStocke.Service.EntrepriseService;
import GestionStocke.Service.UtilisateurService;
import GestionStocke.Validator.EntepriseValidator;
import GestionStocke.repostory.EntrepriseRepository;
import GestionStocke.repostory.RolesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntepriseServiceImp implements EntrepriseService {
	
	
	 private EntrepriseRepository entrepriseRepository;
	  private UtilisateurService utilisateurService;
	  private RolesRepository rolesRepository;



	  @Override
	  public EntrepriseDto save(EntrepriseDto dto) {
	    List<String> errors = EntepriseValidator.enteprisseValidate(dto);
	    if (!errors.isEmpty()) {
	      log.error("Entreprise is not valid {}", dto);
	      throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
	    }
	    EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
	        entrepriseRepository.save(EntrepriseDto.toEntity(dto))
	    );

	    UtilisateurDto utilisateur = fromEntreprise(savedEntreprise);

	    UtilisateurDto savedUser = utilisateurService.saveUtilisateur(utilisateur);

	    RolesDto rolesDto = RolesDto.builder()
	        .roleName("ADMIN")
	        .utilisateur(savedUser)
	        .build();

	    rolesRepository.save(RolesDto.toEntity(rolesDto));

	    return  savedEntreprise;
	  }

	  private UtilisateurDto fromEntreprise(EntrepriseDto dto) {
	    return UtilisateurDto.builder()
	        .adresse(dto.getAdresse())
	        .nom(dto.getNom())
	        .prenom(dto.getCodeFiscal())
	        .email(dto.getEmail())
	        .entreprise(dto)
	        .dateDeNaissance(Instant.now())
	        .photo(dto.getPhoto())
	        .build();
	  }


	  @Override
	  public EntrepriseDto findById(Integer id) {
	    if (id == null) {
	      log.error("Entreprise ID is null");
	      return null;
	    }
	    return entrepriseRepository.findById(id)
	        .map(EntrepriseDto::fromEntity)
	        .orElseThrow(() -> new EntityNotFoundException(
	            "Aucune entreprise avec l'ID = " + id + " n' ete trouve dans la BDD",
	            ErrorCodes.ENTREPRISE_NOT_FOUND)
	        );
	  }

	  @Override
	  public List<EntrepriseDto> findAll() {
	    return entrepriseRepository.findAll().stream()
	        .map(EntrepriseDto::fromEntity)
	        .collect(Collectors.toList());
	  }

	  @Override
	  public void deleteById(Integer id) {
	    if (id == null) {
	      log.error("Entreprise ID is null");
	      return;
	    }
	    entrepriseRepository.deleteById(id);
	  }
	
}
