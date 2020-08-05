package com.beckmann.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.beckmann.dao.PecaDAO;
import com.beckmann.model.Peca;

@Named
@ViewScoped
public class ConsultaPecaController implements Serializable{
	
	private static final long serialVersionUID = 8449792042360411680L;
	private int tipoFiltro = 1;
	private String filtro;
	private List<Peca> listaPeca;
	
	public void pesquisar() {
		PecaDAO dao = new PecaDAO();
		if (tipoFiltro == 1)
			listaPeca = dao.findByDescricao(getFiltro());
	}
	
	public String novaPeca() {
		return "cadastropeca.xhtml?faces-redirect=true";
	}
	
	public String editar(Peca peca) {
		PecaDAO dao = new PecaDAO();
		peca = dao.findById(peca.getId());
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("flashPeca", peca);
		return "cadastropeca.xhtml?faces-redirect=true";
	}

	public List<Peca> getListaPeca() {
		if (listaPeca == null) {
			listaPeca = new ArrayList<Peca>();
		}
		return listaPeca;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public int getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(int tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}
}
