package com.beckmann.model;

public enum TipoUsuario {
	INDEFINIDO(0, "Selecione uma opção"),
	ADM(1, "Administrador"),
	CLIENTE(2, "Cliente");
	
	private int id;
	private String label;
	
	TipoUsuario(int id, String label) {
		this.id = id;
		this.label = label;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public static TipoUsuario valueOf(int value) {
		for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
			if (value == tipoUsuario.getId())
				return tipoUsuario;
		}
		return null;
	}
	
}
