package es.carlosnh.grovestreet.servicios;

import es.carlosnh.grovestreet.dto.propiedad.PropiedadDto;
import es.carlosnh.grovestreet.dto.propiedad.PropiedadMapper;
import es.carlosnh.grovestreet.entidades.Propiedad;
import es.carlosnh.grovestreet.entidades.TipoContrato;
import es.carlosnh.grovestreet.entidades.TipoPropiedad;
import es.carlosnh.grovestreet.repositorios.PropiedadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropiedadService {

    private final PropiedadRepository propiedadRepository;
    private final PropiedadMapper propiedadMapper;
    private static final String UPLOAD_DIR = "uploads/";

    public List<PropiedadDto> obtenerPropiedades() {
        return propiedadRepository.findAll()
                .stream()
                .map(propiedadMapper::toDto)
                .toList();
    }

    public List<Propiedad> obtenerPropiedadesPorUsername(String username) {
        return propiedadRepository.findByUsuarioUsername(username);
    }

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

    public List<Propiedad> filtrarPropiedades(
            String tipoContrato, String tipoPropiedad, Double precioMin, Double precioMax,
            Integer habitaciones, Integer banos, Double metrosCuadrados, String ciudad,
            String provincia, String pais, String codigoPostal) {

        // Convertir tipoContrato de String a TipoContrato (enum)
        TipoContrato contrato = null;
        if (tipoContrato != null && !tipoContrato.isEmpty()) {
            try {
                contrato = TipoContrato.valueOf(tipoContrato.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Tipo de contrato inválido: " + tipoContrato, e);
            }
        }

        // Convertir tipoPropiedad de String a TipoPropiedad (enum)
        TipoPropiedad propiedad = null;
        if (tipoPropiedad != null && !tipoPropiedad.isEmpty()) {
            try {
                propiedad = TipoPropiedad.valueOf(tipoPropiedad.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Tipo de propiedad inválido: " + tipoPropiedad, e);
            }
        }

        return propiedadRepository.findWithFilters(
                contrato, propiedad, precioMin, precioMax, habitaciones,
                banos, metrosCuadrados, ciudad, provincia, pais, codigoPostal);
    }

    public Propiedad guardarPropiedadConImagen(Propiedad propiedad, MultipartFile imagen) {
        if (imagen != null && !imagen.isEmpty()) {
            String imagenUrl = null;
            try {
                imagenUrl = guardarImagen(imagen);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            propiedad.setImagenUrl(imagenUrl);
        }
        return propiedadRepository.save(propiedad);
    }

    public String guardarImagen(MultipartFile imagen) throws IOException {
        if (imagen == null || imagen.isEmpty()) {
            return null;
        }

        String directorio = "uploads/";
        String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
        Path ruta = Paths.get(directorio, nombreArchivo);

        Files.createDirectories(ruta.getParent());
        Files.write(ruta, imagen.getBytes());

        return "/uploads/" + nombreArchivo;
    }

    public Propiedad obtenerPorId(Long id) {
        return propiedadRepository.findById(id).orElse(null);
    }

    public Propiedad guardarPropiedad(Propiedad propiedad) {
        return propiedadRepository.save(propiedad);
    }

    public Propiedad actualizarPropiedad(Long id, Propiedad propiedadActualizada) {
        return propiedadRepository.findById(id).map(propiedad -> {
            propiedad.setTitulo(propiedadActualizada.getTitulo());
            propiedad.setDescripcion(propiedadActualizada.getDescripcion());
            propiedad.setDireccion(propiedadActualizada.getDireccion());
            propiedad.setPrecio(propiedadActualizada.getPrecio());
            propiedad.setHabitaciones(propiedadActualizada.getHabitaciones());
            propiedad.setBanos(propiedadActualizada.getBanos());
            propiedad.setMetrosCuadrados(propiedadActualizada.getMetrosCuadrados());
            propiedad.setTipoPropiedad(propiedadActualizada.getTipoPropiedad());
            propiedad.setTipoContrato(propiedadActualizada.getTipoContrato());
            return propiedadRepository.save(propiedad);
        }).orElse(null);
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
