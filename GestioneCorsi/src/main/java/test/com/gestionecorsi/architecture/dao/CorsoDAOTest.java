package test.com.gestionecorsi.architecture.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gestionecorsi.architecture.dao.CorsoDAO;
import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dbaccess.DBAccess;
import com.gestionecorsi.businesscomponent.model.Corso;

@TestMethodOrder(OrderAnnotation.class)
class CorsoDAOTest {

	private static Corso corso;
	private static Connection conn;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		conn = DBAccess.getConnection();
		corso = new Corso();
		corso.setCodCorso(1);
		corso.setCodDocente(105);
		corso.setNomeCorso("Analisi I");
		corso.setDataInizioCorso(new GregorianCalendar(2010, 9, 15).getTime());
		corso.setDataFineCorso(new GregorianCalendar(2010, 12, 25).getTime());
		corso.setCostoCorso(999.99);
		corso.setCommentiCorso("Corso tosto");
		corso.setAulaCorso("A103");
	}

	@Test
	@Order(1)
	void testCreate() {
		try {
			CorsoDAO.getFactory().create(conn, corso);
			System.out.println("Creato corso");

		} catch (DAOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Order(2)
	void testGetByID() {
		try {
			Corso c = CorsoDAO.getFactory().getByID(conn, corso.getCodCorso());
			System.out.printf("  %s, %s\n", c.getNomeCorso(), c.getAulaCorso());

		} catch (DAOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Order(3)
	void testGetAll() {
		try {
			Corso[] corsi = CorsoDAO.getFactory().getAll(conn);
			assertNotNull(corsi);

		} catch (DAOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Order(4)
	void testDelete() {
		try {
			CorsoDAO.getFactory().delete(conn, corso.getCodCorso());
			System.out.println("Eliminato corso");

		} catch (DAOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		corso = null;
		DBAccess.closeConnection();
	}

}
