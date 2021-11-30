package com.reidasviagens.viagens.model;

public class Descontos {
	// Atributos
	// PK
	private int idDescontos;

	private String tipo;
	private float valor;

	public Descontos() {
	}

	public Descontos(int idDescontos) {
		this.idDescontos = idDescontos;
	}

	public Descontos(String tipo, float valor) {
		this.tipo = tipo;
		this.valor = valor;
	}

	public Descontos(int idDescontos, String tipo, float valor) {
		this.idDescontos = idDescontos;
		this.tipo = tipo;
		this.valor = valor;
	}

	public int getIdDescontos() {
		return idDescontos;
	}

	public void setIdDescontos(int idDescontos) {
		this.idDescontos = idDescontos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

}
