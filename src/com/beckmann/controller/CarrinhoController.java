package com.beckmann.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.beckmann.application.Sessao;
import com.beckmann.application.Util;
import com.beckmann.dao.VendaDAO;
import com.beckmann.model.ItemVenda;
import com.beckmann.model.Usuario;
import com.beckmann.model.Venda;

@Named
@ViewScoped
public class CarrinhoController implements Serializable{

	private static final long serialVersionUID = 7318623976757829750L;
	private Venda venda;

	public Venda getVenda() {
		if (venda == null) 
			venda = new Venda();
		List<ItemVenda> carrinho = (ArrayList<ItemVenda>)Sessao.getInstance().getAttribute("carrinho");
		if (carrinho == null)
			carrinho = new ArrayList<ItemVenda>();
		venda.setListaItemVenda(carrinho);
		return venda;
	}
	
	public void remove(int idProduto) {
		List<ItemVenda> carrinho = (ArrayList<ItemVenda>) Sessao.getInstance().getAttribute("carrinho");
        int cont = 0;
        for (ItemVenda itemVenda : carrinho) {
            if (itemVenda.getPeca().getId() == idProduto) {
                carrinho.remove(cont);
                break;
            }
            cont++;
        }
	}
	
	public void finalizar() {
		Usuario usuario = (Usuario)Sessao.getInstance().getAttribute("usuarioLogado");
		if (usuario == null) {
			Util.addWarningMessage("É preciso estar logado para realizar uma venda. Faca o Login!!");
			return;
		}

		Venda venda = new Venda();
		venda.setData(LocalDate.now());
		venda.setUsuario(usuario);
		List<ItemVenda> carrinho = (ArrayList<ItemVenda>)  Sessao.getInstance().getAttribute("carrinho");
		venda.setListaItemVenda(carrinho);
		VendaDAO dao = new VendaDAO();
		if (dao.create(venda)) {
			Util.addInfoMessage("Venda realizada com sucesso.");
			Sessao.getInstance().setAttribute("carrinho", null);
		} else {
			Util.addErrorMessage("Erro ao finalizar a Venda.");
		}
	}

	public void setVenda(Venda venda) {
		
		this.venda = venda;
	}	
}
