package com.reidasviagens.viagens.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Passagens {

	// Atributos
	// PK
	private int idPassagem;
	// FK
	private Clientes idCliente;
	
	private Cards idCard;
	private Assentos idAssento;
	private Cidades idOrigem;
	private Cidades idDestino;
	private Descontos idDesconto;
	private CompanhiaAerea companhiaAerea;
	private Bagagens idBagagem;

	private String imagePass;
	private String horaEmbarque;
	private String horaDesembarque;
	private String horaCompra;
	private float preco;


	public Passagens(int idPassagem, Clientes idCliente, Cards idCard, Assentos idAssento, Cidades idOrigem,
			Cidades idDestino, Descontos idDesconto, CompanhiaAerea companhiaAerea, Bagagens idBagagem,
			String imagePass, String horaEmbarque, String horaDesembarque, String horaCompra, float preco) {

		this.idPassagem = idPassagem;
		this.idCliente = idCliente;
		this.idCard = idCard;
		this.idAssento = idAssento;
		this.idOrigem = idOrigem;
		this.idDestino = idDestino;
		this.idDesconto = idDesconto;
		this.companhiaAerea = companhiaAerea;
		this.idBagagem = idBagagem;
		this.imagePass = imagePass;
		this.horaEmbarque = horaEmbarque;
		this.horaDesembarque = horaDesembarque;
		this.horaCompra = horaCompra;
		this.preco = preco;
	}

	public int getIdPassagem() {
		return idPassagem;
	}

	public void setIdPassagem(int idPassagem) {
		this.idPassagem = idPassagem;
	}

	public Clientes getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Clientes idCliente) {
		this.idCliente = idCliente;
	}

	public Cards getIdCard() {
		return idCard;
	}

	public void setIdCard(Cards idCard) {
		this.idCard = idCard;
	}

	public Assentos getIdAssento() {
		return idAssento;
	}

	public void setIdAssento(Assentos idAssento) {
		this.idAssento = idAssento;
	}

	public Cidades getIdOrigem() {
		return idOrigem;
	}

	public void setIdOrigem(Cidades idOrigem) {
		this.idOrigem = idOrigem;
	}

	public Cidades getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(Cidades idDestino) {
		this.idDestino = idDestino;
	}

	public Descontos getIdDesconto() {
		return idDesconto;
	}

	public void setIdDesconto(Descontos idDesconto) {
		this.idDesconto = idDesconto;
	}

	public CompanhiaAerea getCompanhiaAerea() {
		return companhiaAerea;
	}

	public void setCompanhiaAerea(CompanhiaAerea companhiaAerea) {
		this.companhiaAerea = companhiaAerea;
	}

	public Bagagens getIdBagagem() {
		return idBagagem;
	}

	public void setIdBagagem(Bagagens idBagagem) {
		this.idBagagem = idBagagem;
	}

	public String getImagePass() {
		return imagePass;
	}

	public void setImagePass(String imagePass) {
		this.imagePass = imagePass;
	}

	public String getHoraEmbarque() {
		return horaEmbarque;
	}

	public void setHoraEmbarque(String horaEmbarque) {
		this.horaEmbarque = horaEmbarque;
	}

	public String getHoraDesembarque() {
		return horaDesembarque;
	}

	public void setHoraDesembarque(String horaDesembarque) {
		this.horaDesembarque = horaDesembarque;
	}

	public String getHoraCompra() {
		return horaCompra;
	}

	public void setHoraCompra(String horaCompra) {
		this.horaCompra = horaCompra;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	/*
	 * public Passagens(int idPassagem, Clientes idCliente, Assentos idAssento,
	 * Cidades idOrigem, Cidades idDestino, Descontos idDesconto, CompanhiaAerea
	 * companhiaAerea, Bagagens idBagagem, String horaEmbarque, String
	 * horaDesembarque, String horaCompra, float preco) { this.idPassagem =
	 * idPassagem; this.idCliente = idCliente; this.idAssento = idAssento;
	 * this.idOrigem = idOrigem; this.idDestino = idDestino; this.idDesconto =
	 * idDesconto; this.companhiaAerea = companhiaAerea; this.idBagagem = idBagagem;
	 * this.horaEmbarque = horaEmbarque; this.horaDesembarque = horaDesembarque;
	 * this.horaCompra = horaCompra; this.preco = preco; }
	 */

}
