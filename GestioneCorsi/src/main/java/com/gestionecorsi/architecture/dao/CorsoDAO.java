package com.gestionecorsi.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.gestionecorsi.businesscomponent.model.Corso;

public class CorsoDAO implements DAOConstants {
	private CachedRowSet rowSet;

	private CorsoDAO() throws DAOException {
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public static CorsoDAO getFactory() throws DAOException {
		return new CorsoDAO();
	}

	public void create(Connection conn, Corso model) throws DAOException {
		try {
			rowSet.setCommand(SELECT_CORSO);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
			rowSet.updateLong(1, model.getCodCorso());
			rowSet.updateLong(2, model.getCodDocente());
			rowSet.updateString(3, model.getNomeCorso());
			rowSet.updateDate(4, new java.sql.Date(model.getDataInizioCorso().getTime()));
			rowSet.updateDate(5, new java.sql.Date(model.getDataFineCorso().getTime()));
			rowSet.updateDouble(6, model.getCostoCorso());
			rowSet.updateString(7, model.getCommentiCorso());
			rowSet.updateString(8, model.getAulaCorso());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges();

		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public void delete(Connection conn, long codCorso) throws DAOException {
		try {
			PreparedStatement pstmt = conn.prepareStatement(DELETE_CORSO);
			pstmt.setLong(1, codCorso);
			pstmt.execute();
			conn.commit();

		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public Corso[] getAll(Connection conn) throws DAOException {
		Corso[] corsi = null;
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_CORSO);
			rs.last();
			corsi = new Corso[rs.getRow()];
			rs.beforeFirst();

			for (int i = 0; rs.next(); i++) {
				Corso corso = new Corso();
				corso.setCodCorso(rs.getLong(1));
				corso.setCodDocente(rs.getLong(2));
				corso.setNomeCorso(rs.getString(3));
				corso.setDataInizioCorso(new java.util.Date(rs.getDate(4).getTime()));
				corso.setDataFineCorso(new java.util.Date(rs.getDate(5).getTime()));
				corso.setCostoCorso(rs.getDouble(6));
				corso.setCommentiCorso(rs.getString(7));
				corso.setAulaCorso(rs.getString(8));
				corsi[i] = corso;
			}
			rs.close();

		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return corsi;
	}

	public Corso getByID(Connection conn, long codCorso) throws DAOException {
		Corso corso = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(SELECT_CORSO_BYID);
			pstmt.setLong(1, codCorso);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				corso = new Corso();
				corso.setCodCorso(rs.getLong(1));
				corso.setCodDocente(rs.getLong(2));
				corso.setNomeCorso(rs.getString(3));
				corso.setDataInizioCorso(new java.util.Date(rs.getDate(4).getTime()));
				corso.setDataFineCorso(new java.util.Date(rs.getDate(5).getTime()));
				corso.setCostoCorso(rs.getDouble(6));
				corso.setCommentiCorso(rs.getString(7));
				corso.setAulaCorso(rs.getString(8));
			}
			rs.close();

		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return corso;
	}

	public Corso getUltimoCorso(Connection conn) throws DAOException {
		Corso corso = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ULTIMO_CORSO);
			if (rs.next())
				corso = getByID(conn, rs.getLong(1));
			rs.close();

		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return corso;
	}

	public int getDurataGiorni(Connection conn, long codCorso) throws DAOException {
		int giorni = 0;
		try {
			Corso corso = getByID(conn, codCorso);
			if (corso != null) {
				Date inizio = corso.getDataInizioCorso();
				Date fine = corso.getDataFineCorso();

				PreparedStatement pstmt = conn.prepareStatement(SELECT_DURATA_CORSO);
				pstmt.setDate(1, new java.sql.Date(inizio.getTime()));
				pstmt.setDate(2, new java.sql.Date(fine.getTime()));
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					giorni = rs.getInt(1);
				rs.close();
			}

		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return giorni;
	}

	public int getNumeroCommenti(Connection conn) throws DAOException {
		int n_commenti = 0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_NUM_COMMENTI);
			if (rs.next())
				n_commenti = rs.getInt(1);
			rs.close();

		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return n_commenti;
	}

}
