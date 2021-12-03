package test.com.gestionecorsi.businesscomponent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.businesscomponent.AdminFacade;
import com.gestionecorsi.businesscomponent.model.Corsista;
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
	
	@Test
	@Order(3)
	void testCreateCorsista()
	{
		try
		{
			Corsista model=new Corsista();
			
			model.setCodCorsista(8);
			
			model.setNomeCorsista("Federico");
			
			model.setCognomeCorsista("c8");
			
			model.setPrecedentiFormativi(true);
			
			AdminFacade.getInstance().createCorsista(model);
			
			System.out.println("creato corsista Federico");
		}
		catch(ClassNotFoundException | DAOException | IOException exc)
		{
			System.out.println(exc.getMessage());
			fail("test fallito");
			
		}
		
	}
	
	@Order(4)
	@Test
	void testGetCorsisti()
	{
		try {
			Corsista[] corsisti = AdminFacade.getInstance().getCorsisti();
			assertNotNull(corsisti);
			
			for(Corsista c : corsisti)
			{
				System.out.println("c   "+c.getCodCorsista() +",nome:   "+c.getNomeCorsista()+",cognome:   "+c.getCognomeCorsista()+",precedenti formativi:    "+c.getPrecedentiFormativi());
				
			}
			
		} catch (ClassNotFoundException | DAOException | IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		
	}
	
	@Order(5)
	@Test
	void testGetCorsistaByID()
	{
		try
		{
			Corsista c=AdminFacade.getInstance().getCorsistaByID(5);
			
			assertNotNull(c);
			assertEquals(5, c.getCodCorsista());
			assertEquals("Federico",c.getNomeCorsista());
			assertEquals("c8",c.getCognomeCorsista());
			assertEquals(true,c.getPrecedentiFormativi());
		}
		catch(DAOException |ClassNotFoundException | IOException exc)
		{
			exc.printStackTrace();
			fail(exc.getMessage());		
			
		}
		
	}
}
