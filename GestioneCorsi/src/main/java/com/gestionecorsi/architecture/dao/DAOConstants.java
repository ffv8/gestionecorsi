package com.gestionecorsi.architecture.dao;

public interface DAOConstants {
	// ---------------- Corsista
	String SELECT_CORSISTA = "select * from corsista";
	String DELETE_CORSISTA = "delete from corsista where cod_corsista=?";
	String SELECT_CORSISTA_BYID = "select * from corsista where cod_corsista=?";
	String SELECT_CORSISTA_SEQ = "select corsista_seq.nextval from dual";/* prendo il prossimo valore della sequenza */

	// ---------------- Docente
	String SELECT_DOCENTE_BYID = "select cod_docente, nome_docente, cognome_docente, cv_docente from docente where cod_docente = ?";
	String SELECT_DOCENTI = "select cod_docente, nome_docente, cognome_docente, cv_docente from docente";

	// ---------------- Corso
	String SELECT_CORSO = "select * from corso";
	String SELECT_CORSO_BYID = "select * from corso where cod_corso = ?";
	String DELETE_CORSO = "delete from corso where cod_corso = ?";
	String SELECT_INIZIO_ULTIMO_CORSO = "select data_inizio_corso from corso where data_inizio_corso = (select max(data_inizio_corso) from corso) fetch first 1 rows only";
	String SELECT_DURATA_CORSO = "select workingdays(?, ?) from dual";
	String SELECT_NUM_COMMENTI = "select count(commenti_corso) from corso where trim(commenti_corso) is not null";
	String SELECT_CORSO_SEQ = "select corso_seq.nextval from dual";
}
