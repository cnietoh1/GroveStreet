package es.carlosnh.grovestreet.servicios;

import es.carlosnh.grovestreet.repositorios.ImagenPropiedadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImagenPropiedadService {

    private final ImagenPropiedadRepository imagenPropiedadRepository;
}
