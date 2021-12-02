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

/*autore Carraro Federico*/
public class CorsistaIDGenerator implements IDGeneratorInterface,DAOConstants {
	
	/*il generatore di id deve essere unico,quindi implemento il singleton*/
	private static CorsistaIDGenerator istanza;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private CorsistaIDGenerator() throws ClassNotFoundException, DAOException, FileNotFoundException, IOException
	{
		conn=DBAccess.getConnection();/*stabilisco la connessione col db*/
		
		
	}
	
	public static CorsistaIDGenerator getInstance() throws ClassNotFoundException, DAOException, FileNotFoundException, IOException
	{
		if(istanza==null)
			istanza = new CorsistaIDGenerator();
		return istanza;
	}
	
	@Override
	public long getNextID() throws DAOException, ClassNotFoundException, IOException {
		long id=0;
		try
		{
			stmt=conn.createStatement();
			rs=stmt.executeQuery(SELECT_CORSISTA_SEQ);
			rs.next();
			id=rs.getLong(1);
			
					
		}
		catch(SQLException sql)
		{
			throw new DAOException(sql);
			
		}
		
		return id;
	}

}
