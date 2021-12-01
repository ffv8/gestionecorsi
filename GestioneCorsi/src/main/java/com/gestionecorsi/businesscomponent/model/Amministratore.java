package com.gestionecorsi.businesscomponent.model;

public class Amministratore {
	private String nomeAdmin;
	private String cognomeAdmin;
	private long codAdmin;
	private String username;
	private String password;

	public String getNomeAdmin() {
		return nomeAdmin;
	}

	public void setNomeAdmin(String nomeAdmin) {
		this.nomeAdmin = nomeAdmin;
	}

	public String getCognomeAdmin() {
		return cognomeAdmin;
	}

	public void setCognomeAdmin(String cognomeAdmin) {
		this.cognomeAdmin = cognomeAdmin;
	}

	public long getCodAdmin() {
		return codAdmin;
	}

	public void setCodAdmin(long codAdmin) {
		this.codAdmin = codAdmin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Amministratore [nomeAdmin=" + nomeAdmin + ", cognomeAdmin=" + cognomeAdmin + ", codAdmin=" + codAdmin
				+ ", username=" + username + ", password=" + password + "]";
	}
}
