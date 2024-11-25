package GestionStocke.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)

@Entity

public class Roles extends AuditModel {
	private String roleName;
	
	 @ManyToOne
	  @JoinColumn(name = "idutilisateur")
	  private Utilisateur utilisateur;
	
}
