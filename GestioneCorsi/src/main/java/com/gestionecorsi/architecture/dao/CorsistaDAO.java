package com.gestionecorsi.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.gestionecorsi.businesscomponent.model.Corsista;


/*Autore:Carraro Federico*/
public class CorsistaDAO implements DAOConstants {
	
	private CachedRowSet rowSet;
	
	public static CorsistaDAO getFactory() throws DAOException {
		return new CorsistaDAO();
	}
	
	private CorsistaDAO() throws DAOException
	{
		try
		{
			rowSet=RowSetProvider.newFactory().createCachedRowSet();/*creo il rowset in cache*/
			
			
		}
		catch(SQLException sql)
		{
			throw new DAOException(sql);
			
		}
		
		
	}
	
	public void create(Connection conn, Corsista model) throws DAOException {
		try
		{	/*per gli inserimenti uso il cachedRowSet*/
			rowSet.setCommand(SELECT_CORSISTA);/*imposto la query*/
			rowSet.execute(conn);/*eseguo la query*/
			rowSet.moveToInsertRow();/*mi posiziono sulla prima riga vuota*/
			rowSet.updateLong(1, model.getCodCorsista());
			rowSet.updateString(2, model.getNomeCorsista());
			rowSet.updateString(3,model.getCognomeCorsista());
			if(model.getPrecedentiFormativi() == true)	
				rowSet.updateInt(4,1);
			else
				rowSet.updateInt(4, 0);
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges();
			
		}
		catch(SQLException sql)
		{
			throw new DAOException(sql);
			
		}
	}
	
	
	public Corsista[] getAll(Connection conn) throws DAOException {
		Corsista[] corsisti =null;
		
		try
		{	/*siccome devo leggere i dati dell'ordine non uso rowSet*/
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = stmt.executeQuery(SELECT_CORSISTA);
			
			rs.last();/*mi posiziono sull'ultimo record*/
			
			corsisti = new Corsista[rs.getRow()]; /*getRow mi dice il numero di righe che ho*/
			
			rs.beforeFirst();/*riparto dall'inizio*/
			
			for(int i=0; rs.next();i++)
			{
				Corsista c= new Corsista();
				c.setCodCorsista(rs.getLong(1));
				c.setNomeCorsista(rs.getString(2));
				c.setCognomeCorsista(rs.getString(3));
				c.setPrecedentiFormativi(rs.getBoolean(4));
				
				corsisti[i] = c;
				
			}
			
			rs.close();
		}
		catch(SQLException sql)
		{
			throw new DAOException(sql);
			
		}	
		
		return corsisti;
	}

	
	public Corsista getByID(Connection conn, long codCorsista) throws DAOException {
		/*ricerca in base alla chiave primaria*/
		
		PreparedStatement ps;
		Corsista corsista =null;
		
		
		try
		{
			ps=conn.prepareStatement(SELECT_CORSISTA_BYID);
			ps.setLong(1, codCorsista);
			ResultSet rs =ps.executeQuery();
			if(rs.next())
			{
				corsista = new Corsista();
				corsista.setCodCorsista(rs.getLong(1));
				corsista.setNomeCorsista(rs.getString(2));
				corsista.setCognomeCorsista(rs.getString(3));
				corsista.setPrecedentiFormativi(rs.getBoolean(4));
				
			}
			
		}
		catch(SQLException sql)
		{
			throw new DAOException(sql);
		}	
		
		return corsista;
	}


}
