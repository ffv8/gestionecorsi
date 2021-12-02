package test.com.gestionecorsi.architecture.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dao.DocenteDAO;
import com.gestionecorsi.architecture.dbaccess.DBAccess;
import com.gestionecorsi.businesscomponent.model.Docente; 

@TestMethodOrder(OrderAnnotation.class)
class TestDocenteDAO {
	private static Connection conn;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		conn = DBAccess.getConnection();
	}

	@Test
	@Order(1)
	void testGetByID() {
		try {
			Docente docente = DocenteDAO.getFactory().getById(conn, 101);
			System.out.println(docente.toString());
		} catch (DAOException e) {
			System.out.println(e.getMessage());
			fail("Test fallito");
		}
	}
	
	@Test
	@Order(2)
	void testGetAll() {
		try {
			Docente[] docenti = DocenteDAO.getFactory().getAll(conn);
			System.out.println(docenti.length);
			System.out.println(docenti[0].toString());
		} catch (DAOException e) {
			System.out.println(e.getMessage());
			fail("Test fallito");
		}
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		conn.close();
	}
}
