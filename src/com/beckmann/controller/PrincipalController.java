package com.beckmann.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.beckmann.model.Usuario;
import com.beckmann.application.Sessao;

@Named
@ViewScoped
public class PrincipalController implements Serializable {

	private static final long serialVersionUID = -3142260687340651656L;
	private Usuario usuarioLogado = null;

	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null) // buscando o usuario da sessao
			usuarioLogado = (Usuario) Sessao.getInstance().getAttribute("usuarioLogado");			
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String encerrarSessao() {
		// encerrando a sessao
		Sessao.getInstance().invalidateSession();
		return "login.xhtml?faces-redirect=true";
	}

}
