package es.carlosnh.grovestreet.servicios;

import es.carlosnh.grovestreet.entidades.Ubicacion;
import es.carlosnh.grovestreet.repositorios.UbicacionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    public List<Ubicacion> obtenerTodasUbicaciones() {
        return ubicacionRepository.findAll();
    }

    public Optional<Ubicacion> buscarUbicacion(String ciudad, String provincia, String pais, String codigoPostal) {
        return ubicacionRepository.findByCiudadAndProvinciaAndPaisAndCodigoPostal(ciudad, provincia, pais, codigoPostal);
    }

    public Long obtenerIdUbicacion(Ubicacion ubicacion) {
        return ubicacionRepository
                .findByCiudadAndCodigoPostalAndPaisAndProvincia(
                        ubicacion.getCiudad(),
                        ubicacion.getCodigoPostal(),
                        ubicacion.getPais(),
                        ubicacion.getProvincia()
                )
                .map(Ubicacion::getId)
                .orElseThrow(() -> new EntityNotFoundException("Ubicación no encontrada"));
    }



}
