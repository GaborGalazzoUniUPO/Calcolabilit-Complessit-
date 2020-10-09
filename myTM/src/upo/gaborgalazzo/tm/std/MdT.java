/***************************************
 * A single tape/single track deterministic TM.
 * The tape can be semiinfinite or infinite,
 * it can be changed changing the corresponding 
 * line in the constructor
 * 
 * @author Lavinia Egidi
 * @date Jan 2015
 *********************************************/
package upo.gaborgalazzo.tm.std;

import static it.uniupo.mdtLib.EnvironmentStaticInterface.RUNNING;
import static it.uniupo.mdtLib.EnvironmentStaticInterface.HALTED;
import static it.uniupo.mdtLib.EnvironmentStaticInterface.TAPE_ERROR;


import it.uniupo.mdtLib.*;

public class MdT implements MdTInterface {
	private DynamicEnvironment workEnv;	 //all the parameters of this project
	private State currentState;       	//the TM state
	private TapeInterface tape;			//the tape
	private TransitionTable transitionTable;		//the transition function

	
	MdT(TransitionTable tt){
		workEnv = DynamicEnvironment.getInstance();
		currentState = workEnv.getInitialState();
		tape = new Tape();			///change this line to change the kind of tape used
		transitionTable = tt;
	}
				//a TM is initialized specifying its state, the position of the head and the contents on the tape
	private void initializeConfig(State s, String tapeString, int headPos ){
		currentState = s;
		tape.setTape(tapeString, headPos);
	}
			//an initialization method independent of the number of heads
	public void initializeConfig(State s, String tapeString, String headPos){
		initializeConfig(s,tapeString,Integer.parseInt(headPos.trim()));
	}
	
	public char step(){				//a step of the TM
		/*************
		 * DA IMPLEMENTARE!
		 * sulla base della configurazione attuale, la MdT deve eseguire un passo
		 * Il metodo deve restituire 
		 * 	-RUNNING se la MdT non ha terminato la computazione,
		 * 	-HALTED se la MdT ha terminato correttamente la computazione
		 * 	-TAPE_ERROR se la testina e' caduta dal nastro
		 * 
		 * Riepilogo degli ingredienti:
		 * 
		 * classe Tape, metodi: 
		 * 		Symbol cellRead(); 
		 * 		void cellWrite(Symbol);  
		 * 		void movement(Move) throws ApplicativeException;
		 * classe TransitionTable, metodi:
		 * 		TransitionTableValue getTriplet(State,Symbol);
		 * 		restituisce null se la funz di transizione non e' def su (stato, simbolo)
		 * classe TransitionTableValue, metodi:
		 * 		State getNewState();
		 * 		Symbol getNewSymbol();
		 * 		Move getMove();
		 *************/

		Symbol currentSymbol = tape.cellRead();
		TransitionTableValue delta = transitionTable.getTriplet(currentState, currentSymbol);

		if(delta == null)
			return HALTED;

		tape.cellWrite(delta.getNewSymbol());
		currentState = delta.getNewState();
		try {
			tape.movement(delta.getMove());
			return RUNNING;
		} catch (ApplicativeException e) {
			return TAPE_ERROR;
		}
	}

	public State getCurrentState() {
		return currentState;
	}
	
	public TapeInterface getTape(){
		return tape;
	}
	
	public String getTapeString() {
		return tape.getTapeString();
	}
		
	public int getHeadPosition(){
		return tape.getHeadPosition();
	}
	
	

	@Override
	public String toString(){		//writes to string the snapshot of the TM
		/****************
		 * DA IMPLEMENTARE!
		 * deve restituire  una stringa che descrive (con la sintassi che preferite) la configurazione attuale
		 * (notate che il metodo toString() della classe Tape gia' restituisce 
		 * la configurazione di nastro: simboli e posizione della testina)
		 ****************/
		StringBuilder line1 = new StringBuilder();
		StringBuilder line2 = new StringBuilder();
		for(int i = 0; i<tape.getHeadPosition(); i++){
			line1.append(" ");
			line2.append(" ");
		}
		line1.append("↑\n");
		line2.append(currentState).append("\n");

		return tape.getTapeString().replace(" ", "␣") + "...\n" +  line1.toString() + line2.toString() + "--------------------------------------------" ;
	}


	
}
