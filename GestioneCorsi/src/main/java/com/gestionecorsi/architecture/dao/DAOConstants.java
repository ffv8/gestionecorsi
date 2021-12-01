package com.gestionecorsi.architecture.dao;

public interface DAOConstants {

	String SELECT_CORSISTA="select * from corsista";
	String DELETE_CORSISTA="delete from corsista where cod_corsista=?";
	String SELECT_CORSISTA_BYID="select * from corsista where cod_corsista=?";
	String SELECT_CORSISTA_SEQ="select corsista_seq.nextval from dual";/*prendo il prossimo valore della sequenza*/

}
