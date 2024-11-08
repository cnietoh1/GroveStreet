package es.carlosnh.grovestreet.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "propiedad")
public class Propiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer habitaciones;

    @Column(nullable = false)
    private Integer banos;

    @Column(nullable = false)
    private Double metrosCuadrados;

    @Column(nullable = false)
    private String tipo; // Ej: "venta", "alquiler"

    // Relación con Usuario (muchas propiedades pertenecen a un usuario)
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Relación 1:N con Ubicacion
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ubicacion", referencedColumnName = "id")
    private Ubicacion ubicacion;

    // Relación 1:N con Imagenes
    @OneToMany(mappedBy = "propiedad", cascade = CascadeType.ALL)
    private List<ImagenPropiedad> imagenes;
}
