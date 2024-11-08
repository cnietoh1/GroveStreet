package es.carlosnh.grovestreet.servicios;

import es.carlosnh.grovestreet.repositorios.VisitaPropiedadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisitaPropiedadService {

    private final VisitaPropiedadRepository visitaPropiedadRepository;
}
