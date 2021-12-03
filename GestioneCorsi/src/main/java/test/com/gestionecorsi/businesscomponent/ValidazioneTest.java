package test.com.gestionecorsi.businesscomponent;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gestionecorsi.businesscomponent.utilities.Validazione;

@TestMethodOrder(OrderAnnotation.class)
class ValidazioneTest {
	private static Validazione validatore;
	
	@BeforeAll
	static void setUpBeforeClass() {
		validatore = new Validazione();
	}
	
	@Test
	@Order(1)
	void testConvalidaString() {
		if(validatore.convalidaString(null))
			fail("Errore di validazione stringa (null)");
		
		if(validatore.convalidaString("asdasdasdasdasdasdasdasdasdasda"))
			fail("Errore di validazione stringa (lunghezza)");
		
		if(validatore.convalidaString("Stefano1"))
			fail("Errore di validazione stringa (regex)");

		if(validatore.convalidaString("Stefano"))
			System.out.println("Validazione stringa completata");
		else
			fail("Errore di validazione stringa");
	}
	
	@Test
	@Order(2)
	void testConvalidaCorsista() {
		if(!(validatore.convalidaCorsista("Stefano", "Sommacal")))
			fail("Errore di validazione del corsista");
		
		System.out.println("Validazione del corsista completata");
	}
	
	@Test
	@Order(3)
	void testConvalidaData() {
		if(validatore.convalidaData("03122021"))
			fail("Errore di validazione della data");
		
		if(validatore.convalidaData("03/12/2021"))
			System.out.println("Validazione della data completata");		
	}
	
	@Test
	@Order(4)
	void testConvalidaDifferenzaDate() {
		if(validatore.convalidaDifferenzaDate("03/12/2021", "04/12/2021"))
			fail("Errore di validazione della differenza tra date");
		
		if(validatore.convalidaDifferenzaDate("03/12/2021", "06/12/2021"))
			System.out.println("Validazione della differenza tra date completata");	
	}
	
	@Test
	@Order(5)
	void testConvalidaCorso() {
		if(!(validatore.convalidaCorso("Python", "03/12/2021", "15/02/2022", "", "A101")))
			fail("Errore di validazione del corso");
		
		System.out.println("Validazione del corso completata");
	}
	
	@AfterAll
	static void tearDownAfterClass() {
		validatore = null;
	}
}
