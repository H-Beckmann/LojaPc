package com.beckmann.controller;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.beckmann.dao.PecaDAO;
import com.beckmann.model.CategoriaPeca;
import com.beckmann.model.Peca;

@Named
@ViewScoped
public class CadastroPecaController extends Controller<Peca> {

	private static final long serialVersionUID = -3644767208798626136L;

	public CadastroPecaController() {
		super(new PecaDAO());
		Flash flash = FacesContext.getCurrentInstance().
				getExternalContext().getFlash();
		flash.keep("flashPeca");
		entity = (Peca) flash.get("flashPeca");
	}
	
	@Override
	public Peca getEntity() {
		if (entity == null)
			entity = new Peca();
		return entity;
	}
	
	public CategoriaPeca[] getListaCategoria() {
		return CategoriaPeca.values();
	}
}
