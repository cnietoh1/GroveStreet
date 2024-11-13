package es.carlosnh.grovestreet.dto.jwt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtSignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotNull
  String email;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

}
