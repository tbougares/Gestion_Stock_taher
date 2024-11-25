package GestionStocke.DTO;

import java.util.List;

import GestionStocke.entity.Client;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientDto {
	private Integer id;

	private String nom,prenom,photo,Tel;
	
	private String email;
	

	private AdressDto adresse;
	
	
	
	private List<CommandClientDto> commandeClients;

	private Integer idEntreprise;

	public static ClientDto fromEntity(Client client) {
		return ClientDto.builder()
				.id(client.getId())
				.nom(client.getNom())
				.prenom(client.getPrenom())
				.photo(client.getPhoto())
				.email(client.getEmail())
				.Tel(client.getTel())
				.adresse(AdressDto.fromEntity(client.getAdresse()))
				.idEntreprise(client.getIdEntreprise())

				
				.build();
				
	
	}
	public static Client toEntity (ClientDto clientDto) {
		Client client =new Client();
		client.setId(clientDto.getId());
		client.setNom(clientDto.getNom());
		client.setPrenom(clientDto.getPrenom());
		client.setPhoto(clientDto.getPhoto());
		client.setEmail(clientDto.getEmail());
		client.setIdEntreprise(clientDto.getIdEntreprise());

		client.setTel(clientDto.getTel());
		client.setAdresse(AdressDto.toEntity(clientDto.getAdresse()));

		
		return client;
	}

}
