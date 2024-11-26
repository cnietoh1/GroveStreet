package es.carlosnh.grovestreet.controladores.restapi;

import es.carlosnh.grovestreet.dto.propiedad.PropiedadDto;
import es.carlosnh.grovestreet.entidades.Propiedad;
import es.carlosnh.grovestreet.servicios.PropiedadService;
import es.carlosnh.grovestreet.dto.propiedad.PropiedadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/propiedades")
@RequiredArgsConstructor
public class PropiedadController {

    private final PropiedadService propiedadService;
    private final PropiedadMapper propiedadMapper;

    @GetMapping("/{id}")
    public ResponseEntity<PropiedadDto> obtenerPorId(@PathVariable Long id) {
        Propiedad propiedad = propiedadService.obtenerPorId(id);
        if (propiedad == null) {
            return ResponseEntity.notFound().build();
        }
        PropiedadDto propiedadDto = propiedadMapper.toDto(propiedad);
        return ResponseEntity.ok(propiedadDto);
    }

    @GetMapping
    public ResponseEntity<List<PropiedadDto>> obtenerTodas() {
        List<PropiedadDto> propiedades = propiedadService.obtenerTodas().stream()
                .map(propiedadMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(propiedades);
    }

    @PostMapping
    public ResponseEntity<PropiedadDto> crearPropiedad(@RequestBody PropiedadDto propiedadDto) {
        Propiedad propiedad = propiedadMapper.toEntity(propiedadDto);
        Propiedad nuevaPropiedad = propiedadService.crear(propiedad);
        PropiedadDto nuevaPropiedadDto = propiedadMapper.toDto(nuevaPropiedad);
        return ResponseEntity.ok(nuevaPropiedadDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPropiedad(@PathVariable Long id) {
        boolean eliminado = propiedadService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
