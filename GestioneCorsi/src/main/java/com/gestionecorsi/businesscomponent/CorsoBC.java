package com.gestionecorsi.businesscomponent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.gestionecorsi.architecture.dao.CorsoDAO;
import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dbaccess.DBAccess;
import com.gestionecorsi.businesscomponent.idgenerator.CorsoIDGenerator;
import com.gestionecorsi.businesscomponent.model.Corso;

public class CorsoBC {
	private Connection conn;
	private CorsoIDGenerator idGen;

	private CorsoBC() throws ClassNotFoundException, DAOException, FileNotFoundException, IOException {
		conn = DBAccess.getConnection();
		idGen = CorsoIDGenerator.getInstance();
	}

	public static CorsoBC getFactory() throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		return new CorsoBC();
	}

	public void create(Corso model) throws DAOException, ClassNotFoundException, IOException {
		try {
			model.setCodCorso(idGen.getNextID());
			CorsoDAO.getFactory().create(conn, model);

		} catch (SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
	}

	public void delete(long codCorso) throws DAOException {
		try {
			CorsoDAO.getFactory().delete(conn, codCorso);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
	}

	public Corso[] getAll() throws DAOException {
		Corso[] corsi = null;
		try {
			corsi = CorsoDAO.getFactory().getAll(conn);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
		return corsi;
	}

	public Corso getByID(long codCorso) throws DAOException {
		try {
			return CorsoDAO.getFactory().getByID(conn, codCorso);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
	}

	public Date getInizioUltimoCorso() throws DAOException {
		try {
			return CorsoDAO.getFactory().getInizioUltimoCorso(conn);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
	}

	public double getDurataMedia() throws DAOException {
		double media = 0.0;
		try {
			Corso[] corsi = CorsoDAO.getFactory().getAll(conn);
			for (Corso corso : corsi)
				media += CorsoDAO.getFactory().getDurataGiorni(conn, corso.getCodCorso());
			media /= corsi.length;

		} catch (SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
		return media;
	}

	public int getNumeroCommenti() throws DAOException {
		try {
			return CorsoDAO.getFactory().getNumeroCommenti(conn);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
	}

}
