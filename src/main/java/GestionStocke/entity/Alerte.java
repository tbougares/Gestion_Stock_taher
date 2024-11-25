package GestionStocke.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alerte {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id ;
      private String message ;
      private boolean isRead ;
      @ManyToOne
      @JsonIgnore
      @JoinColumn(name = "article_id")
      private Article article;



}
