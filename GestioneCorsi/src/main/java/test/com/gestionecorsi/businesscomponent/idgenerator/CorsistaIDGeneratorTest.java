package test.com.gestionecorsi.businesscomponent.idgenerator;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.gestionecorsi.architecture.dao.DAOException;
import com.gestionecorsi.businesscomponent.idgenerator.CorsistaIDGenerator;

class CorsistaIDGeneratorTest {

	@Test
	void testGeneratoreID() {
		try
		{
			CorsistaIDGenerator idGen = CorsistaIDGenerator.getInstance();
			assertNotNull(idGen,"errore nell'istanziazione del generatore");/*la stringa viene fuori se idGen è null*/
			long valore = idGen.getNextID();
			assertEquals(valore,idGen.getNextID()-1);
			
		}
		catch(ClassNotFoundException | DAOException | IOException exc)
		{
			fail(exc.getMessage());
			
		}
	}

}
