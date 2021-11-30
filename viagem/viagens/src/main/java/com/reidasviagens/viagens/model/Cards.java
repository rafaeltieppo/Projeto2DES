package com.reidasviagens.viagens.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Cards {

	// PK
	private int idCard;

	// Atributos
	private Adms idAdm;
	private Cidades idOrigem;
	private Cidades idDestino;
	private CompanhiaAerea idCompanhia;
	private Bagagens idBagagens;

	private String imageCard;
	private String dataIda;
	private String dataVolta;
	private String ihEmbarque;
	private String ihDesembarque;
	private String vhEmbarque;
	private String vhDesembarque;
	private float valor;
	private float precoAdulto;
	private float precoCrianca;
	private int totalAssentos;

	public Cards() {

	}
	
	

	public Cards(int idCard) {

		this.idCard = idCard;
	}



	public Cards(int idCard, Adms idAdm, Cidades idOrigem, Cidades idDestino, CompanhiaAerea idCompanhia,
			Bagagens idBagagens, String imageCard, String dataIda, String dataVolta, String ihEmbarque,
			String ihDesembarque, String vhEmbarque, String vhDesembarque, float valor, float precoAdulto,
			float precoCrianca, int totalAssentos) {

		this.idCard = idCard;
		this.idAdm = idAdm;
		this.idOrigem = idOrigem;
		this.idDestino = idDestino;
		this.idCompanhia = idCompanhia;
		this.idBagagens = idBagagens;
		this.imageCard = imageCard;
		this.dataIda = dataIda;
		this.dataVolta = dataVolta;
		this.ihEmbarque = ihEmbarque;
		this.ihDesembarque = ihDesembarque;
		this.vhEmbarque = vhEmbarque;
		this.vhDesembarque = vhDesembarque;
		this.valor = valor;
		this.precoAdulto = precoAdulto;
		this.precoCrianca = precoCrianca;
		this.totalAssentos = totalAssentos;
	}
	/*
	 * public Cards(int idCard, Adms idAdm, Cidades idOrigem, Cidades idDestino,
	 * Bagagens idBagagens, String dataIda, String dataVolta, CompanhiaAerea
	 * idCompanhia, String ihEmbarque, String ihDesembarque, String vhEmbarque,
	 * String vhDesembarque, float valor, float precoAdulto, float precoCrianca, int
	 * totalAssentos) {
	 * 
	 * this.idCard = idCard; this.idAdm = idAdm; this.idOrigem = idOrigem;
	 * this.idDestino = idDestino; this.idCompanhia = idCompanhia; this.idBagagens =
	 * idBagagens; this.dataIda = dataIda; this.dataVolta = dataVolta;
	 * this.ihEmbarque = ihEmbarque; this.ihDesembarque = ihDesembarque;
	 * this.vhEmbarque = vhEmbarque; this.vhDesembarque = vhDesembarque; this.valor
	 * = valor; this.precoAdulto = precoAdulto; this.precoCrianca = precoCrianca;
	 * this.totalAssentos = totalAssentos; }
	 */
	/*
	 * public Cards(int idCard, Adms idAdm, Cidades idOrigem, Cidades idDestino,
	 * CompanhiaAerea idCompanhia, Bagagens idBagagens, String dataIda, String
	 * dataVolta, String ihEmbarque, String ihDesembarque, String vhEmbarque, String
	 * vhDesembarque, float valor, float precoAdulto, float precoCrianca, int
	 * totalAssentos) {
	 * 
	 * this.idCard = idCard; this.idAdm = idAdm; this.idOrigem = idOrigem;
	 * this.idDestino = idDestino; this.idCompanhia = idCompanhia; this.idBagagens =
	 * idBagagens; this.dataIda = dataIda; this.dataVolta = dataVolta;
	 * this.ihEmbarque = ihEmbarque; this.ihDesembarque = ihDesembarque;
	 * this.vhEmbarque = vhEmbarque; this.vhDesembarque = vhDesembarque; this.valor
	 * = valor; this.precoAdulto = precoAdulto; this.precoCrianca = precoCrianca;
	 * this.totalAssentos = totalAssentos; }
	 */

	public int getIdCard() {
		return idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}

	public Adms getIdAdm() {
		return idAdm;
	}

	public void setIdAdm(Adms idAdm) {
		this.idAdm = idAdm;
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

	public CompanhiaAerea getIdCompanhia() {
		return idCompanhia;
	}

	public void setIdCompanhia(CompanhiaAerea idCompanhia) {
		this.idCompanhia = idCompanhia;
	}

	public Bagagens getIdBagagens() {
		return idBagagens;
	}

	public void setIdBagagens(Bagagens idBagagens) {
		this.idBagagens = idBagagens;
	}

	public String getImageCard() {
		return imageCard;
	}

	public void setImageCard(String imageCard) {
		this.imageCard = imageCard;
	}

	public String getDataIda() {
		return dataIda;
	}

	public void setDataIda(String dataIda) {
		this.dataIda = dataIda;
	}

	public String getDataVolta() {
		return dataVolta;
	}

	public void setDataVolta(String dataVolta) {
		this.dataVolta = dataVolta;
	}

	public String getIhEmbarque() {
		return ihEmbarque;
	}

	public void setIhEmbarque(String ihEmbarque) {
		this.ihEmbarque = ihEmbarque;
	}

	public String getIhDesembarque() {
		return ihDesembarque;
	}

	public void setIhDesembarque(String ihDesembarque) {
		this.ihDesembarque = ihDesembarque;
	}

	public String getVhEmbarque() {
		return vhEmbarque;
	}

	public void setVhEmbarque(String vhEmbarque) {
		this.vhEmbarque = vhEmbarque;
	}

	public String getVhDesembarque() {
		return vhDesembarque;
	}

	public void setVhDesembarque(String vhDesembarque) {
		this.vhDesembarque = vhDesembarque;
	}
	
	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getPrecoAdulto() {
		return precoAdulto;
	}

	public void setPrecoAdulto(float precoAdulto) {
		this.precoAdulto = precoAdulto;
	}
	
	public float getPrecoCrianca() {
		return precoCrianca;
	}

	public void setPrecoCrianca(float precoCrianca) {
		this.precoCrianca = precoCrianca;
	}

	public int getTotalAssentos() {
		return totalAssentos;
	}

	public void setTotalAssentos(int totalAssentos) {
		this.totalAssentos = totalAssentos;
	}
	
	
}
