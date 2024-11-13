package es.carlosnh.grovestreet.dto.usuario;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UsuarioSignupDto (
  @NotNull
  String username,
  @NotNull
  String email,
  @NotNull
  String password
){}
