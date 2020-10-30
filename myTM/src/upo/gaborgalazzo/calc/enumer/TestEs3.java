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

public class TestEs3 {
	Es3RicSseEnumOrdinato esercizio3 = new Es3RicSseEnumOrdinato();

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

	/********************************************
	 * Test enumeratore ordinato
	 *********************************************/
	
	@Test (timeout = 2000)
	public void test200EnumOrdOK() {   //L'enumeratore deve generare tutte le stringhe che appartengono all'insieme (stringa3 appartiene all'insieme)
									//SE SIETE CONVINTI CHE LA VOSTRA IMPLEMENTAZIONE SIA CORRETTA, AUMENTATE IL TEMPO DI timeout DEL TEST
		String target = stringa3;	
		try {
			
				boolean found = false;
				while (!found) {
					String nuova = enumeratoreEs2.next();					
					if (nuova.equals(target)) {
						found = true;
					} else if (SLO.compare(nuova,target) > 0) {
						fail("la stringa "+ stringa3 + "appartiene all'insieme, deve apparire nell'enumerazione");
					}
				}
		
	            } catch (Exception e) {
	    			fail(e.getMessage());
			}
			
			}
	
	@Test  (timeout = 4000)
	public void test210EnumeratoreOrdOK() { //L'enumeratore deve generare tutte le stringhe che appartengono all'insieme (stringa4 appartiene all'insieme)
											//SE SIETE CONVINTI CHE LA VOSTRA IMPLEMENTAZIONE SIA CORRETTA, AUMENTATE IL TEMPO DI timeout DEL TEST
		String target = stringa4;	
		try {
				boolean found = false;
				while (!found) {
					String nuova = enumeratoreEs2.next();
					if (nuova.equals(target)) {
						found = true;
					}  else if (SLO.compare(nuova,target) > 0) {
						fail("la stringa "+ stringa4 + "appartiene all'insieme, deve apparire nell'enumerazione");
					}
				}
				
	         } catch (Exception e) {
	    			fail(e.getMessage());
	         }
	}
	
	
	@Test
	public void test230EnumeratoreOrdNO() {	
		String target = stringa5;	
        final ExecutorService service = Executors.newSingleThreadExecutor();
		try {
			final Future<Object> f = service.submit(() -> {
				int i = 0;
				boolean found = false;
				while (!found) {
					String nuova = enumeratoreEs2.next();					if (nuova.equals(target)) {
						found = true;
					}
					i++;
				}
				return i;
			});
				f.get(2, TimeUnit.SECONDS);
				fail("L'enumeratore deve generare solo stringhe che appartengono" +
        				"all'insieme ("+stringa5+" non appartiene)");	           
	            } catch (final TimeoutException t){
	            } catch (Exception e) {
	    			fail(e.getMessage());
			} finally {
	            service.shutdown();
	        }
	}
	
	@Test
	public void test250EnumOrdOrdine() {	
		assertTrue("L'enumeratore e' ordinato", SLO.compare(esercizio3.enumeratoreOrdinato(4),esercizio3.enumeratoreOrdinato(10)) < 0);
		assertEquals("L'enumeratore e' ordinato", 1, SLO.compare(esercizio3.enumeratoreOrdinato(7),esercizio3.enumeratoreOrdinato(6)) );

	}


}
