package es.carlosnh.grovestreet.repositorios;


import es.carlosnh.grovestreet.entidades.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

}
