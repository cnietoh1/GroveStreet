package es.carlosnh.grovestreet.servicios;

import es.carlosnh.grovestreet.dto.usuario.UsuarioMapper;
import es.carlosnh.grovestreet.dto.usuario.UsuarioSignupDto;
import es.carlosnh.grovestreet.entidades.Usuario;
import es.carlosnh.grovestreet.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public Usuario save(Usuario u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return usuarioRepository.save(u);
    }

    public Usuario save(UsuarioSignupDto dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.password()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> saveAll (List<Usuario> lista) {
        lista.stream()
                .forEach(usuario ->
                        usuario.setPassword(passwordEncoder.encode(usuario.getPassword())));
        return usuarioRepository.saveAll(lista); }

    public Usuario findById(long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username).orElse(null);
    }

    public Usuario buscarPorUsernameOEmail(String s) {
        return usuarioRepository.buscarPorUsernameOEmail(s).orElse(null);
    }

    public Usuario findByUsernameOrEmail(String username, String email) {
        return usuarioRepository.findByUsernameOrEmail(username,email).orElse(null);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario crear(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean eliminar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
