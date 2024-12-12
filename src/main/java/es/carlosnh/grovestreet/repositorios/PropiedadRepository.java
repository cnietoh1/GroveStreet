package es.carlosnh.grovestreet.repositorios;

import es.carlosnh.grovestreet.entidades.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {

    // Buscar por ciudad dentro de la relación Ubicacion
    List<Propiedad> findByUbicacionCiudadContainingIgnoreCase(String ciudad);

    // Buscar por provincia dentro de la relación Ubicacion
    List<Propiedad> findByUbicacionProvinciaContainingIgnoreCase(String provincia);

    // Buscar por ciudad y provincia dentro de la relación Ubicacion
    List<Propiedad> findByUbicacionCiudadAndUbicacionProvincia(String ciudad, String provincia);
}
