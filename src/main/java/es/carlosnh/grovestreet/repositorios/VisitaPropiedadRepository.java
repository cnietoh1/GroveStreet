package es.carlosnh.grovestreet.repositorios;

import es.carlosnh.grovestreet.entidades.VisitaPropiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaPropiedadRepository extends JpaRepository<VisitaPropiedad, Long> {

}
