package GestionStocke.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

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
@Entity
@EqualsAndHashCode(callSuper = true)

public class Tranch extends AuditModel {

	private static final long serialVersionUID = 1L;

	@Column(name = "montantAPayee")
	private double montantAPayee;
	
		
	@Column(name = "dateDePaiement")
	private LocalDate dateDePaiement;
	
	@ManyToOne
    @JoinColumn(name="credit_id", nullable=false)
    private Credit credit;
}
