package upo.gaborgalazzo.tm.ndtm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniupo.mdtLib.DynamicEnvironment;
import it.uniupo.mdtLib.ReadMachine;
import it.uniupo.mdtLib.State;
import it.uniupo.mdtLib.Tape;
import it.uniupo.mdtLib.TapeInterface;
import it.uniupo.mdtLib.TransitionTable;
import it.uniupo.mdtLib.TransitionTableValue;

import static it.uniupo.mdtLib.EnvironmentStaticInterface.HALTED;

public class TestComputation {
public static final String TEST_DELTA_FILE = "testNDTM";
	Computation testComp;
	TapeInterface t;
	State s;
	TransitionTableValue tri;
	DynamicEnvironment workEnv;

	
	@Before
	public void setUp(){
		DefaultEnvironmentNDTM e = new DefaultEnvironmentNDTM();
		workEnv = DynamicEnvironment.getInstance(e);
		workEnv.setPath("data/");
		workEnv.setDeltaFile(TEST_DELTA_FILE);
		
		s = State.getState("q3");
		t = new Tape();
		t.setTape("011", 2);
		testComp = new Computation(s,"011", 2);
		TransitionTable tt = ReadMachine.readTT();
		Iterable<TransitionTableValue> triplets = tt.getTriplets(s,t.cellRead()); 
		tri = triplets.iterator().next();
	}
	
	@Test
	public void testFactoryGetComputationNotNull() {
		assertNotNull("l'oggetto Computation non e' stato creato", testComp);
	}
	
	@Test
	public void testFactoryGetComputationCompTape() {
		assertTrue("il nastro non ha la stessa lunghezza di quello di test", testComp.getTapeString().length() == t.length());
		assertTrue("la testina non legge lo stesso input di quella di test", testComp.getCurrentSymbol() == t.cellRead());
	}
	
	@Test
	public void testFactoryGetComputationCompState() {
		assertTrue("lo stato non e' quello di test", testComp.getCurrentState() == s);
	}
	
	@Test
	public void testComputationStep() { // per ora sistemate lo step() quando la delta e' definita
										//cioe' TransitionTableValue sempre non null
		testComp.step(tri);
		assertTrue("Errore nuovo stato",testComp.getCurrentState() == tri.getNewState());
		assertTrue("Errore spostamento", testComp.getHeadPosition() == t.getHeadPosition() + 1);
		assertTrue("Errore di scrittura su nastro",testComp.getTapeString().equals("01$ "));
	}
	
	@Test
	public void testComputationStep2() { //adesso considerate il caso della delta non definita
		tri = null;
		char stateOfRun = testComp.step(tri);
		assertEquals("Errore quando la delta  non e' definita", HALTED, stateOfRun);

	}
	

}
