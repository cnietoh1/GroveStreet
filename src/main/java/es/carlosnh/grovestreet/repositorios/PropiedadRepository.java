package es.carlosnh.grovestreet.repositorios;

import es.carlosnh.grovestreet.entidades.TipoContrato;
import es.carlosnh.grovestreet.entidades.Propiedad;
import es.carlosnh.grovestreet.entidades.TipoPropiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Long>, JpaSpecificationExecutor<Propiedad> {

    List<Propiedad> findByUsuarioUsername(String username);

    List<Propiedad> findByUbicacionCiudadContainingIgnoreCase(String ciudad);

    List<Propiedad> findByUbicacionProvinciaContainingIgnoreCase(String provincia);

    // Buscar por ciudad y provincia dentro de la relación Ubicacion
    List<Propiedad> findByUbicacionCiudadAndUbicacionProvincia(String ciudad, String provincia);

    @Query("SELECT p FROM Propiedad p JOIN p.ubicacion u WHERE " +
            "(:tipoContrato IS NULL OR p.tipoContrato = :tipoContrato) AND " +
            "(:tipoPropiedad IS NULL OR p.tipoPropiedad = :tipoPropiedad) AND " +
            "(:precioMin IS NULL OR p.precio >= :precioMin) AND " +
            "(:precioMax IS NULL OR p.precio <= :precioMax) AND " +
            "(:habitaciones IS NULL OR p.habitaciones = :habitaciones) AND " +
            "(:banos IS NULL OR p.banos = :banos) AND " +
            "(:metrosCuadrados IS NULL OR p.metrosCuadrados = :metrosCuadrados) AND " +
            "(:ciudad IS NULL OR u.ciudad = :ciudad) AND " +
            "(:provincia IS NULL OR u.provincia = :provincia) AND " +
            "(:pais IS NULL OR u.pais = :pais) AND " +
            "(:codigoPostal IS NULL OR u.codigoPostal = :codigoPostal)")
    List<Propiedad> findWithFilters(
            @Param("tipoContrato") TipoContrato tipoContrato,
            @Param("tipoPropiedad") TipoPropiedad tipoPropiedad,
            @Param("precioMin") Double precioMin,
            @Param("precioMax") Double precioMax,
            @Param("habitaciones") Integer habitaciones,
            @Param("banos") Integer banos,
            @Param("metrosCuadrados") Double metrosCuadrados,
            @Param("ciudad") String ciudad,
            @Param("provincia") String provincia,
            @Param("pais") String pais,
            @Param("codigoPostal") String codigoPostal
    );

}
