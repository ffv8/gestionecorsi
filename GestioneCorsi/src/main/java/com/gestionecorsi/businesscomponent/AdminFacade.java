package com.gestionecorsi.businesscomponent;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.businesscomponent.model.Docente;

public class AdminFacade {
	private static AdminFacade istanza;
	
	private AdminFacade() {}
	
	public static AdminFacade getInstance() {
		if(istanza == null)
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
}
