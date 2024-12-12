package es.carlosnh.grovestreet.controladores.restapi;

import es.carlosnh.grovestreet.entidades.Propiedad;
import es.carlosnh.grovestreet.servicios.PropiedadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {

    private final PropiedadService propiedadService;

    @GetMapping("/api/propiedades")
    public List<Propiedad> buscarPropiedades(
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) String provincia) {
        return propiedadService.buscarPorCiudadYProvincia(ciudad, provincia);
    }
}
