package com.gestionecorsi.businesscomponent;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.businesscomponent.model.Amministratore;
import com.gestionecorsi.businesscomponent.model.Corsista;
import com.gestionecorsi.businesscomponent.model.Docente;

public class AdminFacade {
	private static AdminFacade istanza;

	private AdminFacade() {
	}

	public static AdminFacade getInstance() {
		if (istanza == null)
			istanza = new AdminFacade();
		return istanza;
	}

	public Docente getDocenteByID(long codDocente)
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {

		DocenteBC dBC = new DocenteBC();
		return dBC.getByID(codDocente);
	}

	public Docente getDocentePiuCorsi()
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {

		DocenteBC dBC = new DocenteBC();
		return dBC.getDocentePiuCorsi();
	}

	public Amministratore getAmministratoreByID(long codAdmin)
			throws ClassNotFoundException, DAOException, FileNotFoundException, IOException {
		AmministratoreBC aBC = new AmministratoreBC();
		return aBC.getById(codAdmin);
	}
	
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
