package GestionStocke.entity;
import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

@Table(name="Movement_stocke")

public class MvtStk extends AuditModel {

	  @Column(name = "identreprise")
	  private Integer idEntreprise;
	private Instant dateMvt;
	
	
	@ManyToOne
	@JoinColumn(name="idarticle")
	private Article article;
	
	private Integer quantite;
	@Column(name = "sourcemvt")
	  @Enumerated(EnumType.STRING)
	  private SourceMvtStk sourceMvt;
	
	private TypeMvtStk typeMvtStock;
}
