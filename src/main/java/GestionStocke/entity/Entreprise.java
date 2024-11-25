package GestionStocke.entity;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)

public class Entreprise extends AuditModel {
	
	@Column(name = "nom")
	  private String nom;

	  @Column(name = "description")
	  private String description;

	  @Embedded
	  private Adress adresse;

	  @Column(name = "codefiscal")
	  private String codeFiscal;

	  @Column(name = "photo")
	  private String photo;

	  @Column(name = "email")
	  private String email;

	  @Column(name = "numtel")
	  private String numTel;

	  @Column(name = "siteweb")
	  private String steWeb;


}
