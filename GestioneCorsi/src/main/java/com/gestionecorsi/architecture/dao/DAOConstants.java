package com.gestionecorsi.architecture.dao;

public interface DAOConstants {
	String SELECT_DOCENTE_BYID = "select cod_docente, nome_docente, cognome_docente, cv_docente from docente where cod_docente = ?";
	String SELECT_DOCENTI = "select cod_docente, nome_docente, cognome_docente, cv_docente from docente";
}
