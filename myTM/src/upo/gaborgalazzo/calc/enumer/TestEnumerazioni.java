package upo.gaborgalazzo.calc.enumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.uniupo.enumLib.Enumeratore;
import it.uniupo.enumLib.SLO;
//import it.uniupo.enumLib.WaW;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestEnumerazioni {
	Es1SeEnumeratoRE esercizio1 = new Es1SeEnumeratoRE();
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
	 * Test metodo che accetta l'insieme (esercizio1)
	 *********************************************/
	
	@Test (timeout = 1000)
	public void test050Acceptor() { //Il metodo accettante deve terminare su input  che  appartengono all'insieme		
		String in = stringa1;  
			esercizio1.acceptor(in);		
	}
	
	@Test (timeout = 1000)
	public void test055Acceptor() { //Il metodo accettante deve terminare su input  che  appartengono all'insieme
		
		String in = stringa3;
			esercizio1.acceptor(in);
		
	}
	
	
	@Test (timeout = 1000)
	public void test056Acceptor() {  //Il metodo accettante deve terminare su input  che  appartengono all'insieme
		
		String in = stringa4;     
			esercizio1.acceptor(in);
	}
	
	
	
	@Test 
	public void test060Acceptor() {
		String in = stringa2;
        final ExecutorService service = Executors.newSingleThreadExecutor();
        try {
		final Future<Object> f = service.submit(() -> {
			esercizio1.acceptor(in);
			
			return 42;
		 });
            f.get(2, TimeUnit.SECONDS);  
            fail("Il metodo accettante non deve terminare su input  che" +
					"non appartengono all'insieme ("+ stringa2 +" non appartiene)\n");
            } catch (final TimeoutException t){
        } catch (Exception e) {
			fail(e.getMessage());
		} finally {
            service.shutdown();
        }
		
	}
	
	@Test 
	public void test067Acceptor() {
		String in = stringa7;
        final ExecutorService service = Executors.newSingleThreadExecutor();
        try {
		final Future<Object> f = service.submit(() -> {
			esercizio1.acceptor(in);
			
			return 42;
		 });
            f.get(2, TimeUnit.SECONDS);  
            fail("Il metodo accettante non deve terminare su input  che" +
					"non appartengono all'insieme ("+ stringa7 +" non appartiene)\n");
            } catch (final TimeoutException t){
        } catch (Exception e) {
			fail(e.getMessage());
		} finally {
            service.shutdown();
        }
		
	}
	
	
	
	
	/********************************************
	 * Test metodo che accetta l'insieme, con passi limitati
	 *********************************************/
	@Test
	public void test070AcceptorLimitato() {	
		String in = stringa1;			
        assertTrue("Il metodo accettante deve terminare su input  che" +
					"appartengono all'insieme ("+stringa1 +" non appartiene)\n"
					+ "SE PENSATE CHE IL VOSTRO METODO FUNZIONI, \nMA CHE SERVA UN MAGGIOR NUMERO DI PASSI, "
					+ "AUMENTATE IL NUMERO DI PASSI NELLA CHIAMATA \nALL'INTERNO"
					+ "DEL TEST", esercizio1.acceptorLimitato(in, 300));		
	}
	
	@Test 
	public void test080AcceptorLimitato() {
		String in = stringa2;
        
		assertFalse("Il metodo accettante non deve terminare su input  che" +
					"non appartengono all'insieme ("+ stringa2+" non appartiene)"
					, esercizio1.acceptorLimitato(in, 100));		
		
	}
	
	/********************************************
	 * Test enumeratore
	 *********************************************/
	
	@Test
	public void test130Create() {
		assertNotNull(enumeratoreEs2);
	}

	
	@Test (timeout = 2000)
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
	
	@Test (timeout = 1000)
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
					String nuova = enumeratoreEs2.next();					if (nuova.equals(target)) {
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
	
	/********************************************
	 * Test metodo che decide l'insieme
	 *********************************************/


	@Test
	public void test300RiconOK() {
		assertTrue(stringa4+ " appartiene all'insieme", esercizio3.belongsTo(stringa4));
	}
	
	@Test
	public void test310RiconNO() {
		assertFalse(stringa2 +" non appartiene all'insieme", esercizio3.belongsTo(stringa2));
	}

	@Test
	public void test320RiconOKLungo() { //richiede tanto tempo!
		assertTrue(stringa6 + " appartiene all'insieme", esercizio3.belongsTo(stringa6));
	}
	
	@Test
	public void test330RiconNoDispari() { //richiede tanto tempo
		assertFalse(stringa7 +" appartiene all'insieme", esercizio3.belongsTo(stringa7));
	}

}
