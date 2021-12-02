package com.gestionecorsi.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gestionecorsi.businesscomponent.model.Docente;

public class DocenteDAO implements DAOConstants {
	public static DocenteDAO getFactory() throws DAOException {
		return new DocenteDAO();
	}
		
	public Docente getById(Connection conn, long codDocente) throws DAOException {
		PreparedStatement ps;
		Docente docente = null;
		try {
			ps = conn.prepareStatement(SELECT_DOCENTE_BYID);
			ps.setLong(1, codDocente);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				docente = new Docente();
				docente.setCodDocente(rs.getLong(1));
				docente.setNomeDocente(rs.getString(2));
				docente.setCognomeDocente(rs.getString(3));
				docente.setCvDocente(rs.getString(4));
			}
		} catch(SQLException exc) {
			throw new DAOException(exc);
		}
		
		return docente;
	}
	
	public Docente[] getAll(Connection conn) throws DAOException {
		Docente[] docenti = null;
		try {
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_DOCENTI);
			rs.last();
			docenti = new Docente[rs.getRow()];
			rs.beforeFirst();
			
			for(int i=0; rs.next(); i++) {
				Docente docente = new Docente();
				docente.setCodDocente(rs.getLong(1));
				docente.setNomeDocente(rs.getString(2));
				docente.setCognomeDocente(rs.getString(3));
				docente.setCvDocente(rs.getString(4));
				docenti[i] = docente;
			}
		} catch(SQLException exc) {
			throw new DAOException(exc);
		}
		
		return docenti;
	}
}
