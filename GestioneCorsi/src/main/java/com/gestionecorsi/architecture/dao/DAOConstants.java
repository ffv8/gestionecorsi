package com.gestionecorsi.architecture.dao;

public interface DAOConstants {

// ----- ISCRIZIONE -----
	String SELECT_ISCRIZIONE = "select * from iscrizione";
	String SELECT_N_ISCRITTI_CORSO = "select cod_corsista from iscrizione where cod_corso = ?";
	String SELECT_CORSO_FREQUENTATO = "select cod_corso from iscrizione group by cod_corso order by count(cod_corso) desc";



}
