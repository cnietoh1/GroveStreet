package es.carlosnh.grovestreet.servicios;

import com.github.database.rider.core.api.dataset.DataSet;
import es.carlosnh.grovestreet.entidades.Usuario;
import es.carlosnh.grovestreet.repositorios.UsuarioRepository;
import es.carlosnh.grovestreet.servicios.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("admin");
        usuario.setEmail("admin@gmail.com");
        usuario.setPassword("admin");
    }

    @Test
    @DataSet("datasets/usuarios.yml")
    void testCrearUsuario() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario resultado = usuarioService.save(usuario);
        assertNotNull(resultado);
        assertEquals("admin", resultado.getUsername());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testObtenerUsuarioPorId() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Usuario resultado = usuarioService.obtenerPorId(1L);
        assertNotNull(resultado);
        assertEquals("admin", resultado.getUsername());
        verify(usuarioRepository, times(1)).findById(1L);
    }
}
