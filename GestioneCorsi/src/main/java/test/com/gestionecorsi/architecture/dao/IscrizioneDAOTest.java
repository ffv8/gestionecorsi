package test.com.gestionecorsi.architecture.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gestionecorsi.architecture.dao.CorsistaDAO;
import com.gestionecorsi.architecture.dao.CorsoDAO;
import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dao.IscrizioneDAO;
import com.gestionecorsi.architecture.dbaccess.DBAccess;
import com.gestionecorsi.businesscomponent.model.Corsista;
import com.gestionecorsi.businesscomponent.model.Corso;
import com.gestionecorsi.businesscomponent.model.Iscrizione;

//TODO aggiungi test per getIscrizioniCorsista
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IscrizioneDAOTest {
	private static Corso corso1;
	private static Corso corso2;
	private static Corsista corsista1;
	private static Corsista corsista2;
	private static Corsista corsista3;
	private static Iscrizione iscrizione1;
	private static Iscrizione iscrizione2;
	private static Iscrizione iscrizione3;
	private static Connection conn;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		conn = DBAccess.getConnection();

		corso1 = new Corso();
		corso1.setCodCorso(1);
		corso1.setCodDocente(105);
		corso1.setNomeCorso("Analisi I");
		corso1.setDataInizioCorso(new GregorianCalendar(2010, 9, 15).getTime());
		corso1.setDataFineCorso(new GregorianCalendar(2010, 12, 25).getTime());
		corso1.setCostoCorso(999.99);
		corso1.setCommentiCorso("Corso tosto");
		corso1.setAulaCorso("A103");
		
		corso2 = new Corso();
		corso2.setCodCorso(2);
		corso2.setCodDocente(104);
		corso2.setNomeCorso("ASP");
		corso2.setDataInizioCorso(new GregorianCalendar(2010, 10, 15).getTime());
		corso2.setDataFineCorso(new GregorianCalendar(2010, 12, 25).getTime());
		corso2.setCostoCorso(99.99);
		corso2.setCommentiCorso("Corso facile");
		corso2.setAulaCorso("A103");
		
		corsista1 = new Corsista();
		corsista1.setCodCorsista(11);
		corsista1.setNomeCorsista("Mario");
		corsista1.setCognomeCorsista("Verdi");
		corsista1.setPrecedentiFormativi(true);
		
		corsista2 = new Corsista();
		corsista2.setCodCorsista(12);
		corsista2.setNomeCorsista("Mario");
		corsista2.setCognomeCorsista("Verdi");
		corsista2.setPrecedentiFormativi(true);
		
		corsista3 = new Corsista();
		corsista3.setCodCorsista(13);
		corsista3.setNomeCorsista("Mario");
		corsista3.setCognomeCorsista("Verdi");
		corsista3.setPrecedentiFormativi(true);
		
		iscrizione1 = new Iscrizione();
		iscrizione1.setCodCorsista(corsista1.getCodCorsista());
		iscrizione1.setCodCorso(corso1.getCodCorso());
		
		iscrizione2 = new Iscrizione();
		iscrizione2.setCodCorsista(corsista2.getCodCorsista());
		iscrizione2.setCodCorso(corso1.getCodCorso());
		
		iscrizione3 = new Iscrizione();
		iscrizione3.setCodCorsista(corsista3.getCodCorsista());
		iscrizione3.setCodCorso(corso2.getCodCorso());
		
	}

	
	
	@Test
	@Order(1)
	void testCreate() {
		try {
			
			CorsoDAO.getFactory().create(conn, corso1);
			CorsoDAO.getFactory().create(conn, corso2);
			System.out.println("Creati corsi");
			CorsistaDAO.getFactory().create(conn, corsista1);
			CorsistaDAO.getFactory().create(conn, corsista2);
			CorsistaDAO.getFactory().create(conn, corsista3);
			System.out.println("Creati corsisti");
			IscrizioneDAO.getFactory().create(conn, iscrizione1);
			IscrizioneDAO.getFactory().create(conn, iscrizione2);
			IscrizioneDAO.getFactory().create(conn, iscrizione3);
			System.out.println("Create iscrizioni");
			
		}catch(DAOException exc) {
			exc.printStackTrace();
			fail(exc.getMessage());
		}

	}

	@Test
	@Order(2)
	void testGetIscritti() {
		try {
			
			long[] corsisti = IscrizioneDAO.getFactory().getIscritti(conn, corso1.getCodCorso());
			System.out.println(corsisti.length);
			assertNotNull(corsisti);

		} catch (DAOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Order(3)
	void testGetCorsoPiuFrequentato() {
		try {
			
			long corsoID = IscrizioneDAO.getFactory().getCorsoPiuFrequentato(conn);
			System.out.println(corsoID);
			assertNotEquals(0, corsoID);

		} catch (DAOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		try {
			CorsoDAO.getFactory().delete(conn, corso1.getCodCorso());
			System.out.println("Eliminato corso1");
			CorsoDAO.getFactory().delete(conn, corso2.getCodCorso());
			System.out.println("Eliminato corso2");
			PreparedStatement pstmt1 = conn.prepareStatement("delete from corsista where cod_corsista = ?");
			pstmt1.setLong(1, corsista1.getCodCorsista());
			PreparedStatement pstmt2 = conn.prepareStatement("delete from corsista where cod_corsista = ?");
			pstmt2.setLong(1, corsista2.getCodCorsista());
			PreparedStatement pstmt3 = conn.prepareStatement("delete from corsista where cod_corsista = ?");
			pstmt3.setLong(1, corsista3.getCodCorsista());
			pstmt1.execute();
			pstmt2.execute();
			pstmt3.execute();
			conn.commit();
			System.out.println("Eliminati corsisti");

		
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}

		corso1 = null;
		corso2 = null;
		corsista1 = null;
		corsista2 = null;
		corsista3 = null;
		iscrizione1 = null;
		iscrizione2 = null;
		iscrizione3 = null;
		DBAccess.closeConnection();
	}

}
