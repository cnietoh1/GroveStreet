package es.carlosnh.grovestreet.servicios;

import es.carlosnh.grovestreet.entidades.Usuario;
import es.carlosnh.grovestreet.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Guardar usuario sin encriptar la contraseña
    public Usuario save(Usuario u) {
        log.info("Guardando usuario sin codificar contraseña...");
        return usuarioRepository.save(u);
    }

    // Encontrar por nombre de usuario
    public Optional<Usuario> encontrarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por su ID
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Eliminar un usuario por su ID
    public boolean eliminar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
