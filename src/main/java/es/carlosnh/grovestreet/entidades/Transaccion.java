package es.carlosnh.grovestreet.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaccion")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private Double cantidad;

    @Column(nullable = false)
    private String tipo_transaccion; // Ej: "completada", "pendiente"

    // Relación N:1 con Usuario (comprador)
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario comprador;

    // Relación N:1 con Propiedad
    @ManyToOne
    @JoinColumn(name = "id_propiedad", nullable = false)
    private Propiedad propiedad;

    // Relación N:1 con Usuario (vendedor)
    @ManyToOne
    @JoinColumn(name = "id_vendedor", nullable = false)
    private Usuario vendedor;
}
