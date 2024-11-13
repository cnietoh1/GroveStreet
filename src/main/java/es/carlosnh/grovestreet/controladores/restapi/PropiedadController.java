package es.carlosnh.grovestreet.controladores.restapi;

import es.carlosnh.grovestreet.entidades.Propiedad;
import es.carlosnh.grovestreet.servicios.PropiedadService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@Slf4j
@RestController
@RequestMapping("/api/propiedades")
public class PropiedadController {

    @Autowired
    private PropiedadService propiedadService;

    @GetMapping
    public List<Propiedad> obtenerTodas() {
        return propiedadService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propiedad> obtenerPorId(@PathVariable Long id) {
        Propiedad propiedad = propiedadService.obtenerPorId(id);
        return propiedad != null ? ResponseEntity.ok(propiedad) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Propiedad crearPropiedad(@RequestBody Propiedad propiedad) {
        return propiedadService.crear(propiedad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPropiedad(@PathVariable Long id) {
        boolean eliminado = propiedadService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
