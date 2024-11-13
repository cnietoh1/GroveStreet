package es.carlosnh.grovestreet.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
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

    @JsonProperty("tipo_propiedad")
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
    @JsonIgnore
    private List<ImagenPropiedad> imagenes;
}
