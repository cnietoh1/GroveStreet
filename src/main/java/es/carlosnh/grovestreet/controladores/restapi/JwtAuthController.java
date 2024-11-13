package es.carlosnh.grovestreet.controladores.restapi;

import es.carlosnh.grovestreet.dto.jwt.*;
import es.carlosnh.grovestreet.entidades.Usuario;
import es.carlosnh.grovestreet.seguridad.jwt.JwtTokenProvider;
import es.carlosnh.grovestreet.servicios.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class JwtAuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider tokenProvider;
  private final UserDetailsService userDetailsService;
  private final UsuarioService usuarioService;
  private final JwtMapper mapper;


  @PostMapping(value = "/signin")
  public ResponseEntity<?> authenticate(@Valid @RequestBody JwtSigninRequest dto) throws Exception {

    try{
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        dto.getUsername(), dto.getPassword()));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("CREDENCIALES NO VÁLIDAS", e);
    }
    UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
    String token = tokenProvider.generateToken(userDetails);

    return ResponseEntity.ok(JwtSigninResponse.builder()
      .token(token)
      .username(userDetails.getUsername())
      .build());
  }

  @PostMapping("/signup")
  public ResponseEntity<?> register(@Valid @RequestBody JwtSignupRequest dto) throws Exception {
    if (dto == null)
      return ResponseEntity.badRequest().body(null);
    try {
      Usuario usuario = usuarioService.save(dto);
      JwtSignupResponse signupResponse = mapper.toDto(usuario);
      URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
          .buildAndExpand(usuario.getId()).toUri();
      return ResponseEntity.created(location).body(signupResponse);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseEntity.internalServerError()
          .body(Collections.singletonMap("error", e.getMessage()));
    }
  }

}
