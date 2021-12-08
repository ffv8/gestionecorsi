package com.gestionecorsi.businesscomponent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.gestionecorsi.architecture.dao.CorsistaDAO;
import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dbaccess.DBAccess;
import com.gestionecorsi.businesscomponent.idgenerator.CorsistaIDGenerator;
import com.gestionecorsi.businesscomponent.model.Corsista;

/*autore Carraro Federico*/

public class CorsistaBC {
	
	private Connection conn;
	private CorsistaIDGenerator idGen;
	
	public CorsistaBC() throws ClassNotFoundException, DAOException, FileNotFoundException, IOException
	{
		idGen=CorsistaIDGenerator.getInstance();/*creo la sequenza*/
		
	}
	
	
	public void create(Corsista model) throws DAOException, ClassNotFoundException, IOException
	{
		conn = DBAccess.getConnection();
		try
		{
			/*creo il corsista da zero richiamando il metodo dal DAO*/
			model.setCodCorsista(idGen.getNextID());
			CorsistaDAO.getFactory().create(conn, model);
		}	
		catch(SQLException sql)
		{
			throw new DAOException(sql);
			
		} finally {
			DBAccess.closeConnection();
		}
		
	}
	
	
	
	public Corsista[] getAll() throws DAOException, ClassNotFoundException, FileNotFoundException, IOException
	{
		conn = DBAccess.getConnection();
		Corsista[] corsisti = null;
		try
		{
			corsisti=CorsistaDAO.getFactory().getAll(conn);
					
		}
		catch(SQLException sql)
		{
			throw new DAOException(sql);
			
		} finally {
			DBAccess.closeConnection();
		}
		
		return corsisti;
		
		
	}
	
	public Corsista getByID(long codCorsista) throws DAOException, ClassNotFoundException, FileNotFoundException, IOException
	{
		conn = DBAccess.getConnection();
		try
		{
			return CorsistaDAO.getFactory().getByID(conn, codCorsista);
			
			
		}
		catch(SQLException sql)
		{
			throw new DAOException(sql);
			
		} finally {
			DBAccess.closeConnection();
		}
		
	}

}
