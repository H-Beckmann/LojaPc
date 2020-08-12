package com.beckmann.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.beckmann.application.Sessao;
import com.beckmann.application.Util;
import com.beckmann.dao.UsuarioDAO;
import com.beckmann.model.Usuario;

@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;
	
	public String logar() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.verificarLoginSenha(getUsuario().getLogin(),
				Util.hashSHA256(getUsuario().getSenha()));
		
		if (usuario != null) {
			Sessao.getInstance().setAttribute("usuarioLogado", usuario);
			return "principal.xhtml?faces-redirect=true";
		}
		Util.addErrorMessage("Login ou Senha inválido.");
		return "";
	}
	
	public void limpar() {
		usuario = null;
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
