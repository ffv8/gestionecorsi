package com.gestionecorsi.businesscomponent.idgenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gestionecorsi.architecture.dao.DAOConstants;
import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dbaccess.DBAccess;

// TODO implementa interfaccia IDGeneratorInterface
public class CorsoIDGenerator implements DAOConstants {
	private static CorsoIDGenerator instance;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	private CorsoIDGenerator() throws ClassNotFoundException, DAOException, FileNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}

	public static CorsoIDGenerator getInstance()
			throws ClassNotFoundException, DAOException, FileNotFoundException, IOException {
		if (instance == null)
			instance = new CorsoIDGenerator();
		return instance;
	}

	// TODO implementa interfaccia IDGeneratorInterface
	// @Override
	public long getNextID() throws DAOException, ClassNotFoundException, IOException {
		long id = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_CORSO_SEQ);
			rs.next();
			id = rs.getLong(1);

		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return id;
	}
}
