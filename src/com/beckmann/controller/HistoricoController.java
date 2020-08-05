package com.beckmann.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.beckmann.application.Sessao;
import com.beckmann.dao.VendaDAO;
import com.beckmann.model.Usuario;
import com.beckmann.model.Venda;

@Named
@ViewScoped
public class HistoricoController implements Serializable {
	
	private static final long serialVersionUID = -7315416608078549905L;
	private List<Venda> listaVenda = null;
	

	public List<Venda> getListaVenda() {
		if (listaVenda == null) {
			VendaDAO dao = new VendaDAO();
			Usuario usuario = (Usuario) Sessao.getInstance().getAttribute("usuarioLogado");
			listaVenda = dao.findByUsuario(usuario.getId());
			if (listaVenda == null)
				listaVenda = new ArrayList<Venda>();
		}
		return listaVenda;
	}
	
	public String detalhes(Venda venda) {
		Flash flash = FacesContext.
				getCurrentInstance().
				getExternalContext().getFlash();
		flash.put("detalhesVenda", venda);
		
		return "detalhesvenda.xhtml?faces-redirect=true";
	}
	
}
