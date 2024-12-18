package es.carlosnh.grovestreet.controladores.restapi;

import es.carlosnh.grovestreet.entidades.Ubicacion;
import es.carlosnh.grovestreet.servicios.UbicacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ubicaciones")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @GetMapping
    public ResponseEntity<List<Ubicacion>> obtenerUbicaciones() {
        return ResponseEntity.ok(ubicacionService.obtenerTodasUbicaciones());
    }

}
