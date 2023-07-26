package br.com.multiinovacoes.gcon.lai.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.multiinovacoes.gcon.lai.model.Manifestante;
import br.com.multiinovacoes.gcon.lai.repository.ManifestanteRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private ManifestanteRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String documento) throws UsernameNotFoundException {
		Optional<Manifestante> usuarioOptional = usuarioRepository.findByNumeroDocumento(documento);
		Manifestante usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new UsuarioSistema(usuario, getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Manifestante usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		//usuario.getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
		return authorities;
	}

}
