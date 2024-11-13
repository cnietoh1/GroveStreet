package es.carlosnh.grovestreet.seguridad;

import es.carlosnh.grovestreet.entidades.Usuario;
import es.carlosnh.grovestreet.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.buscarPorUsernameOEmail(s)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		return User.builder()
				.username(usuario.getUsername())
				.password(usuario.getPassword())
				.disabled(false)
				.build();

	}

}
