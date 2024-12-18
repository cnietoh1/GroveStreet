package es.carlosnh.grovestreet.controladores.restapi;

import es.carlosnh.grovestreet.entidades.Usuario;
import es.carlosnh.grovestreet.servicios.PropiedadService;
import es.carlosnh.grovestreet.servicios.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final PropiedadService propiedadService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> iniciarSesion(@RequestBody Usuario usuario, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("username", usuario.getUsername());

        return ResponseEntity.ok(Map.of(
                "message", "Inicio de sesión exitoso",
                "username", usuario.getUsername()
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> registrarUsuario(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        return ResponseEntity.ok(Map.of("message", "Usuario registrado correctamente"));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok("Sesión cerrada correctamente");
    }

    @GetMapping("/{username}")
    public ResponseEntity<Usuario> obtenerUsuarioPorUsername(@PathVariable String username) {
        return usuarioService.encontrarPorUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
