package es.carlosnh.grovestreet.repositorios;

import es.carlosnh.grovestreet.entidades.ImagenPropiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenPropiedadRepository extends JpaRepository<ImagenPropiedad, Long> {

}
