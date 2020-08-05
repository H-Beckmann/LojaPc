package com.beckmann.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.beckmann.model.Venda;

@Named
@ViewScoped
public class DetalhesVendaController implements Serializable{
	private static final long serialVersionUID = 5440629593716245699L;
	private Venda venda;
	
	public DetalhesVendaController() {
		Flash flash = FacesContext.
				getCurrentInstance().
				getExternalContext().getFlash();
		flash.keep("detalhesVenda");
		venda = (Venda) flash.get("detalhesVenda");
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
}
