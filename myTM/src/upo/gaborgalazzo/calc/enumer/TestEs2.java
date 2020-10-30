package upo.gaborgalazzo.calc.enumer;

import it.uniupo.enumLib.Enumeratore;
import it.uniupo.enumLib.SLO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.concurrent.*;

import static org.junit.Assert.*;

//import it.uniupo.enumLib.WaW;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestEs2 {
	Enumeratore enumeratoreEs2 = new Es2EnumeratoreDaAccettante();
	String stringa1;
	String stringa2;
	String stringa3;
	String stringa4;
	String stringa5;
	String stringa6;
	String stringa7;

	//*********
	// Stringhe per la verifica con insieme Orpal
	//**********
	@Before
	public void setUp() {  //insieme Orpal
		stringa1 = "aab"; //ok
		stringa2 = "abba"; //no
		stringa3 = "b"; //ok
		stringa4 = "bba"; //ok
		stringa5 = "bbab"; //no
		stringa6 = "bbabbbbabba"; //ok
		stringa7 = "baaabbbabba"; //no
	}
	
	//*********
		// Stringhe per la verifica con insieme Waw
		//**********
//	@Before
//	public void setUp() {  //insieme WaW
//		stringa1 = "aaa"; //ok
//		stringa2 = "abba"; //no
//		stringa3 = "bab"; //ok
//		stringa4 = "bcabc"; //ok
//		stringa5 = "bbab"; //no
//		stringa6 = "cbabcacbabc"; //ok
//		stringa7 = "baaabababba"; //no
//	}



	/********************************************
	 * Test enumeratore
	 *********************************************/

	@Test
	public void test130Create() {
		assertNotNull(enumeratoreEs2);
	}


	@Test (timeout = 5000)
	public void test130EnumeratoreOK() { //L'enumeratore deve generare tutte le stringhe che appartengono all'insieme (stringa3 appartiene all'insieme)
		//SE SIETE CONVINTI CHE LA VOSTRA IMPLEMENTAZIONE SIA CORRETTA, AUMENTATE IL TEMPO DI timeout DEL TEST
		String target = stringa3;
		try {
			boolean found = false;
			while (!found) {
				String nuova = enumeratoreEs2.next();
				if (nuova.equals(target)) {
					found = true;
				}
			}
		}  catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test (timeout = 5000)
	public void test140Enumeratoreok() {   //L'enumeratore deve generare tutte le stringhe che appartengono all'insieme (stringa4 appartiene all'insieme)
		//SE SIETE CONVINTI CHE LA VOSTRA IMPLEMENTAZIONE SIA CORRETTA, AUMENTATE IL TEMPO DI timeout DEL TEST
		String target = stringa4;
		try {
			boolean found = false;
			while (!found) {
				String nuova = enumeratoreEs2.next();
				if (nuova.equals(target)) {
					found = true;
				}
			}


		} catch (Exception e) {
			fail(e.getMessage());
		}
	}


	@Test
	public void test150EnumeratoreNo() {
		String target = stringa5;
		final ExecutorService service = Executors.newSingleThreadExecutor();
		try {
			final Future<Object> f = service.submit(() -> {
				int i = 0;
				boolean found = false;
				while (!found) {
					String nuova = enumeratoreEs2.next();
					if (nuova.equals(target)) {
						found = true;
					}
					i++;
				}
				return i;
			});
			f.get(6, TimeUnit.SECONDS);
			fail("L'enumeratore deve generare solo stringhe che appartengono" +
					"all'insieme ("+stringa5+" non appartiene)");

		} catch (final TimeoutException t){
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			service.shutdown();
		}
	}


}
