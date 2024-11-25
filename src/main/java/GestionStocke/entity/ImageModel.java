package GestionStocke.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ImageModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id ;
    private String name ;
    private String type ;

    @Column(length=500000000)
    private byte[] picByte;

    private String  userId;

    // @JsonIgnore // Ignorer le champ "file"
    // private MultipartFile file;

    // abonnement
    public ImageModel(String name, String type, byte[] picByte ) {
        super();
        this.name = name;
        this.type = type;
        this.picByte = picByte;

    }

    // user
    public ImageModel(String name, String type, byte[] picByte , String userId ) {
        super();
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.userId=userId ;

    }







}
