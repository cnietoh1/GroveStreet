package es.carlosnh.grovestreet.servicios;

import es.carlosnh.grovestreet.dto.propiedad.PropiedadDto;
import es.carlosnh.grovestreet.dto.propiedad.PropiedadMapper;
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
    private final PropiedadMapper propiedadMapper;

    // Obtener todas las propiedades
    public List<PropiedadDto> obtenerPropiedades() {
        return propiedadRepository.findAll()
                .stream()
                .map(propiedadMapper::toDto)
                .toList();
    }

    // Buscar propiedades por ciudad y/o provincia
    public List<Propiedad> buscarPorCiudadYProvincia(String ciudad, String provincia) {
        if (ciudad != null && !ciudad.isEmpty() && provincia != null && !provincia.isEmpty()) {
            return propiedadRepository.findByUbicacionCiudadAndUbicacionProvincia(ciudad, provincia);
        } else if (ciudad != null && !ciudad.isEmpty()) {
            return propiedadRepository.findByUbicacionCiudadContainingIgnoreCase(ciudad);
        } else if (provincia != null && !provincia.isEmpty()) {
            return propiedadRepository.findByUbicacionProvinciaContainingIgnoreCase(provincia);
        } else {
            return propiedadRepository.findAll();
        }
    }

    // Obtener una propiedad por ID
    public Propiedad obtenerPorId(Long id) {
        return propiedadRepository.findById(id).orElse(null);
    }

    // Crear una nueva propiedad
    public Propiedad crear(Propiedad propiedad) {
        return propiedadRepository.save(propiedad);
    }

    // Eliminar una propiedad
    public boolean eliminar(Long id) {
        if (propiedadRepository.existsById(id)) {
            propiedadRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
