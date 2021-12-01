package com.gestionecorsi.businesscomponent.model;

/*Autore:Carraro Federico*/

public class Corsista {

	private long cod_corsista;
	private String nome_corsista;
	private String cognome_corsista;
	private byte precenti_formativi;
	
	
	
	public long getCod_corsista() {
		return cod_corsista;
	}
	public void setCod_corsista(long cod_corsista) {
		this.cod_corsista = cod_corsista;
	}
	public String getNome_corsista() {
		return nome_corsista;
	}
	public void setNome_corsista(String nome_corsista) {
		this.nome_corsista = nome_corsista;
	}
	public String getCognome_corsista() {
		return cognome_corsista;
	}
	public void setCognome_corsista(String cognome_corsista) {
		this.cognome_corsista = cognome_corsista;
	}
	public byte getPrecenti_formativi() {
		return precenti_formativi;
	}
	public void setPrecenti_formativi(byte precenti_formativi) {
		this.precenti_formativi = precenti_formativi;
	}
	
}
