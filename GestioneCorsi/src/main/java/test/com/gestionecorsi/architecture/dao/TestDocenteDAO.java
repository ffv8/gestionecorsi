package test.com.gestionecorsi.architecture.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dao.DocenteDAO;
import com.gestionecorsi.architecture.dbaccess.DBAccess;
import com.gestionecorsi.businesscomponent.models.Docente; 

class TestDocenteDAO {
	private static Connection conn;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		conn = DBAccess.getConnection();
	}

	@Test
	void test() {
		try {
			Docente docente = DocenteDAO.getFactory().getById(conn, 101);
			System.out.println(docente.toString());
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
