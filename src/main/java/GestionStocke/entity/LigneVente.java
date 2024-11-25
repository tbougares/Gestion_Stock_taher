package GestionStocke.entity;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)

@Entity

public class LigneVente extends AuditModel {
	
	
	@ManyToOne
	@JoinColumn(name = "idvente")
	private Ventes vente;
	
	private BigDecimal quantite;
	
	private BigDecimal prixUniteur;

	  @Column(name = "identreprise")
	  private Integer idEntreprise;
	
	@ManyToOne
	@JoinColumn(name = "idArticle")
	private Article article;
}
