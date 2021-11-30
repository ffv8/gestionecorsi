package com.gestionecorsi.architecture.dao;

public interface DAOConstants {
	// ---------------- Corso
	String SELECT_CORSO = "select * from corso";
	String SELECT_CORSO_BYID = "select * from corso where cod_corso = ?";
	String DELETE_CORSO = "delete from corso where cod_corso = ?";
	// TODO aggiungi query SELECT_CORSO_ULTIMO
	// TODO aggiungi query SELECT_DURATA_CORSO
	// TODO aggiungi query SELECT_NUM_COMMENTI
}
