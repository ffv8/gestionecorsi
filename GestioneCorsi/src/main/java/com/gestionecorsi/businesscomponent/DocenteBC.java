package com.gestionecorsi.businesscomponent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.StringTokenizer;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dao.DocenteDAO;
import com.gestionecorsi.architecture.dbaccess.DBAccess;
import com.gestionecorsi.businesscomponent.model.Docente;

public class DocenteBC {
	private Connection conn;
	
	public DocenteBC() 
			throws ClassNotFoundException, DAOException, FileNotFoundException, IOException {
		
		conn = DBAccess.getConnection();
	}
	
	public Docente getByID(long codDocente) throws DAOException {
		try {
			return DocenteDAO.getFactory().getById(conn, codDocente);
		} catch(DAOException e) {
			throw new DAOException(e);
		}
	}
	
	public Docente getDocentePiuCorsi() throws DAOException {
		try {
			Docente[] docenti = DocenteDAO.getFactory().getAll(conn);
			Docente docente = docenti[0];
			StringTokenizer tokenizer = new StringTokenizer(docente.getCvDocente(), ",");
			int piuCorsi = tokenizer.countTokens();
			for(int i=1; i<docenti.length; i++) {	
				tokenizer = new StringTokenizer(docenti[i].getCvDocente(), ",");
				if(piuCorsi < tokenizer.countTokens())
					docente = docenti[i];
			}
			return docente;
		} catch(DAOException e) {
			throw new DAOException(e);
		}
	}
}
