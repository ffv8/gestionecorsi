package com.gestionecorsi.businesscomponent.idgenerator;

import java.io.IOException;

import com.gestionecorsi.architecture.dao.DAOException;

public interface IDGeneratorInterface {

	long getNextID() throws DAOException, ClassNotFoundException, IOException; 
	
}
