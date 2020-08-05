package com.beckmann.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.beckmann.application.Sessao;
import com.beckmann.application.Util;
import com.beckmann.dao.PecaDAO;
import com.beckmann.model.ItemVenda;
import com.beckmann.model.Peca;

@Named
@ViewScoped
public class VendaPecaController implements Serializable {

	private static final long serialVersionUID = -5243343690138626623L;
	private String descricao;
	private List<Peca> listaPeca = null;
	
	public void pesquisar() {
		listaPeca = null;
	}
	
	public void adicionar(int idLivro) {
		PecaDAO dao = new PecaDAO();
		Peca peca = dao.findById(idLivro);
		// verifica se existe um carrinho na sessao
		if (Sessao.getInstance().getAttribute("carrinho") == null) {
			// adiciona um carrinho (de itens de venda) na sessao
			Sessao.getInstance().setAttribute("carrinho", 
					new ArrayList<ItemVenda>());
		}
		
		// obtendo o carrinho da sessao
		List<ItemVenda> carrinho = 
				(ArrayList<ItemVenda>) Sessao.getInstance().getAttribute("carrinho");
		
		// criando um item de venda para adicionar no carrinho
		ItemVenda item = new ItemVenda();
		item.setPeca(peca);
		item.setValor(peca.getPreco());
		
		// adicionando o item no carrinho (variavel local) 
		carrinho.add(item);
		
		// atualizando o carrinho na sessao
		Sessao.getInstance().setAttribute("carrinho", carrinho);
		
		Util.addInfoMessage("Livro adicionado no carrinho. "
				+ "Quantidade de Itens: " + carrinho.size());
		
	}

	public List<Peca> getListaPeca() {
		if (listaPeca == null) {
			PecaDAO dao = new PecaDAO();
			listaPeca = dao.findByDescricao(getDescricao());
			if (listaPeca == null)
				listaPeca = new ArrayList<Peca>();
		}
		return listaPeca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
