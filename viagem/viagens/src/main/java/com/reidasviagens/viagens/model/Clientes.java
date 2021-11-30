package com.reidasviagens.viagens.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// valores default (ex: 0, "", null)
@JsonInclude(value = Include.NON_EMPTY)
public class Clientes {
	// Atributos
	// PK
	private int idCliente;

	// FK
	private DadosBancarios idBancario;
	private Passagens idPassagem;

	private int nvl;
	private boolean cad;

	private boolean status;
	private int rg;
	private String usuario;
	private String senha;
	private String nome;
	private String sexo;
	private String endereco;
	private int telefone;
	private String nascimento;

	public Clientes() {
	}

	public Clientes(boolean status) {
		this.status = status;
	}

	public Clientes(int idCliente) {
		this.idCliente = idCliente;
	}

	public Clientes(int idCliente, int nvl, boolean status, boolean cad) {
		this.idCliente = idCliente;
		this.nvl = nvl;
		this.status = status;
		this.cad = cad;
	}

	public Clientes(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}
	
	

	
	public Clientes(int rg, String nome, String sexo, String endereco, int telefone, String nascimento) {
		this.rg = rg;
		this.nome = nome;
		this.sexo = sexo;
		this.endereco = endereco;
		this.telefone = telefone;
		this.nascimento = nascimento;
	}

	public Clientes(int idCliente, DadosBancarios idBancario, int rg, String sexo, String endereco, int telefone,
			String nascimento) {
		this.idCliente = idCliente;
		this.idBancario = idBancario;
		this.rg = rg;
		this.sexo = sexo;
		this.endereco = endereco;
		this.telefone = telefone;
		this.nascimento = nascimento;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public DadosBancarios getIdBancario() {
		return idBancario;
	}

	public void setIdBancario(DadosBancarios idBancario) {
		this.idBancario = idBancario;
	}

	public Passagens getIdPassagem() {
		return idPassagem;
	}

	public void setIdPassagem(Passagens idPassagem) {
		this.idPassagem = idPassagem;
	}
	
	public int getRg() {
		return rg;
	}

	public void setRg(int rg) {
		this.rg = rg;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public int getNvl() {
		return nvl;
	}

	public void setNvl(int nvl) {
		this.nvl = nvl;
	}

	public boolean getCad() {
		return cad;
	}

	public void setCad(boolean cad) {
		this.cad = cad;
	}

}
