package GestionStocke.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)

@Entity
public class Client extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom, prenom, photo, Tel;
	@Email
	@NotBlank
	private String email;

	private Integer code_barre;

	private TypeClient typeClient;
	@Embedded
	private Adress adresse;

	@Column(name = "identreprise")
	private Integer idEntreprise;

	@OneToMany(mappedBy = "client")
	private List<CommandeClient> commandeClients;
	
	

}
