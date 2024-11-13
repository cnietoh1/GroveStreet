package es.carlosnh.grovestreet.servicios;

import es.carlosnh.grovestreet.entidades.VisitaPropiedad;
import es.carlosnh.grovestreet.repositorios.VisitaPropiedadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisitaPropiedadService {

    private final VisitaPropiedadRepository visitaPropiedadRepository;

    public List<VisitaPropiedad> obtenerTodas() {
        return visitaPropiedadRepository.findAll();
    }

    public VisitaPropiedad obtenerPorId(Long id) {
        return visitaPropiedadRepository.findById(id).orElse(null);
    }

    public VisitaPropiedad crear(VisitaPropiedad visitaPropiedad) {
        return visitaPropiedadRepository.save(visitaPropiedad);
    }

    public VisitaPropiedad actualizar(Long id, VisitaPropiedad visitaPropiedad) {
        if (visitaPropiedadRepository.existsById(id)) {
            visitaPropiedad.setId(id);
            return visitaPropiedadRepository.save(visitaPropiedad);
        }
        return null;
    }

    public boolean eliminar(Long id) {
        if (visitaPropiedadRepository.existsById(id)) {
            visitaPropiedadRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
