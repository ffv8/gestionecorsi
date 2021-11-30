package com.gestionecorsi.businesscomponent.model;

import java.util.Date;

public class Corso {
	private long codCorso;
	private long codDocente;
	private String nomeCorso;
	private Date dataInizioCorso;
	private Date dataFineCorso;
	private double costoCorso;
	private String commentiCorso;
	private String aulaCorso;

	public long getCodCorso() {
		return codCorso;
	}

	public void setCodCorso(long codCorso) {
		this.codCorso = codCorso;
	}

	public long getCodDocente() {
		return codDocente;
	}

	public void setCodDocente(long codDocente) {
		this.codDocente = codDocente;
	}

	public String getNomeCorso() {
		return nomeCorso;
	}

	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}

	public Date getDataInizioCorso() {
		return dataInizioCorso;
	}

	public void setDataInizioCorso(Date dataInizioCorso) {
		this.dataInizioCorso = dataInizioCorso;
	}

	public Date getDataFineCorso() {
		return dataFineCorso;
	}

	public void setDataFineCorso(Date dataFineCorso) {
		this.dataFineCorso = dataFineCorso;
	}

	public double getCostoCorso() {
		return costoCorso;
	}

	public void setCostoCorso(double costoCorso) {
		this.costoCorso = costoCorso;
	}

	public String getCommentiCorso() {
		return commentiCorso;
	}

	public void setCommentiCorso(String commentiCorso) {
		this.commentiCorso = commentiCorso;
	}

	public String getAulaCorso() {
		return aulaCorso;
	}

	public void setAulaCorso(String aulaCorso) {
		this.aulaCorso = aulaCorso;
	}

}
