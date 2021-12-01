package com.gestionecorsi.businesscomponent.model;

/*Autore:Carraro Federico*/

public class Corsista {

	private long codCorsista;
	private String nomeCorsista;
	private String cognomeCorsista;
	private boolean precedentiFormativi;
	
	
	
	public long getCodCorsista() {
		return codCorsista;
	}
	public void setCodCorsista(long codCorsista) {
		this.codCorsista = codCorsista;
	}
	public String getNomeCorsista() {
		return nomeCorsista;
	}
	public void setNomeCorsista(String nomeCorsista) {
		this.nomeCorsista = nomeCorsista;
	}
	public String getCognomeCorsista() {
		return cognomeCorsista;
	}
	public void setCognomeCorsista(String cognomeCorsista) {
		this.cognomeCorsista = cognomeCorsista;
	}
	public boolean getPrecedentiFormativi() {
		return precedentiFormativi;
	}
	public void setPrecedentiFormativi(boolean precedentiFormativi) {
		this.precedentiFormativi = precedentiFormativi;
	}
	
}
