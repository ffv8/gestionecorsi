package com.gestionecorsi.businesscomponent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.businesscomponent.model.Amministratore;
import com.gestionecorsi.businesscomponent.model.Corso;
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

	// ---------------- Docente
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

	// ---------------- Corso
	public void createCorso(Corso model)
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		CorsoBC.getFactory().create(model);
	}

	public void deleteCorso(long codCorso)
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		CorsoBC.getFactory().delete(codCorso);
	}

	public Corso[] getCorsi()
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		return CorsoBC.getFactory().getAll();
	}
	
	public Corso getCorsoByID(long codCorso)
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		return CorsoBC.getFactory().getByID(codCorso);
	}
	
	public Date getInizioUltimoCorso()
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		return CorsoBC.getFactory().getInizioUltimoCorso();
	}
	
	public double getDurataMedia()
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		return CorsoBC.getFactory().getDurataMedia();
	}
	
	public int getNumeroCommenti()
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		return CorsoBC.getFactory().getNumeroCommenti();
	}
	
	// ---------------- Corsista
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

	// ---------------- Amministratore
	public Amministratore getAmministratoreByID(long codAdmin)
			throws ClassNotFoundException, DAOException, FileNotFoundException, IOException {
		AmministratoreBC aBC = new AmministratoreBC();
		return aBC.getById(codAdmin);
	}
}
