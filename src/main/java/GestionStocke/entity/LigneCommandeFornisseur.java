package GestionStocke.entity;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)


public class LigneCommandeFornisseur extends AuditModel {

	 @ManyToOne
	  @JoinColumn(name = "idarticle")
	  private Article article;

	  @ManyToOne
	  @JoinColumn(name = "idcommandefournisseur")
	  private CommandeFornisseur commandeFournisseur;

	  @Column(name = "quantite")
	  private Integer quantite;

	  @Column(name = "prixunitaire")
	  private BigDecimal prixUnitaire;

	  @Column(name = "identreprise")
	  private Integer idEntreprise;
}

