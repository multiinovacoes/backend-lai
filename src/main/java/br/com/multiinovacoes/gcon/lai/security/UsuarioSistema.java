package br.com.multiinovacoes.gcon.lai.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.multiinovacoes.gcon.lai.model.Manifestante;


public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;

	private Manifestante usuario;

	public UsuarioSistema(Manifestante usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getNome(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Manifestante getUsuario() {
		return usuario;
	}

}