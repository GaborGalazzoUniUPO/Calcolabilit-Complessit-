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

public class TestEs3Belongs {
	Es3RicSseEnumOrdinato esercizio3 = new Es3RicSseEnumOrdinato();

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
