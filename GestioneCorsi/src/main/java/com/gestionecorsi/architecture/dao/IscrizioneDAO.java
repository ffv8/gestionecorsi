package com.gestionecorsi.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;


import com.gestionecorsi.businesscomponent.model.Iscrizione;

public class IscrizioneDAO implements DAOConstants{
	private CachedRowSet rowSet;

	
	private IscrizioneDAO() throws DAOException {
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}
	
	public static IscrizioneDAO getFactory() throws DAOException {
		return new IscrizioneDAO();
	}
	
	
	
	public void create(Connection conn, Iscrizione model) throws DAOException {
		try {
			rowSet.setCommand(SELECT_ISCRIZIONE);
			rowSet.execute(conn);
			
			rowSet.moveToInsertRow();
			rowSet.updateLong(1, model.getCodCorsista());
			rowSet.updateLong(2, model.getCodCorso());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges();
			
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}
	
	
	
	public long[] getIscritti(Connection conn, long codCorso) throws DAOException{
		long[] iscritti = null;
		PreparedStatement ps;
		
		try {
			
			ps = conn.prepareStatement(SELECT_N_ISCRITTI_CORSO,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ps.setLong(1, codCorso);
			ResultSet rs = ps.executeQuery();
			
			rs.last();
			iscritti = new long[rs.getRow()]; // mi sono posizionato nell'ultima riga quindi conosco quanti record sono presenti
			rs.beforeFirst();
			for(int i=0; rs.next(); i++) {
				iscritti[i] = rs.getLong(1);
			}
			rs.close();
					
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
		return iscritti;
	}
	
	
	public long corsoFrequentato(Connection conn) throws DAOException {
		long corso = 0;
		
		try {
			
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_CORSO_FREQUENTATO);
			
			rs.beforeFirst();
			if(rs.next()) {
				corso = rs.getLong(1);
			}
			rs.close();
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
		return corso;
	}
}
