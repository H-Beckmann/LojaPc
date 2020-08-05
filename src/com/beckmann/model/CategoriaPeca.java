package com.beckmann.model;

public enum CategoriaPeca {
	
	INDEFINIDO(0, "Selecione uma categoria"),
	PLACA_DE_VIDEO(1, "Placa de video"),
	PROCESSADOR(2, "Processador"),
	PLACA_MAE(3, "Placa mae"),
	MEMORIA(4, "memoria RAM"),
	HD(5, "Armazenamento"),
	COOLER(6, "Cooler"),
	GABINETE(7, "Gabinete"),
	FONTE(8, "Fonte"),
	PERIFERICO(9, "Perifericos");
	
	private int id;
	private String label;

	CategoriaPeca(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static CategoriaPeca valueOf(int value) {
		for (CategoriaPeca categoriaPeca : CategoriaPeca.values()) {
			if (value == categoriaPeca.getId())
				return categoriaPeca;
		}
		return null;
	}
	
}
