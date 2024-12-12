package es.carlosnh.grovestreet.controladores;

import es.carlosnh.grovestreet.controladores.restapi.UsuarioController;
import es.carlosnh.grovestreet.entidades.Usuario;
import es.carlosnh.grovestreet.servicios.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    void testCrearUsuario() throws Exception {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setId(1L);
        nuevoUsuario.setUsername("admin");
        nuevoUsuario.setEmail("admin@gmail.com");
        nuevoUsuario.setPassword("admin");

        when(usuarioService.crear(any(Usuario.class))).thenReturn(nuevoUsuario);

        String usuarioJson = """
            {
                "username": "admin",
                "email": "admin@gmail.com",
                "password": "admin"
            }
            """;

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(jsonPath("$.email").value("admin@gmail.com"));
    }

    @Test
    void testObtenerTodosUsuarios() throws Exception {
        List<Usuario> usuarios = Arrays.asList(
                Usuario.builder()
                        .id(1L)
                        .username("admin")
                        .email("admin@gmail.com")
                        .password("admin")
                        .build(),
                Usuario.builder()
                        .id(2L)
                        .username("user")
                        .email("user@gmail.com")
                        .password("user")
                        .build()
        );

        when(usuarioService.obtenerTodos()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].username").value("admin"))
                .andExpect(jsonPath("$[1].username").value("user"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testObtenerUsuarioPorId() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("admin");
        usuario.setEmail("admin@gmail.com");

        when(usuarioService.obtenerPorId(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(jsonPath("$.email").value("admin@gmail.com"));
    }


    @Test
    void testEliminarUsuario() throws Exception {
        when(usuarioService.eliminar(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testEliminarUsuarioNoEncontrado() throws Exception {
        when(usuarioService.eliminar(2L)).thenReturn(false);

        mockMvc.perform(delete("/api/usuarios/2"))
                .andExpect(status().isNotFound());
    }
}
