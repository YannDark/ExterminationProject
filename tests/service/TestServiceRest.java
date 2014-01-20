package service;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import fr.epsi.projet.beans.Emplacement;
import fr.epsi.projet.service.ServiceRest;

public class TestServiceRest {

	private ServiceRest serviceRest = new ServiceRest();
	
	@Test
	public void test() {
		fail("Not yet implemented");
		
	}

	public void testGetBennes(){
		List<Emplacement> le = serviceRest.getBennes();
	}
}
