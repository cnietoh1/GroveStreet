package es.carlosnh.grovestreet.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visita_propiedad")
public class VisitaPropiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private String estado; // Ej: "programada", "completada"

    // Relación N:1 con Usuario (interesado)
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Relación N:1 con Propiedad
    @ManyToOne
    @JoinColumn(name = "id_propiedad", nullable = false)
    private Propiedad propiedad;
}
