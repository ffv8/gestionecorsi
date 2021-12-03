package test.com.gestionecorsi.architecture.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.gestionecorsi.architecture.dao.CorsistaDAO;
import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.architecture.dbaccess.DBAccess;
import com.gestionecorsi.businesscomponent.model.Corsista;

@TestMethodOrder(OrderAnnotation.class)
class CorsistaDAOTest {
	
	private static Connection conn;
	private static Corsista c1;
	private static Corsista c2;
	private static Corsista c3;
	private static Corsista c4;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		conn=DBAccess.getConnection();
		
		c1=new Corsista();
		c1.setCodCorsista(1);
		c1.setNomeCorsista("Luca");
		c1.setCognomeCorsista("c1");
		c1.setPrecedentiFormativi(false);
		
		c2=new Corsista();
		c2.setCodCorsista(2);
		c2.setNomeCorsista("Roberta");
		c2.setCognomeCorsista("c2");
		c2.setPrecedentiFormativi(true);
		
		c3=new Corsista();
		c3.setNomeCorsista("Silvia");
		c3.setCognomeCorsista("c3");
		c3.setCodCorsista(3);
		c3.setPrecedentiFormativi(false);
		
		c4=new Corsista();
		c4.setCodCorsista(4);
		c4.setNomeCorsista("Paolo");
		c4.setCognomeCorsista("c4");
		c4.setPrecedentiFormativi(true);
		
		
		
		
	}

	@Test
	@Order(1)
	void testCreate() {

		try {
				CorsistaDAO.getFactory().create(conn, c1);
				System.out.println("creato corsista c1");
			
				CorsistaDAO.getFactory().create(conn, c2);
				System.out.println("creato corsista c2");
			
				CorsistaDAO.getFactory().create(conn, c3);
				System.out.println("creato corsista c3");
			
				CorsistaDAO.getFactory().create(conn, c4);
				System.out.println("creato corsista c4");
						
			
		} 
		catch (DAOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		
	}
	
	@Test
	@Order(2)
	void testGetAll() {

		try {
				Corsista[] corsisti=CorsistaDAO.getFactory().getAll(conn);
				assertNotNull(corsisti);		
			
				for(Corsista c : corsisti)
				{
					System.out.println("c   "+c.getCodCorsista() +",nome:   "+c.getNomeCorsista()+",cognome:   "+c.getCognomeCorsista()+",precedenti formativi:    "+c.getPrecedentiFormativi());
					
				}
		} 
		catch (DAOException e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
	}		
	@Test
	@Order(3)
	void testGetByID() {

		try {
				Corsista c=CorsistaDAO.getFactory().getByID(conn, c1.getCodCorsista());
				System.out.println("Dati del corsista c1:   "+ c.getCodCorsista()+",nome:   "+c.getNomeCorsista()+",cognome:    "+c.getCognomeCorsista()+",precedenti formativi:    "+c.getPrecedentiFormativi());			
				assertNotNull(c);
				assertEquals(1, c.getCodCorsista());
				assertEquals("Luca",c.getNomeCorsista());
				assertEquals("c1",c.getCognomeCorsista());
				assertEquals(false,c.getPrecedentiFormativi());
			} 
			catch (DAOException e) {
				e.printStackTrace();
				fail(e.getMessage());
				
			}
					
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		c1= null;
		c2 = null;
		c3 = null;
		c4=null;
		DBAccess.closeConnection();
	}
	
}
