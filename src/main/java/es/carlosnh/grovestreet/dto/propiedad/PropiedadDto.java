package es.carlosnh.grovestreet.dto.propiedad;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropiedadDto {
    private Long id;
    private String titulo;
    private String descripcion;
    private Double precio;
    private Integer habitaciones;
    private Integer banos;
    private Double metrosCuadrados;
    private String tipo;
    private Long idUsuario;
}
