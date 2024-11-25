package GestionStocke.Service;

import java.util.List;
import java.util.Optional;

import GestionStocke.DTO.UtilisateurDto;

public interface UtilisateurService {
	public UtilisateurDto saveUtilisateur(UtilisateurDto utilisateur);
	public List<UtilisateurDto> findAll();
	public UtilisateurDto findUtilisateurById(Integer id);
	public void deleteUtilisateurById(Integer id);
	public void deleteUtilisateur();
	public UtilisateurDto findUtilisateurByEmail(String email);
	
}
