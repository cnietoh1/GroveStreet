package es.carlosnh.grovestreet.controladores.restapi;

import es.carlosnh.grovestreet.entidades.Propiedad;
import es.carlosnh.grovestreet.entidades.TipoContrato;
import es.carlosnh.grovestreet.entidades.TipoPropiedad;
import es.carlosnh.grovestreet.entidades.Ubicacion;
import es.carlosnh.grovestreet.servicios.PropiedadService;
import es.carlosnh.grovestreet.servicios.UbicacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/propiedades")
public class PropiedadController {

    private final PropiedadService propiedadService;
    private final UbicacionService ubicacionService;

    @GetMapping("/filtrar")
    public ResponseEntity<List<Propiedad>> filtrarPropiedades(
            @RequestParam(required = false) String tipoContrato,
            @RequestParam(required = false) String tipoPropiedad,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax,
            @RequestParam(required = false) Integer habitaciones,
            @RequestParam(required = false) Integer banos,
            @RequestParam(required = false) Double metrosCuadrados,
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) String provincia,
            @RequestParam(required = false) String pais,
            @RequestParam(required = false) String codigoPostal) {

        List<Propiedad> propiedades = propiedadService.filtrarPropiedades(
                tipoContrato, tipoPropiedad, precioMin, precioMax, habitaciones, banos,
                metrosCuadrados, ciudad, provincia, pais, codigoPostal);
        return ResponseEntity.ok(propiedades);
    }

    @GetMapping("/list/usuario={username}")
    public ResponseEntity<List<Propiedad>> listarPropiedadesPorUsuario(@PathVariable String username) {
        List<Propiedad> propiedades = propiedadService.obtenerPropiedadesPorUsername(username);
        if (propiedades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(propiedades);
    }

    @PostMapping("/new")
    public ResponseEntity<Propiedad> crearPropiedad(
            @RequestParam("titulo") String titulo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("direccion") String direccion,
            @RequestParam("precio") Double precio,
            @RequestParam("habitaciones") Integer habitaciones,
            @RequestParam("banos") Integer banos,
            @RequestParam("metrosCuadrados") Double metrosCuadrados,
            @RequestParam("tipoContrato") TipoContrato tipoContrato,
            @RequestParam("tipoPropiedad") TipoPropiedad tipoPropiedad,
            @RequestParam("ciudad") String ciudad,
            @RequestParam("provincia") String provincia,
            @RequestParam("pais") String pais,
            @RequestParam("codigoPostal") String codigoPostal,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen) {

        log.info("Datos recibidos:");
        log.info("Titulo: {}", titulo);
        log.info("Descripcion: {}", descripcion);
        log.info("Direccion: {}", direccion);
        log.info("Precio: {}", precio);
        log.info("Habitaciones: {}", habitaciones);
        log.info("Banos: {}", banos);
        log.info("Metros cuadrados: {}", metrosCuadrados);
        log.info("TipoContrato: {}", tipoContrato);
        log.info("TipoPropiedad: {}", tipoPropiedad);
        log.info("Ciudad: {}", ciudad);
        log.info("Provincia: {}", provincia);
        log.info("Pais: {}", pais);
        log.info("Codigo Postal: {}", codigoPostal);
        log.info("Imagen: {}", imagen != null ? imagen.getOriginalFilename() : "No subida");

        try {
            // Buscar o crear la ubicación correspondiente
            Ubicacion ubicacion = ubicacionService.buscarUbicacion(ciudad, provincia, pais, codigoPostal)
                    .orElseThrow(() -> new IllegalArgumentException("Ubicación no encontrada"));

            // Crear la nueva propiedad
            Propiedad propiedad = new Propiedad();
            propiedad.setTitulo(titulo);
            propiedad.setDescripcion(descripcion);
            propiedad.setDireccion(direccion);
            propiedad.setPrecio(precio);
            propiedad.setHabitaciones(habitaciones);
            propiedad.setBanos(banos);
            propiedad.setMetrosCuadrados(metrosCuadrados);
            propiedad.setTipoContrato(tipoContrato);
            propiedad.setTipoPropiedad(tipoPropiedad);
            propiedad.setUbicacion(ubicacion); // Asignar el objeto Ubicacion

            // Procesar la imagen si existe
            if (imagen != null && !imagen.isEmpty()) {
                String imagenUrl = propiedadService.guardarImagen(imagen);
                propiedad.setImagenUrl(imagenUrl);
            }

            // Guardar la propiedad
            Propiedad nuevaPropiedad = propiedadService.guardarPropiedad(propiedad);
            return ResponseEntity.ok(nuevaPropiedad);

        } catch (Exception e) {
            log.error("Error al crear propiedad", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Propiedad> actualizarPropiedad(
            @PathVariable Long id,
            @RequestParam("titulo") String titulo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("direccion") String direccion,
            @RequestParam("precio") Double precio,
            @RequestParam("habitaciones") Integer habitaciones,
            @RequestParam("banos") Integer banos,
            @RequestParam("metrosCuadrados") Double metrosCuadrados,
            @RequestParam("tipoContrato") TipoContrato tipoContrato,
            @RequestParam("tipoPropiedad") TipoPropiedad tipoPropiedad,
            @RequestParam("ciudad") String ciudad,
            @RequestParam("provincia") String provincia,
            @RequestParam("pais") String pais,
            @RequestParam("codigoPostal") String codigoPostal,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen) {

        try {
            Propiedad propiedad = propiedadService.obtenerPorId(id);
            if (propiedad == null) {
                return ResponseEntity.notFound().build();
            }

            // Buscar el ID de la ubicación existente
            Ubicacion ubicacion = new Ubicacion(ciudad, provincia, pais, codigoPostal);
            Long idUbicacion = ubicacionService.obtenerIdUbicacion(ubicacion);

            // Actualizar propiedad
            propiedad.setTitulo(titulo);
            propiedad.setDescripcion(descripcion);
            propiedad.setDireccion(direccion);
            propiedad.setPrecio(precio);
            propiedad.setHabitaciones(habitaciones);
            propiedad.setBanos(banos);
            propiedad.setMetrosCuadrados(metrosCuadrados);
            propiedad.setTipoContrato(tipoContrato);
            propiedad.setTipoPropiedad(tipoPropiedad);
            propiedad.setUbicacion(ubicacion);

            // Actualizar imagen si se sube una nueva
            if (imagen != null && !imagen.isEmpty()) {
                String imagenUrl = propiedadService.guardarImagen(imagen);
                propiedad.setImagenUrl(imagenUrl);
            }

            Propiedad propiedadActualizada = propiedadService.guardarPropiedad(propiedad);
            return ResponseEntity.ok(propiedadActualizada);

        } catch (Exception e) {
            log.error("Error al actualizar propiedad", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarPropiedad(@PathVariable Long id) {
        boolean eliminada = propiedadService.eliminar(id);
        if (eliminada) {
            log.info("Propiedad con ID {} eliminada correctamente", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("No se encontró la propiedad con ID {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
