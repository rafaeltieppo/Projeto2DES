package com.reidasviagens.viagens.model;

public class Bagagens {

	// Atributos
	// PK
	private int idBagagem;

	private String desc;
	private float peso;
	private float valor;

	public Bagagens(int idBagagem, String desc, float peso, float valor) {
		this.idBagagem = idBagagem;
		this.desc = desc;
		this.peso = peso;
		this.valor = valor;
	}

	

	public Bagagens(String desc, float peso, float valor) {
		super();
		this.desc = desc;
		this.peso = peso;
		this.valor = valor;
	}



	public Bagagens(int idBagagem) {
		this.idBagagem = idBagagem;
	}

	public Bagagens() {
	}

	public int getIdBagagem() {
		return idBagagem;
	}

	public void setIdBagagem(int idBagagem) {
		this.idBagagem = idBagagem;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

}
