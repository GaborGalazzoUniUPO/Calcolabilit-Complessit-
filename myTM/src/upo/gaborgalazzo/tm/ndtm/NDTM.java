/***************************************
 * A single tape/single track nondeterministic TM.
 * The tape can be semiinfinite or infinite,
 * it can be changed changing the corresponding 
 * line in the constructor
 * 
 * @author Lavinia Egidi
 * @date Jan 2015
 *********************************************/
package upo.gaborgalazzo.tm.ndtm;

import static it.uniupo.mdtLib.EnvironmentStaticInterface.RUNNING;
import static it.uniupo.mdtLib.EnvironmentStaticInterface.HALTED;
import static it.uniupo.mdtLib.EnvironmentStaticInterface.TAPE_ERROR;

import java.util.LinkedList;

import it.uniupo.mdtLib.MdTInterface;
import it.uniupo.mdtLib.State;
import it.uniupo.mdtLib.TransitionTable;
import it.uniupo.mdtLib.TransitionTableValue;

public class NDTM implements MdTInterface {

	private LinkedList<Computation> lastLevel;
	private TransitionTable transitionTable;

	NDTM(TransitionTable tt) { // costruttore: genera una nuova NDTM con funz di
								// transizione tt
		lastLevel = new LinkedList<Computation>();
		transitionTable = tt;
	}

	public int getNumberOfComputations() {
		return lastLevel.size();
	}

	public void initializeConfig(State s, String ts, int headPos) { // inizializza
																	// la NDTM
																	// con stato
																	// iniziale
																	// s, nastro
																	// iniziale
																	// t
		lastLevel.clear();
		Computation startComp = new Computation(s, ts, headPos);
		lastLevel.add(startComp);
	}

	public void initializeConfig(State s, String ts, String headPos) { // inizializza
																		// la
																		// NDTM
																		// con
																		// stato
																		// iniziale
																		// s,
																		// nastro
																		// iniziale
																		// t
		initializeConfig(s, ts, Integer.parseInt(headPos));
	}

	public void addNewComputation(State s, String ts, int headPos) { // aggiunge
																		// una
																		// nuova
																		// computazione
																		// con
																		// stato
																		// s, e
																		// nastro
																		// t
		Computation comp = new Computation(s, ts, headPos);
		lastLevel.add(comp);
	}

	public char step() { // esegue un passo della NDTM
						//restituisce RUNNING, HALTED o TAPE_ERROR

		char stateOfRun = RUNNING;

		boolean hasTapeError = false;
		
		LinkedList<Computation> nextLevel = new LinkedList<Computation>();

		for (Computation c : lastLevel) { // per ogni computazione al livello i,
			// genera i figli al livello i+1
			// li aggiunge nella struttura
			// temporanea nextLevel che poi
			// diventa
			// il nuovo lastLevel
			Iterable<TransitionTableValue> tts = transitionTable.getTriplets(c.getCurrentState(), c.getCurrentSymbol());
			if (tts == null) {
				stateOfRun = HALTED;
			} else {
				for (TransitionTableValue t : tts) {
					Computation computation = new Computation(c.getCurrentState(), c.getTapeString(), c.getHeadPosition());
					switch (computation.step(t)) {
						case HALTED: {
							stateOfRun = HALTED;
							break;
						}
						case RUNNING: {
							nextLevel.add(computation);
						}
						case TAPE_ERROR: {
							hasTapeError = true;
						}
					}
				}
			}
		}
		lastLevel = nextLevel;

		if(lastLevel.size() == 0 && hasTapeError){
			stateOfRun = TAPE_ERROR;
		}
		return stateOfRun;  
	}

	@Override
	public String toString() { // stampa la MdT su stringa
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < lastLevel.size(); ++i) {
			buf.append(lastLevel.get(i));
			buf.append("    ");
		}
		return new String(buf);
	}

}
