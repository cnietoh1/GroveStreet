package es.carlosnh.grovestreet.controladores.restapi;

import es.carlosnh.grovestreet.entidades.VisitaPropiedad;
import es.carlosnh.grovestreet.servicios.VisitaPropiedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitas")
public class VisitaPropiedadController {

    @Autowired
    private VisitaPropiedadService visitaPropiedadService;

    @GetMapping
    public List<VisitaPropiedad> obtenerTodas() {
        return visitaPropiedadService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitaPropiedad> obtenerPorId(@PathVariable Long id) {
        VisitaPropiedad visita = visitaPropiedadService.obtenerPorId(id);
        return visita != null ? ResponseEntity.ok(visita) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public VisitaPropiedad agendarVisita(@RequestBody VisitaPropiedad visita) {
        return visitaPropiedadService.crear(visita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarVisita(@PathVariable Long id) {
        boolean eliminado = visitaPropiedadService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
