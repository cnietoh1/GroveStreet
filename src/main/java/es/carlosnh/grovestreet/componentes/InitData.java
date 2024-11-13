package es.carlosnh.grovestreet.componentes;

import es.carlosnh.grovestreet.entidades.Usuario;
import es.carlosnh.grovestreet.servicios.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final UsuarioService usuarioService;

    @Transactional
    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initScrum();
    }

    public void initScrum() {

        /*Usuario usuario1 = Usuario.builder()
                .username("admin")
                .email("user@gmail.com")
                .password("admin")
                .build();

        usuario1 = usuarioService.save(usuario1);*/
    }
}
