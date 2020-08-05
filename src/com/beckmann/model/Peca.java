package com.beckmann.model;

public class Peca extends Entity<Peca>{
	private static final long serialVersionUID = 861062496988355425L;
	private String nome;
	private String desc;
	private float preco;
	private CategoriaPeca categoriaPeca;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public CategoriaPeca getCategoriaPeca() {
		return categoriaPeca;
	}
	public void setCategoriaPeca(CategoriaPeca categoriaPeca) {
		this.categoriaPeca = categoriaPeca;
	}
}
