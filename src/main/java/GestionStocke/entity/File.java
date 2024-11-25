package GestionStocke.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class File {
	@Id
	@GeneratedValue
private Long id;
	
	@Column(name="name")
	private String name;
	@Column(name="type")

	private String type;
	@Lob
	@Column(length=10000000, columnDefinition="longblob")
	private byte[] data;
	}
