package es.carlosnh.grovestreet.dto.jwt;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtSigninRequest {

  @NotBlank
  private String username;

  @NotBlank
  private String password;

}