package com.gestionecorsi.architecture.dbaccess;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.gestionecorsi.architecture.dao.DAOException;


public class DBAccess{
	private static Connection conn;
	
	public static synchronized Connection getConnection() 
			throws ClassNotFoundException, DAOException, FileNotFoundException, IOException {

		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("properties/config.properties"); // siamo all'interno di un package, quindi si usa "/" e non "\\" per l'indirizzo
			Properties p = new Properties();
			p.load(input);
			
			Class.forName(p.getProperty("jdbcDriver"));
			conn = DriverManager.getConnection(
				p.getProperty("jdbcURL"), 
				p.getProperty("jdbcUsername"), 
				p.getProperty("jdbcPassword")); 

			conn.setAutoCommit(false);

		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
		return conn;
	}
	
	
	public static void closeConnection() throws DAOException {
		try {
			if(conn != null)
				conn.close();			
		}catch(SQLException exc) {
			throw new DAOException(exc);
		}
	}
}