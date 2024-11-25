package GestionStocke.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

public class Ventes extends AuditModel {

	private String code;

	private Instant dateVente;

	@Column(name = "identreprise")
	private Integer idEntreprise;

	private String commenntaier;

	@OneToMany(mappedBy = "vente")
	private List<LigneVente> LigneVentes = new ArrayList<>();
}
