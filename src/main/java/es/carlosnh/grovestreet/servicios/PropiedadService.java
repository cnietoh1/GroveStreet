package es.carlosnh.grovestreet.servicios;

import es.carlosnh.grovestreet.entidades.Propiedad;
import es.carlosnh.grovestreet.repositorios.PropiedadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropiedadService {

    private final PropiedadRepository propiedadRepository;

    public List<Propiedad> obtenerTodas() {
        return propiedadRepository.findAll();
    }

    public Propiedad obtenerPorId(Long id) {
        return propiedadRepository.findById(id).orElse(null);
    }

    public Propiedad crear(Propiedad propiedad) {
        return propiedadRepository.save(propiedad);
    }

    public boolean eliminar(Long id) {
        if (propiedadRepository.existsById(id)) {
            propiedadRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
