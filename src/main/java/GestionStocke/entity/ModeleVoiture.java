package GestionStocke.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "modele_voiture")

@Entity
public class ModeleVoiture extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "nom_modele", nullable = false, unique = true)
    private String nomModele;

    @ManyToMany(mappedBy = "modelesVoiture")
    private Set<Article> articles = new HashSet<>();

}