package test.com.gestionecorsi.architecture.dao;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.sql.Connection;

import org.junit.jupiter.api.Test;

import com.gestionecorsi.architecture.dao.AmministratoreDAO;
import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dbaccess.DBAccess;
import com.gestionecorsi.businesscomponent.model.Amministratore;

class AmministratoreDAOTest {
	private long id;
	private Amministratore admin;
	private Connection conn;

	@Test
	void testGetAdminById() {
		try {
			conn = DBAccess.getConnection();
			admin = AmministratoreDAO.getFactory().getById(conn, id);
			System.out.println("Trovato admin:/n");
			System.out.println(admin);
		} catch (ClassNotFoundException | DAOException | IOException exc) {
			exc.printStackTrace();
			fail(exc.getMessage());
		}
	}
}
