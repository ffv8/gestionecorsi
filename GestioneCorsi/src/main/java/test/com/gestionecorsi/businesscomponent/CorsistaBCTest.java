package test.com.gestionecorsi.businesscomponent;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.businesscomponent.CorsistaBC;
import com.gestionecorsi.businesscomponent.model.Corsista;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class CorsistaBCTest {

	private static Corsista c1;
	private static Corsista c2;
	private static Corsista c3;
	private static CorsistaBC cBC;
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		
		c1=new Corsista();
		c1.setCodCorsista(5);
		c1.setNomeCorsista("Mattia");
		c1.setCognomeCorsista("c5");
		c1.setPrecedentiFormativi(true);
		
		c2=new Corsista();
		c2.setCodCorsista(6);
		c2.setNomeCorsista("Carlotta");
		c2.setCognomeCorsista("c6");
		c2.setPrecedentiFormativi(true);
		
		c3=new Corsista();
		c3.setCodCorsista(7);
		c3.setNomeCorsista("Ilaria");
		c3.setCognomeCorsista("c7");
		c3.setPrecedentiFormativi(false);
		
		cBC=new CorsistaBC();
				
		
	}

	@Test
	@Order(1)
	void testCreate() {

		try {
				
				cBC.create(c1);
				System.out.println("creato corsista c1");
			
				cBC.create(c2);
				System.out.println("creato corsista c2");
			
				cBC.create(c3);
				System.out.println("creato corsista c3");
									
			
		} 
		catch (DAOException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		
	}
	
	@Test
	@Order(2)
	void testGetByID() {

		try {
			
			
			Corsista c=cBC.getByID(c1.getCodCorsista());
			System.out.println("Dati del corsista c1:   "+ c.getCodCorsista()+",nome:   "+c.getNomeCorsista()+",cognome:    "+c.getCognomeCorsista()+",precedenti formativi:    "+c.getPrecedentiFormativi());			
			assertNotNull(c);
			assertEquals(5, c.getCodCorsista());
			assertEquals("Mattia",c.getNomeCorsista());
			assertEquals("c5",c.getCognomeCorsista());
			assertEquals(true,c.getPrecedentiFormativi());
			
				
		} 
		catch (DAOException e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
	}		
	@Test
	@Order(3)
	void testGetAll() {

		try {
			
			
			Corsista[] corsisti=cBC.getAll();
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
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		c1= null;
		c2 = null;
		c3 = null;
		cBC=null;
	}


}
