package es.carlosnh.grovestreet.repositorios;

import es.carlosnh.grovestreet.entidades.VisitiaPropiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaPropiedadRepository extends JpaRepository<VisitiaPropiedad, Long> {

}
