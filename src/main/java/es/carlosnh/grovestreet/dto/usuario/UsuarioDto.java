package es.carlosnh.grovestreet.dto.usuario;

import lombok.*;

@Getter
@Setter
@Builder
public class UsuarioDto {
    private Long id;
    private String username;
    private String email;
    private String password;
}
