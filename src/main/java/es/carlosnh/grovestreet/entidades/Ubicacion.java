package es.carlosnh.grovestreet.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ubicacion")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name = "provincia", nullable = false)
    private String provincia;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "codigo_postal", nullable = false)
    private String codigoPostal;

    @OneToOne(mappedBy = "ubicacion")
    @JsonIgnore
    private Propiedad propiedad;

    // Constructor personalizado
    public Ubicacion(String ciudad, String provincia, String pais, String codigoPostal) {
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
    }
}
