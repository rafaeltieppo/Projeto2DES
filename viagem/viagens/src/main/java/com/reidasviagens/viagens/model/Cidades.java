package com.reidasviagens.viagens.model;

public class Cidades {

	// Atributos
	// PK
	private int idCidade;

	private String nome;
	private String aeroporto;
	private String image;

	public Cidades(int idCidade) {
		this.idCidade = idCidade;
	}

	public Cidades() {
	}

	public Cidades(String nome, String aeroporto) {
		this.nome = nome;
		this.aeroporto = aeroporto;
	}

	public Cidades(String nome, String aeroporto, String image) {
		this.nome = nome;
		this.aeroporto = aeroporto;
		this.image = image;
	}

	public Cidades(int idCidade, String nome, String aeroporto) {
		this.idCidade = idCidade;
		this.nome = nome;
		this.aeroporto = aeroporto;
	}

	public Cidades(int idCidade, String nome, String aeroporto, String image) {
		this.idCidade = idCidade;
		this.nome = nome;
		this.aeroporto = aeroporto;
		this.image = image;
	}

	public int getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAeroporto() {
		return aeroporto;
	}

	public void setAeroporto(String aeroporto) {
		this.aeroporto = aeroporto;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
