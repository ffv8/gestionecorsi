package com.gestionecorsi.businesscomponent;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.businesscomponent.model.Corsista;
import com.gestionecorsi.businesscomponent.model.Corso;

public class AdminFacade {
	private static AdminFacade istanza;
	
	private AdminFacade() {

	}
	
	public static AdminFacade getIstance() {
		if(istanza == null)
			istanza = new AdminFacade();
		return istanza;
	}
	
	
	// ----- Iscrizione
	public void createIscrizione(long codCorso, long codCorsista) throws ClassNotFoundException, DAOException, FileNotFoundException, IOException {
		IscrizioneBC iBC = new IscrizioneBC();
		iBC.create(codCorso, codCorsista);
	}
	public Corsista[] getIscrittiCorso(long codCorso) throws ClassNotFoundException, DAOException, FileNotFoundException, IOException {
		IscrizioneBC iBC = new IscrizioneBC();
		return iBC.getIscritti(codCorso);
	}
	public Corso[] getCorsiDisponibili() throws ClassNotFoundException, DAOException, FileNotFoundException, IOException {
		IscrizioneBC iBC = new IscrizioneBC();
		return iBC.getCorsiDisponibili();
	}
	
	
}
