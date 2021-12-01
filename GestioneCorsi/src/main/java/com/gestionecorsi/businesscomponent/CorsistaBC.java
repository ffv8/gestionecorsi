package com.gestionecorsi.businesscomponent;

import java.sql.Connection;
import java.sql.SQLException;

import com.gestionecorsi.architecture.dao.CorsistaDAO;
import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dbaccess.DBAccess;
import com.gestionecorsi.businesscomponent.model.Corsista;

/*autore Carraro Federico*/

public class CorsistaBC {
	
	private Connection conn;
	private CorsistaIDGenerator idGen;
	
	public CorsistaBC()
	{
		conn=DBAccess.getConnection();/*apro la connessione*/
		idGen=CorsistaIDGenerator.getInstance();/*creo la sequenza*/
		
	}
	
	public void create(Corsista model)
	{
		/*creo il corsista da zero richiamando il metodo dal DAO*/
		model.setIdArticolo(idGen.getNextId());
		CorsistaDAO.getFactory().create(conn, model);
		
		
	}
	
	
	public void delete(long codCorsista)
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
	
	public Corsista[] getAll()
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
	
	public Corsista getByID(long codCorsista)
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
