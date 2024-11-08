package es.carlosnh.grovestreet.repositorios;

import es.carlosnh.grovestreet.entidades.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {

}
