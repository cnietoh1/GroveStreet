package es.carlosnh.grovestreet.repositorios;

import es.carlosnh.grovestreet.entidades.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {

}
