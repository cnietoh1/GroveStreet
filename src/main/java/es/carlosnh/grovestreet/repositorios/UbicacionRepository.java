package es.carlosnh.grovestreet.repositorios;

import es.carlosnh.grovestreet.entidades.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {

    Optional<Ubicacion> findByCiudadAndProvinciaAndPaisAndCodigoPostal(String ciudad, String provincia, String pais, String codigoPostal);

    Optional<Ubicacion> findByCiudadAndCodigoPostalAndPaisAndProvincia(
            String ciudad,
            String codigoPostal,
            String pais,
            String provincia
    );
}
