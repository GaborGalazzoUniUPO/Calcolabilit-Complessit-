package upo.gaborgalazzo.tm.ndtm;
import static it.uniupo.mdtLib.EnvironmentStaticInterface.HALTED;
import static it.uniupo.mdtLib.EnvironmentStaticInterface.RUNNING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import it.uniupo.mdtLib.DynamicEnvironment;
import it.uniupo.mdtLib.ReadMachine;
import it.uniupo.mdtLib.State;
import it.uniupo.mdtLib.TransitionTable;

import org.junit.Before;
import org.junit.Test;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestNDTM {
	public static final String TEST_DELTA_FILE = "testNDTM";
	NDTM tn;
	DynamicEnvironment workEnv;
	
	@Before
	public void setUp(){
		DefaultEnvironmentNDTM e = new DefaultEnvironmentNDTM();
		workEnv = DynamicEnvironment.getInstance(e);
		workEnv.setPath("data/");
		workEnv.setDeltaFile(TEST_DELTA_FILE);

		TransitionTable tt = ReadMachine.readTT();	
		tn = new NDTM(tt);
	    tn.initializeConfig(State.getState("q3"), "011", 2);	
		tn.addNewComputation(State.getState("q5"), " 10", 1);
	}
	
	@Test
	public void test01StepSize(){ //generate le nuove computazioni nel caso che non se ne fermi nessuna delle esistenti
								//preoccupatevi ora *solo del loro numero*
		tn.step();
		assertTrue("dopo un passo di computazione del caso di test, devono esserci cinque computazioni attive", tn.getNumberOfComputations() == 5);
	}
	
	@Test
	public void test05StepTape(){ //ora preoccupatevi che siano proseguite nel modo corretto
								//potete ancora supporre che nessuna termini
								//cioe' TransitionTableNonDetValue sempre non null
		tn.step();
		assertTrue("dopo un passo, una configurazione e' q4, |01$-> ", tn.toString().contains("q4, |01$-> "));
		assertTrue("dopo un passo, una configurazione e' q5, |01->1", tn.toString().contains("q5, |01->1"));
		assertTrue("dopo un passo, una configurazione e' q3, | 1->0", tn.toString().contains("q3, | 1->0"));
		assertTrue("dopo un passo, una configurazione e' q3, | 1->0", tn.toString().contains("q6, |-> 00"));
		assertTrue("dopo un passo, una configurazione e' q1, | ->00", tn.toString().contains("q1, | ->00"));
		assertTrue("dopo un passo, nessuna configurazione e' q1, | ->10", !tn.toString().contains("q1, | ->10"));
	}
	
	@Test
	public void test10running(){ //il metodo deve restiutire RUNNING (l'unica comutazione non e' terminata)
		TransitionTable tt = ReadMachine.readTT();
		tn = new NDTM(tt);
		tn.initializeConfig(State.getState("q3"), "AA", 1);
		char runState = tn.step();
		assertEquals("L'unica computazione non dovrebbe essere terminata",RUNNING, runState);
	}
	
	@Test
	public void test15AllHalted1(){  //il metodo deve restituire HALTED (l'unica computazione e' terminata)
		TransitionTable tt = ReadMachine.readTT();
		tn = new NDTM(tt);
		tn.initializeConfig(State.getState("q100"), "B", 0);
		char runState = tn.step();
		assertEquals("L'unica computazione dovrebbe essere terminata",HALTED, runState);
	}
	
	@Test
	public void test20AllHalted2(){ //il metodo deve restituire HALTED (tutte le computazioni terminate)
		TransitionTable tt = ReadMachine.readTT();
		tn = new NDTM(tt);
		tn.initializeConfig(State.getState("q100"), "B", 0);
	    tn.addNewComputation(State.getState("q100"),"B",0);
		char runState = tn.step();
		assertEquals("Le computazioni dovrebbero essere tutte terminate",HALTED, runState);
	}
	
	@Test
	public void test30OneHalted2(){ //il metodo deve restituire HALTED (una computazione e' terminata)
		TransitionTable tt = ReadMachine.readTT();
		tn = new NDTM(tt);
		tn.initializeConfig(State.getState("q100"), "B", 0);
		tn.initializeConfig(State.getState("q3"), "A0", 1);
		char runState = tn.step();
		assertEquals("Una computazione terminata: la macchina deve essere terminata",HALTED, runState);
	}
	
	
	
	
}
