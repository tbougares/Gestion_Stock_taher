package GestionStocke.entity;
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

public class Category extends AuditModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */


	private String  code;
	
	
	private String designation;

	  @Column(name = "identreprise")
	  private Integer idEntreprise;
	
	@OneToMany(mappedBy = "category")
	private List<Article> articles;
}
