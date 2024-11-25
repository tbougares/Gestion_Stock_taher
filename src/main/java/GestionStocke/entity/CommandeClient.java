package GestionStocke.entity;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class CommandeClient extends AuditModel {

		/**
	 * 
	 */
	    private static final long serialVersionUID = 1L;

		private String code;
		
		@Column(name = "date_commande")
		private Instant dateCommande;
		
		private EtatCommande etatCommande;
		
		private boolean status;
		
		private ModeDePaiement modeDePaiement;
		
		
		private double montant;
		
		private boolean contientCredit  ;

		
		@ManyToOne
		@JoinColumn(name="idClient")
		private Client client;
		
		
		@OneToMany(mappedBy = "commandeClient")
		private List<LigneCommandeClient> ligneCommandeClients=new ArrayList<LigneCommandeClient>();
		
		@OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "credit_id", referencedColumnName = "id")
	    private Credit credit;
	    
}
