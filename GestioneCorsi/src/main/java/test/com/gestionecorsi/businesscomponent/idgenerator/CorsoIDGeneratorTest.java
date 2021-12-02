package test.com.gestionecorsi.businesscomponent.idgenerator;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.businesscomponent.idgenerator.CorsoIDGenerator;

class CorsoIDGeneratorTest {

	@Test
	void testGetNextID() {
		try {
			long id = CorsoIDGenerator.getInstance().getNextID();
			System.out.println("Valore corrente corso_seq: " + id);
			assertTrue(id > 0);

		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
