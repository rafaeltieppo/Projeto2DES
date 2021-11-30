package com.reidasviagens.viagens.model;

public class Assentos {

	// Atributos
	// PK
	private int idAssento;

	private Cards idCard;
	private int assento;

	public Assentos() {
	}

	public Assentos(int idAssento) {
		this.idAssento = idAssento;
	}

	
	
	public Assentos(int idAssento, int assento) {
		
		this.idAssento = idAssento;
		this.assento = assento;
	}

	public Assentos(int idAssento, Cards idCard, int assento) {

		this.idAssento = idAssento;
		this.idCard = idCard;
		this.assento = assento;
	}

	public int getIdAssento() {
		return idAssento;
	}

	public void setIdAssento(int idAssento) {
		this.idAssento = idAssento;
	}

	public int getAssento() {
		return assento;
	}

	public void setAssento(int assento) {
		this.assento = assento;
	}

	public Cards getIdCard() {
		return idCard;
	}

	public void setIdCard(Cards idCard) {
		this.idCard = idCard;
	}

}
