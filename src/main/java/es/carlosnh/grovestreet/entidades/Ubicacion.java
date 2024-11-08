package es.carlosnh.grovestreet.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ubicacion")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String estado;  // O provincia/estado

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false)
    private String codigoPostal;

    // Relación 1:1 con Propiedad
    @OneToOne(mappedBy = "ubicacion")
    private Propiedad propiedad;
}
