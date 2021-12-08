package com.gestionecorsi.businesscomponent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gestionecorsi.architecture.dao.CorsistaDAO;
import com.gestionecorsi.architecture.dao.CorsoDAO;
import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dao.IscrizioneDAO;
import com.gestionecorsi.architecture.dbaccess.DBAccess;
import com.gestionecorsi.businesscomponent.model.Corsista;
import com.gestionecorsi.businesscomponent.model.Corso;
import com.gestionecorsi.businesscomponent.model.Iscrizione;

public class IscrizioneBC {
	private Connection conn;
	
	private IscrizioneBC() throws ClassNotFoundException, DAOException, FileNotFoundException, IOException {

	}
	
	public static IscrizioneBC getFactory() throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		return new IscrizioneBC();
	}
	
	
	public void create(long codCorso, long codCorsista)
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		conn = DBAccess.getConnection();
		try {
			Iscrizione model = new Iscrizione();
			model.setCodCorsista(codCorsista);
			model.setCodCorso(codCorso);
			IscrizioneDAO.getFactory().create(conn, model);
		}catch(SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
	}
	
	
	public Corsista[] getIscritti(long codCorso)
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException{
		conn = DBAccess.getConnection();
		
		Corsista[] iscritti = null;
		long[] iscrittiCod = null;
		
		try {
			
			iscrittiCod = IscrizioneDAO.getFactory().getIscritti(conn, codCorso);
			
		}catch(SQLException sql) {
			throw new DAOException(sql);
		}
		
		iscritti = new Corsista[iscrittiCod.length];
		try {
			
			for(int i=0; i<iscrittiCod.length; i++) {
				iscritti[i] = CorsistaDAO.getFactory().getByID(conn, iscrittiCod[i]);
			}
			
		}catch(SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
		return iscritti;
	}

	
	public Corso[] getIscrizioniCorsista(long codCorsista)
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException {
		conn = DBAccess.getConnection();
		Corso[] iscrizioni = null;
		try {
			iscrizioni = IscrizioneDAO.getFactory().getIscrizioniCorsista(conn, codCorsista);
		}catch(SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
		return iscrizioni;
	}
	
	
	public Corso[] getCorsiDisponibili()
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException{
		conn = DBAccess.getConnection();
		
		Corso[] corsiAll = null;
		ArrayList<Corso> corsiDisponibili = new ArrayList<Corso>();
		long[] iscrittiCod = null;

		try {
			
			corsiAll = CorsoDAO.getFactory().getAll(conn);
			
		}catch(SQLException sql) {
			throw new DAOException(sql);
		}
		
		
		
		try {
			
			for(int i=0; i<corsiAll.length; i++) {
				iscrittiCod = IscrizioneDAO.getFactory().getIscritti(conn, corsiAll[i].getCodCorso());
				if(iscrittiCod.length < 12) {
					corsiDisponibili.add(corsiAll[i]);
				}
			}
			
		}catch(SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
		
        Corso[] corsi = new Corso[corsiDisponibili.size()];
        corsi = corsiDisponibili.toArray(corsi);
		
		return corsi;
	}

	
	
	public Corso getCorsoPiuFrequentato()
			throws DAOException, ClassNotFoundException, FileNotFoundException, IOException{
		conn = DBAccess.getConnection();

		Corso corsoFrequentato = new Corso();
		long corsoCod = 0;
		
		try {
			
			corsoCod = IscrizioneDAO.getFactory().getCorsoPiuFrequentato(conn);
			
		}catch(SQLException sql) {
			throw new DAOException(sql);
		}
		
		try {
			
			corsoFrequentato = CorsoDAO.getFactory().getByID(conn, corsoCod);
			
		}catch(SQLException sql) {
			throw new DAOException(sql);
		} finally {
			DBAccess.closeConnection();
		}
		
		return corsoFrequentato;
	}

	
}
