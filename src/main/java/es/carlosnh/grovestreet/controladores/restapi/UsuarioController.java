package es.carlosnh.grovestreet.controladores.restapi;

import es.carlosnh.grovestreet.dto.usuario.UsuarioDto;
import es.carlosnh.grovestreet.entidades.Usuario;
import es.carlosnh.grovestreet.servicios.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .build();
        return ResponseEntity.ok(usuarioDto);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> obtenerTodos() {
        List<UsuarioDto> usuarios = usuarioService.obtenerTodos().stream()
                .map(u -> UsuarioDto.builder()
                        .id(u.getId())
                        .username(u.getUsername())
                        .email(u.getEmail())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.crear(usuario);
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .id(nuevoUsuario.getId())
                .username(nuevoUsuario.getUsername())
                .email(nuevoUsuario.getEmail())
                .build();
        return ResponseEntity.ok(usuarioDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
