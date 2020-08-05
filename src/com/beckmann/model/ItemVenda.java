package com.beckmann.model;

public class ItemVenda extends Entity<ItemVenda> {
	private static final long serialVersionUID = 257669033097205012L;
	
	private Peca peca;
	private Float valor;
	private Venda venda;

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

}