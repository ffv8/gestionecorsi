package com.gestionecorsi.businesscomponent.model;

public class Docente {
	private long codDocente;
	private String nomeDocente;
	private String cognomeDocente;
	private String cvDocente;
	
	public long getCodDocente() {
		return codDocente;
	}
	
	public void setCodDocente(long codDocente) {
		this.codDocente = codDocente;
	}
	
	public String getNomeDocente() {
		return nomeDocente;
	}
	
	public void setNomeDocente(String nomeDocente) {
		this.nomeDocente = nomeDocente;
	}
	
	public String getCognomeDocente() {
		return cognomeDocente;
	}
	
	public void setCognomeDocente(String cognomeDocente) {
		this.cognomeDocente = cognomeDocente;
	}
	
	public String getCvDocente() {
		return cvDocente;
	}
	
	public void setCvDocente(String cvDocente) {
		this.cvDocente = cvDocente;
	}
	
	@Override
	public String toString() {
		return "Docente [codDocente=" + codDocente + ", nomeDocente=" + nomeDocente + ", cognomeDocente="
				+ cognomeDocente + ", cvDocente=" + cvDocente + "]";
	}
}
