package com.reidasviagens.viagens.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Adms {

	private int idAdm;
	private int nvl;
	private boolean status;

	private String usuario;
	private String senha;

	public Adms() {
	}

	public Adms(int idAdm, int nvl, boolean status) {
		this.idAdm = idAdm;
		this.nvl = nvl;
		this.status = status;
	}

	public Adms(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	public Adms(int idAdm) {
		this.idAdm = idAdm;
	}

	public int getIdAdm() {
		return idAdm;
	}

	public void setIdAdm(int idAdm) {
		this.idAdm = idAdm;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@JsonIgnore
	@JsonProperty(value = "usuario")
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@JsonIgnore
	@JsonProperty(value = "senha")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getNvl() {
		return nvl;
	}

	public void setNvl(int nvl) {
		this.nvl = nvl;
	}

}
