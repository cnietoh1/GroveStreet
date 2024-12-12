package es.carlosnh.grovestreet.controladores.restapi;

import es.carlosnh.grovestreet.servicios.PropiedadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/propiedades")
@RequiredArgsConstructor
public class PropiedadController {

    private final PropiedadService propiedadService;


}
