package es.carlosnh.grovestreet.servicios;

import com.github.database.rider.core.api.dataset.DataSet;
import es.carlosnh.grovestreet.dto.propiedad.PropiedadDto;
import es.carlosnh.grovestreet.entidades.Propiedad;
import es.carlosnh.grovestreet.repositorios.PropiedadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PropiedadServiceTest {

    @InjectMocks
    private PropiedadService propiedadService;

    @Mock
    private PropiedadRepository propiedadRepository;

    private Propiedad propiedad;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        propiedad = new Propiedad();
        propiedad.setId(1L);
        propiedad.setTitulo("Piso en el centro");
        propiedad.setDescripcion("Bonito piso en el centro");
        propiedad.setPrecio(250000.0);
    }

    @Test
    @DataSet("datasets/propiedades.yml")
    void testCrearPropiedad() {
        when(propiedadRepository.save(propiedad)).thenReturn(propiedad);
        Propiedad resultado = propiedadService.guardarPropiedad(propiedad);
        assertNotNull(resultado);
        assertEquals("Piso en el centro", resultado.getTitulo());
        verify(propiedadRepository, times(1)).save(propiedad);
    }

    @Test
    void testObtenerTodasPropiedades() {
        when(propiedadRepository.findAll()).thenReturn(List.of(propiedad));
        List<PropiedadDto> resultado = propiedadService.obtenerPropiedades();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(propiedadRepository, times(1)).findAll();
    }
}
