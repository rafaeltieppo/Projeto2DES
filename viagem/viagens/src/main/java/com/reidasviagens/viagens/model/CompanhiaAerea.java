package com.reidasviagens.viagens.model;

public class CompanhiaAerea {

	// Atributos
	// PK
	private int idCompanhia;

	private int cnpj;
	private String nome;
	
	

	public CompanhiaAerea(int idCompanhia, int cnpj, String nome) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.idCompanhia = idCompanhia;
	}

	
	
	/*
	public CompanhiaAerea(int cnpjj, String nomee) {
		this.cnpj = cnpjj;
		this.nome = nomee;
	}*/




	public CompanhiaAerea(int idCompanhia) {
		this.idCompanhia = idCompanhia;
	}

	public int getIdCompanhia() {
		return idCompanhia;
	}

	public void setIdCompanhia(int idCompanhia) {
		this.idCompanhia = idCompanhia;
	}

	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
