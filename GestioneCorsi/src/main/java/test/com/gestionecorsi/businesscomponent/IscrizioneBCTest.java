package test.com.gestionecorsi.businesscomponent;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dbaccess.DBAccess;
import com.gestionecorsi.businesscomponent.CorsistaBC;
import com.gestionecorsi.businesscomponent.CorsoBC;
import com.gestionecorsi.businesscomponent.IscrizioneBC;
import com.gestionecorsi.businesscomponent.model.Corsista;
import com.gestionecorsi.businesscomponent.model.Corso;
import com.gestionecorsi.businesscomponent.model.Iscrizione;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IscrizioneBCTest {
	private static Corso corso1;
	private static Corsista corsista1;
	private static Corsista corsista2;
	private static Iscrizione iscrizione1;
	private static Iscrizione iscrizione2;
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
		
		corsista1 = new Corsista();
		corsista1.setCodCorsista(19);
		corsista1.setNomeCorsista("Mario");
		corsista1.setCognomeCorsista("Verdi");
		corsista1.setPrecedentiFormativi(true);
		
		corsista2 = new Corsista();
		corsista2.setCodCorsista(20);
		corsista2.setNomeCorsista("Mario");
		corsista2.setCognomeCorsista("Verdi");
		corsista2.setPrecedentiFormativi(true);
		
		iscrizione1 = new Iscrizione();
		iscrizione1.setCodCorsista(corsista1.getCodCorsista());
		iscrizione1.setCodCorso(corso1.getCodCorso());
		
		iscrizione2 = new Iscrizione();
		iscrizione2.setCodCorsista(corsista2.getCodCorsista());
		iscrizione2.setCodCorso(corso1.getCodCorso());
	}

	
	
	@Test
	@Order(1)
	void testCreate() {
		try {
			
			CorsistaBC cBC = new CorsistaBC();
			
			CorsoBC.getFactory().create(corso1);
			System.out.println("Creati corsi");
			cBC.create(corsista1);
			cBC.create(corsista2);
			System.out.println("Creati corsisti");
			IscrizioneBC.getFactory().create(corso1.getCodCorso(), corsista1.getCodCorsista());
			IscrizioneBC.getFactory().create(corso1.getCodCorso(), corsista2.getCodCorsista());
			System.out.println("Create iscrizioni");
			
		}catch(DAOException | ClassNotFoundException | IOException exc) {
			exc.printStackTrace();
			fail(exc.getMessage());
		}
	}

	
	@Test
	@Order(2)
	void testGetIscritti() {
		try {
			
			Corsista[] corsisti = IscrizioneBC.getFactory().getIscritti(corso1.getCodCorso());
			System.out.println(corsisti.length);
			assertNotNull(corsisti);

		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	
	@Test
	@Order(3)
	void testGetCorsiDisponibili() {
		try {
			
			Corso[] corsi = IscrizioneBC.getFactory().getCorsiDisponibili();
			System.out.println(corsi.length);
			assertNotNull(corsi);

		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	
	@Test
	@Order(4)
	void testGetCorsoPiuFrequentato() {
		try {
			
			Corso corso = IscrizioneBC.getFactory().getCorsoPiuFrequentato();
			System.out.println(corso.getAulaCorso());
			assertNotNull(corso);

		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		try {
			
			CorsoBC.getFactory().delete(corso1.getCodCorso());
			System.out.println("Eliminato corso1");
			System.out.println(corsista1.getCodCorsista());
			System.out.println(corsista2.getCodCorsista());
			
			PreparedStatement pstmt1 = conn.prepareStatement("delete from corsista where cod_corsista = ?");
			pstmt1.setLong(1, corsista1.getCodCorsista());
			PreparedStatement pstmt2 = conn.prepareStatement("delete from corsista where cod_corsista = ?");
			pstmt2.setLong(1, corsista2.getCodCorsista());
			pstmt1.execute();
			pstmt2.execute();
			conn.commit();
			System.out.println("Eliminati corsisti");

		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		corso1 = null;
		corsista1 = null;
		corsista2 = null;
		iscrizione1 = null;
		iscrizione2 = null;
		
	}
}
