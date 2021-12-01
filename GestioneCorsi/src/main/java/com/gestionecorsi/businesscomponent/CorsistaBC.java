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
		conn=DBAccess.getConnection();/*apro la connessione*/
		idGen=CorsistaIDGenerator.getInstance();/*creo la sequenza*/
		
	}
	
	
	public void create(Corsista model) throws DAOException, ClassNotFoundException, IOException
	{
		
		try
		{
			/*creo il corsista da zero richiamando il metodo dal DAO*/
			model.setCodCorsista(idGen.getNextID());
			CorsistaDAO.getFactory().create(conn, model);
		}	
		catch(SQLException sql)
		{
			throw new DAOException(sql);
			
		}
		
	}
	
	
	public void delete(long codCorsista) throws DAOException
	{
		try
		{
			CorsistaDAO.getFactory().delete(conn, codCorsista);
			
						
		}
		catch(SQLException sql)
		{
			throw new DAOException(sql);
		
		}	
		
		
	}
	
	public Corsista[] getAll() throws DAOException
	{
		Corsista[] corsisti = null;
		try
		{
			corsisti=CorsistaDAO.getFactory().getAll(conn);
					
		}
		catch(SQLException sql)
		{
			throw new DAOException(sql);
			
		}
		
		return corsisti;
		
		
	}
	
	public Corsista getByID(long codCorsista) throws DAOException
	{
		try
		{
			return CorsistaDAO.getFactory().getByID(conn, codCorsista);
			
			
		}
		catch(SQLException sql)
		{
			throw new DAOException(sql);
			
		}
		
	}

}
