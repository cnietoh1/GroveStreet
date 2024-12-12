package es.carlosnh.grovestreet.controladores;

import es.carlosnh.grovestreet.controladores.restapi.PropiedadController;
import es.carlosnh.grovestreet.dto.propiedad.PropiedadDto;
import es.carlosnh.grovestreet.entidades.Propiedad;
import es.carlosnh.grovestreet.entidades.TipoContrato;
import es.carlosnh.grovestreet.servicios.PropiedadService;
import es.carlosnh.grovestreet.dto.propiedad.PropiedadMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PropiedadController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PropiedadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropiedadService propiedadService;

    @MockBean
    private PropiedadMapper propiedadMapper;

    @Test
    void testCrearPropiedad() throws Exception {
        String nuevaPropiedad = """
            {
                "titulo": "Piso en el centro",
                "descripcion": "Bonito piso en el centro",
                "precio": 250000.0,
                "tipo": "venta"
            }
            """;

        PropiedadDto propiedadDto = PropiedadDto.builder()
                .id(1L)
                .titulo("Piso en el centro")
                .descripcion("Bonito piso en el centro")
                .precio(250000.0)
                .tipo("venta")
                .build();

        Propiedad propiedad = Propiedad.builder()
                .id(1L)
                .titulo("Piso en el centro")
                .descripcion("Bonito piso en el centro")
                .precio(250000.0)
                .tipoContrato(TipoContrato.VENTA)
                .build();

        when(propiedadMapper.toEntity(any(PropiedadDto.class))).thenReturn(propiedad);
        when(propiedadService.crear(any(Propiedad.class))).thenReturn(propiedad);
        when(propiedadMapper.toDto(any(Propiedad.class))).thenReturn(propiedadDto);

        mockMvc.perform(post("/api/propiedades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevaPropiedad))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Piso en el centro"))
                .andExpect(jsonPath("$.precio").value(250000.0));
    }

    @Test
    void testObtenerPropiedadPorId() throws Exception {
        Propiedad propiedad = Propiedad.builder()
                .id(1L)
                .titulo("Piso en el centro")
                .descripcion("Bonito piso en el centro")
                .precio(250000.0)
                .tipoContrato(TipoContrato.VENTA)
                .build();

        PropiedadDto propiedadDto = PropiedadDto.builder()
                .id(1L)
                .titulo("Piso en el centro")
                .descripcion("Bonito piso en el centro")
                .precio(250000.0)
                .tipo("venta")
                .build();

        when(propiedadService.obtenerPorId(1L)).thenReturn(propiedad);
        when(propiedadMapper.toDto(propiedad)).thenReturn(propiedadDto);

        mockMvc.perform(get("/api/propiedades/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Piso en el centro"))
                .andExpect(jsonPath("$.descripcion").value("Bonito piso en el centro"))
                .andExpect(jsonPath("$.precio").value(250000.0))
                .andExpect(jsonPath("$.tipo").value("venta"));
    }

    @Test
    void testObtenerTodasPropiedades() throws Exception {
        Propiedad propiedad = Propiedad.builder()
                .id(1L)
                .titulo("Piso en el centro")
                .descripcion("Bonito piso en el centro")
                .precio(250000.0)
                .tipoContrato(TipoContrato.VENTA)
                .build();

        PropiedadDto propiedadDto = PropiedadDto.builder()
                .id(1L)
                .titulo("Piso en el centro")
                .descripcion("Bonito piso en el centro")
                .precio(250000.0)
                .tipo("venta")
                .build();

        when(propiedadService.obtenerPropiedades()).thenReturn(List.of());
        when(propiedadMapper.toDto(propiedad)).thenReturn(propiedadDto);

        mockMvc.perform(get("/api/propiedades")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].titulo").value("Piso en el centro"))
                .andExpect(jsonPath("$[0].descripcion").value("Bonito piso en el centro"))
                .andExpect(jsonPath("$[0].precio").value(250000.0))
                .andExpect(jsonPath("$[0].tipo").value("venta"));
    }

    @Test
    void testEliminarPropiedad() throws Exception {
        when(propiedadService.eliminar(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/propiedades/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testEliminarPropiedadNotFound() throws Exception {
        when(propiedadService.eliminar(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/propiedades/1"))
                .andExpect(status().isNotFound());
    }
}
