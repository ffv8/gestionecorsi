package test.com.gestionecorsi.businesscomponent;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.gestionecorsi.businesscomponent.AdminFacade;
import com.gestionecorsi.businesscomponent.model.Corsista;
import com.gestionecorsi.businesscomponent.model.Corso;
import com.gestionecorsi.businesscomponent.model.Docente;

@TestMethodOrder(OrderAnnotation.class)
class AdminFacadeTest {
	// ---------------- Corso
	private static Corso corso1;
	private static Corso corso2;
	private static Corso corso3;
	// ---------------- Corsista
	private static Corsista corsista;
	// ---------------- Docente
	private static Docente docente;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// ---------------- Corso
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

	// ---------------- Corso
	@Test
	@Order(1)
	void testCreate() {
		try {
			AdminFacade.getInstance().createCorso(corso1);
			System.out.println("Creato corso1");
			AdminFacade.getInstance().createCorso(corso2);
			System.out.println("Creato corso2");
			AdminFacade.getInstance().createCorso(corso3);
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
			Corso c = AdminFacade.getInstance().getCorsoByID(corso1.getCodCorso());
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
			Corso[] corsi = AdminFacade.getInstance().getCorsi();
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
			Date data = AdminFacade.getInstance().getInizioUltimoCorso();
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
			double durata = AdminFacade.getInstance().getDurataMedia();
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
			int n_commenti = AdminFacade.getInstance().getNumeroCommenti();
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
			AdminFacade.getInstance().deleteCorso(corso1.getCodCorso());
			System.out.println("Eliminato corso1");
			AdminFacade.getInstance().deleteCorso(corso2.getCodCorso());
			System.out.println("Eliminato corso2");
			AdminFacade.getInstance().deleteCorso(corso3.getCodCorso());
			System.out.println("Eliminato corso3");

		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	// ---------------- Docente
	@Test
	@Order(8)
	void testGetDocenteByID() {
		try {
			docente = AdminFacade.getInstance().getDocenteByID(101);
			System.out.println(docente.toString());
		} catch (DAOException | ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
			fail("Test fallito");
		}
	}

	@Test
	@Order(9)
	void testGetDocentePiuCorsi() {
		try {
			docente = AdminFacade.getInstance().getDocentePiuCorsi();
			System.out.println(docente.toString());
		} catch (DAOException | ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
			fail("Test fallito");
		}
	}

	// ---------------- Corsista
	@Test
	@Order(10)
	void testCreateCorsista() {
		try {
			corsista = new Corsista();

			corsista.setCodCorsista(8);

			corsista.setNomeCorsista("Federico");

			corsista.setCognomeCorsista("c8");

			corsista.setPrecedentiFormativi(true);

			AdminFacade.getInstance().createCorsista(corsista);

			System.out.println("creato corsista Federico");
		} catch (ClassNotFoundException | DAOException | IOException exc) {
			System.out.println(exc.getMessage());
			fail("test fallito");

		}

	}

	@Order(11)
	@Test
	void testGetCorsisti() {
		try {
			Corsista[] corsisti = AdminFacade.getInstance().getCorsisti();
			assertNotNull(corsisti);

			for (Corsista c : corsisti) {
				System.out.println("c   " + c.getCodCorsista() + ",nome:   " + c.getNomeCorsista() + ",cognome:   "
						+ c.getCognomeCorsista() + ",precedenti formativi:    " + c.getPrecedentiFormativi());

			}

		} catch (ClassNotFoundException | DAOException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Order(12)
	@Test
	void testGetCorsistaByID() {
		try {
			Corsista c = AdminFacade.getInstance().getCorsistaByID(3);

			assertNotNull(c);
			assertEquals(3, c.getCodCorsista());
			assertEquals("Federico", c.getNomeCorsista());
			assertEquals("c8", c.getCognomeCorsista());
			assertEquals(true, c.getPrecedentiFormativi());
		} catch (DAOException | ClassNotFoundException | IOException exc) {
			exc.printStackTrace();
			fail(exc.getMessage());

		}

	}

	// --------------------------------

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// ---------------- Corso
		corso1 = null;
		corso2 = null;
		corso3 = null;
		// ---------------- Docente
		docente = null;
		// ---------------- Corsista
		corsista = null;
	}
}
