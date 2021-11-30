package com.reidasviagens.viagens.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DadosBancarios {

	// Atributos
	// PK
	private int idBancario;

	private int numeroCartao;
	private String nomeCliCartao;
	private String validade;
	private int numeroConta;
	private int chave;

	public DadosBancarios() {
	}

	public DadosBancarios(int idBancario) {
		this.idBancario = idBancario;
	}

	public DadosBancarios(int idBancario, int numeroCartao, String nomeCliCartao) {

		this.idBancario = idBancario;
		this.numeroCartao = numeroCartao;
		this.nomeCliCartao = nomeCliCartao;
	}

	public DadosBancarios(int idBancario, int numeroCartao, String nomeCliCartao, String validade, int numeroConta,
			int chave) {
		this.idBancario = idBancario;
		this.numeroCartao = numeroCartao;
		this.nomeCliCartao = nomeCliCartao;
		this.validade = validade;
		this.numeroConta = numeroConta;
		this.chave = chave;
	}

	public int getIdBancario() {
		return idBancario;
	}

	public void setIdBancario(int idBancario) {
		this.idBancario = idBancario;
	}

	
	public int getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(int numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNomeCliCartao() {
		return nomeCliCartao;
	}

	public void setNomeCliCartao(String nomeCliCartao) {
		this.nomeCliCartao = nomeCliCartao;
	}

	@JsonIgnore
	@JsonProperty("validade")
	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	@JsonIgnore
	@JsonProperty("numeroConta")
	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	@JsonIgnore
	@JsonProperty("chave")
	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}

}
