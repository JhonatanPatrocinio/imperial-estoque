package br.ufac.si.academico.controladores;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import br.ufac.si.academico.modelos.*;

@ManagedBean(name="loginControlador")
@SessionScoped
public class LoginControlador {

	private UsuarioGerente ug;
	private Usuario usuarioLogado;
	private String login;
	private String senha;
	
	public LoginControlador() {
		ug = new UsuarioGerente();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public String entrar() {
		FacesContext context = FacesContext.getCurrentInstance();
		usuarioLogado = ug.recuperar(login, senha);
		
		if(usuarioLogado != null) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", usuarioLogado);
			return "index.xhtml?faces-redirect=true";
		} else {
			return null;
		}
	}
	
	public String sair() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
		return "login.xhtml?faces-redirect=true";
	}
	
}
