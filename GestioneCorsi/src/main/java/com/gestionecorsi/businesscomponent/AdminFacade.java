package com.gestionecorsi.businesscomponent;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.businesscomponent.model.Corsista;

public class AdminFacade {
	
	public void createCorsista(Corsista model) throws ClassNotFoundException, DAOException, FileNotFoundException, IOException
	{
		CorsistaBC c =new CorsistaBC();
		
		c.create(model);
		
	}
	
	public Corsista[] getCorsisti() throws ClassNotFoundException, DAOException, FileNotFoundException, IOException
	{
		CorsistaBC c = new CorsistaBC();
				
		return c.getAll();
		
	}
	
	public Corsista getCorsistaByID(long codCorsista) throws DAOException, ClassNotFoundException, FileNotFoundException, IOException
	{
		CorsistaBC c =new CorsistaBC();
		return c.getByID(codCorsista);
	}

}
