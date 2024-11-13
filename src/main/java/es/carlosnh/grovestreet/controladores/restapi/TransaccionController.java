package es.carlosnh.grovestreet.controladores.restapi;

import es.carlosnh.grovestreet.entidades.Transaccion;
import es.carlosnh.grovestreet.servicios.TransaccionService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@Slf4j
@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping
    public List<Transaccion> obtenerTodas() {
        return transaccionService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> obtenerPorId(@PathVariable Long id) {
        Transaccion transaccion = transaccionService.obtenerPorId(id);
        return transaccion != null ? ResponseEntity.ok(transaccion) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Transaccion crearTransaccion(@RequestBody Transaccion transaccion) {
        return transaccionService.crear(transaccion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaccion> actualizarTransaccion(@PathVariable Long id, @RequestBody Transaccion transaccion) {
        Transaccion actualizada = transaccionService.actualizar(id, transaccion);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTransaccion(@PathVariable Long id) {
        boolean eliminado = transaccionService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
