package es.carlosnh.grovestreet.repositorios;

import es.carlosnh.grovestreet.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    @Query("select u from Usuario u " +
            "where lower(u.username) = ?1 or lower(u.email) = ?1")
    Optional<Usuario> buscarPorUsernameOEmail(String s);
}
