package GestionStocke.entity;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)

@Table(name = "commandefournisseur")

public class CommandeFornisseur extends AuditModel {

	 @Column(name = "code")
	  private String code;

	  @Column(name = "datecommande")
	  private Instant dateCommande;

	  @Column(name = "etatcommande")
	  @Enumerated(EnumType.STRING)
	  private EtatCommande etatCommande;

	  @Column(name = "identreprise")
	  private Integer idEntreprise;

	  @ManyToOne
	  @JoinColumn(name = "idfournisseur")
	  private Fornisseur Fornisseur;

	  @OneToMany(mappedBy = "commandeFournisseur")
	  private List<LigneCommandeFornisseur> ligneCommandeFournisseurs;
}
