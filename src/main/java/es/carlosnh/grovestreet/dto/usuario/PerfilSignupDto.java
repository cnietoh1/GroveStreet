package es.carlosnh.grovestreet.dto.usuario;

import jakarta.validation.constraints.NotNull;


public record PerfilSignupDto (
  Long id,
  @NotNull
  String nombre
  ) {}
