package es.carlosnh.grovestreet.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaccion")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private String estado; // Ej: "completada", "pendiente"

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
