package upo.gaborgalazzo.calc.enumer;

import it.uniupo.enumLib.Enumeratore;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.*;

public class TestEs1 {

    Es1SeEnumeratoRE esercizio1 = new Es1SeEnumeratoRE();
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

    @Test(timeout = 1000)
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


}
