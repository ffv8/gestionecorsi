package com.gestionecorsi.businesscomponent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.gestionecorsi.architecture.dao.CorsoDAO;
import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dbaccess.DBAccess;
import com.gestionecorsi.businesscomponent.idgenerator.CorsoIDGenerator;
import com.gestionecorsi.businesscomponent.model.Corso;

public class CorsoBC {
	private Connection conn;
	private CorsoIDGenerator idGen;

	private CorsoBC() throws ClassNotFoundException, DAOException, FileNotFoundException, IOException {
		idGen = CorsoIDGenerator.getInstance();
	}

	public static CorsoBC getFactory()
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		return new CorsoBC();
	}

	public void create(Corso model)
			throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
		try {
			model.setCodCorso(idGen.getNextID());
			// TODO trim eventuali spazi iniziali e finali nei commenti
			CorsoDAO.getFactory().create(conn, model);

		} catch (SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
	}

	public void delete(long codCorso)
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		conn = DBAccess.getConnection();
		try {
			CorsoDAO.getFactory().delete(conn, codCorso);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
	}

	public Corso[] getAll()
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		conn = DBAccess.getConnection();
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

	public Corso getByID(long codCorso)
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		conn = DBAccess.getConnection();
		try {
			return CorsoDAO.getFactory().getByID(conn, codCorso);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
	}

	public Corso getUltimoCorso()
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		conn = DBAccess.getConnection();
		try {
			return CorsoDAO.getFactory().getUltimoCorso(conn);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
	}

	public double getDurataMedia()
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		conn = DBAccess.getConnection();
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

	public int getNumeroCommenti()
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		conn = DBAccess.getConnection();
		try {
			return CorsoDAO.getFactory().getNumeroCommenti(conn);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
	}

}
