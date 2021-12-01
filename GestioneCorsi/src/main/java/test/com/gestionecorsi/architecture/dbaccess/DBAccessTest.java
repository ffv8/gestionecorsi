package test.com.gestionecorsi.architecture.dbaccess;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dbaccess.DBAccess;

class DBAccessTest {

	@Test
	void testConnection() {
		try {
			DBAccess.getConnection();
		} catch (DAOException | ClassNotFoundException | IOException exc) {
			exc.printStackTrace();
			fail("Connessione non funzionante");
		} finally {
			try {
				DBAccess.closeConnection();
			} catch (DAOException exc) {
				exc.printStackTrace();
				fail("Impossibile chiudere la connessione");
			}
		}
	}
}
