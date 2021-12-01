package com.gestionecorsi.businesscomponent.model;

public class Iscrizione {
	private long codCorsista;
	private long codCorso;
	
	
	
	public long getCodCorsista() {
		return codCorsista;
	}
	public void setCodCorsista(long codCorsista) {
		this.codCorsista = codCorsista;
	}
	
	public long getCodCorso() {
		return codCorso;
	}
	public void setCodCorso(long codCorso) {
		this.codCorso = codCorso;
	}
	
	
	
	@Override
	public String toString() {
		return "iscrizione [codCorsista=" + codCorsista + ", codCorso=" + codCorso + "]";
	}

}
