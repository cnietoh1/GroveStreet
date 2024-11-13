package es.carlosnh.grovestreet.entidades;

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
@Table(name = "imagen_propiedad")
public class ImagenPropiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url_imagen; // URL de la imagen

    // Relación N:1 con Propiedad
    @ManyToOne
    @JoinColumn(name = "id_propiedad", nullable = false)
    private Propiedad propiedad;
}
