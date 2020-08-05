package com.beckmann.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.beckmann.application.Util;
import com.beckmann.dao.UsuarioDAO;
import com.beckmann.model.Usuario;

@Named
@ViewScoped
public class CadastroUsuarioController extends Controller<Usuario> implements Serializable {
	
	private static final long serialVersionUID = 6066151802539299604L;
	private List<Usuario> listaUsuario;
	
	public CadastroUsuarioController() {
		super(new UsuarioDAO());
	}
	
	@Override
	public Usuario getEntity() {
		if (entity == null)
			entity = new Usuario();
		return entity;
	}
	
	@Override
	public void limpar() {
		super.limpar();
		listaUsuario = null;
	}

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			UsuarioDAO dao = new UsuarioDAO();
			listaUsuario = dao.findAll();
		}
		return listaUsuario;
	}

	
	@Override
	public boolean validarDados() {
		if (getEntity().getNome().isBlank()) {
			Util.addErrorMessage("O campo nome deve ser informado.");
			return false;
		}
		
		// gerando o hash da senha
		String senha = Util.hashSHA256(getEntity().getSenha());
		getEntity().setSenha(senha);
		
		return true;
	}
}