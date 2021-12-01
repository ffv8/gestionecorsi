package test.com.gestionecorsi.businesscomponent;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.businesscomponent.CorsoBC;
import com.gestionecorsi.businesscomponent.model.Corso;

@TestMethodOrder(OrderAnnotation.class)
class CorsoBCTest {
	private static Corso corso1;
	private static Corso corso2;
	private static Corso corso3;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		corso1 = new Corso();
		corso1.setCodDocente(105);
		corso1.setNomeCorso("Analisi I");
		corso1.setDataInizioCorso(new GregorianCalendar(2010, 9, 15).getTime());
		corso1.setDataFineCorso(new GregorianCalendar(2010, 12, 25).getTime());
		corso1.setCostoCorso(999.99);
		corso1.setCommentiCorso("Corso tosto");
		corso1.setAulaCorso("A103");

		corso2 = new Corso();
		corso2.setCodDocente(104);
		corso2.setNomeCorso("Swift");
		corso2.setDataInizioCorso(new GregorianCalendar(2020, 9, 15).getTime());
		corso2.setDataFineCorso(new GregorianCalendar(2020, 12, 25).getTime());
		corso2.setCostoCorso(399.99);
		// senza commenti
		corso2.setAulaCorso("A101");

		corso3 = new Corso();
		corso3.setCodDocente(103);
		corso3.setNomeCorso("Java");
		corso3.setDataInizioCorso(new GregorianCalendar(2019, 9, 15).getTime());
		corso3.setDataFineCorso(new GregorianCalendar(2020, 12, 25).getTime());
		corso3.setCostoCorso(0.00);
		corso3.setCommentiCorso("Commento2");
		corso3.setAulaCorso("Zoom");
	}

	@Test
	@Order(1)
	void testCreate() {
		try {
			CorsoBC.getFactory().create(corso1);
			System.out.println("Creato corso1");
			CorsoBC.getFactory().create(corso2);
			System.out.println("Creato corso2");
			CorsoBC.getFactory().create(corso3);
			System.out.println("Creato corso3");

		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Order(2)
	void testGetByID() {
		try {
			Corso c = CorsoBC.getFactory().getByID(corso1.getCodCorso());
			System.out.printf("  %s, %s\n", c.getNomeCorso(), c.getAulaCorso());
			assertNotNull(c);

		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Order(3)
	void testGetAll() {
		try {
			Corso[] corsi = CorsoBC.getFactory().getAll();
			assertNotNull(corsi);

		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Order(4)
	void testGetInizioUltimoCorso() {
		try {
			Date data = CorsoBC.getFactory().getInizioUltimoCorso();
			assertNotNull(data);

		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Order(5)
	void testGetDurataMedia() {
		try {
			double durata = CorsoBC.getFactory().getDurataMedia();
			System.out.println("Durata media: " + durata);
			assertNotEquals(0.0, durata);

		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Order(6)
	void testGetNumeroCommenti() {
		try {
			int n_commenti = CorsoBC.getFactory().getNumeroCommenti();
			assertTrue(n_commenti > 0);

		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Order(7)
	void testDelete() {
		try {
			CorsoBC.getFactory().delete(corso1.getCodCorso());
			System.out.println("Eliminato corso1");
			CorsoBC.getFactory().delete(corso2.getCodCorso());
			System.out.println("Eliminato corso2");
			CorsoBC.getFactory().delete(corso3.getCodCorso());
			System.out.println("Eliminato corso3");

		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		corso1 = null;
		corso2 = null;
		corso3 = null;
	}

}
