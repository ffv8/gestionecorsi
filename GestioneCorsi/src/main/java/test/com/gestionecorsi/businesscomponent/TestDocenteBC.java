package test.com.gestionecorsi.businesscomponent;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.businesscomponent.DocenteBC;
import com.gestionecorsi.businesscomponent.models.Docente;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class TestDocenteBC {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	@Order(1)
	void testGetByID() {
		try {
			DocenteBC dBC = new DocenteBC();
			Docente docente = dBC.getByID(101);
			System.out.println(docente.toString());
		} catch(ClassNotFoundException | DAOException | IOException e) {
			System.out.println(e.getMessage());
			fail("Test fallito");
		}
	}
	
	@Test
	@Order(2)
	void testDocentePiuCorsi() {
		try {
			DocenteBC dBC = new DocenteBC();
			Docente docente = dBC.getDocentePiuCorsi();
			System.out.println(docente.toString());
		} catch(ClassNotFoundException | DAOException | IOException e) {
			System.out.println(e.getMessage());
			fail("Test fallito");
		}
	}
	
	@AfterAll
		static void tearDownAfterClass() throws Exception {
		}
}
