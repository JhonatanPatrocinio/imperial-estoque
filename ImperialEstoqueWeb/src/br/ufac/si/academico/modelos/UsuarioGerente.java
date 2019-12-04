package br.ufac.si.academico.modelos;

import java.util.*;

public class UsuarioGerente {
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public UsuarioGerente() {
		usuarios.add(new Usuario("admin", "Administrador", "admin"));
	}
	
	public Usuario recuperar(String login, String senha) {
		for (Usuario usuario : usuarios) {
			if(usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
				return usuario;
			}
		}
		return null;
	}

}
