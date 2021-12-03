package test.com.gestionecorsi.businesscomponent;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.businesscomponent.AdminFacade;
import com.gestionecorsi.businesscomponent.model.Docente;

@TestMethodOrder(OrderAnnotation.class)
class AdminFacadeTest {
	@Test
	@Order(1)
	void testGetDocenteByID() {
		try {
			Docente docente = AdminFacade.getInstance().getDocenteByID(101);
			System.out.println(docente.toString());
		} catch (DAOException | ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
			fail("Test fallito");
		}
	}
	
	@Test
	@Order(2)
	void testGetDocentePiuCorsi() {
		try {
			Docente docente = AdminFacade.getInstance().getDocentePiuCorsi();
			System.out.println(docente.toString());
		} catch (DAOException | ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
			fail("Test fallito");
		}
	}
}
