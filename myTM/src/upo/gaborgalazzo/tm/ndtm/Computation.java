package upo.gaborgalazzo.tm.ndtm;

import it.uniupo.mdtLib.ApplicativeException;
import it.uniupo.mdtLib.State;
import it.uniupo.mdtLib.Symbol;
import it.uniupo.mdtLib.TapeInterface;
import it.uniupo.mdtLib.TransitionTableValue;
import static it.uniupo.mdtLib.EnvironmentStaticInterface.RUNNING;
import static it.uniupo.mdtLib.EnvironmentStaticInterface.HALTED;
import static it.uniupo.mdtLib.EnvironmentStaticInterface.TAPE_ERROR;

public class Computation {
	private State currentState;
	private TapeInterface tape;
	
	public Computation(State s, String tstring, int hpos){
		tape = new TwoWayTape();
		if((s!=null)&&(tstring!=null)&&!(hpos<0)){
		setTape(tstring, hpos);
		currentState = s;
		}
	}
	
	public char step(TransitionTableValue triplet){   //esegue un passo della computazione, deterministicamente
		char runState = RUNNING;
		if(triplet == null) {
			runState = HALTED;
		} else {
		tape.cellWrite(triplet.getNewSymbol());
		try {
			tape.movement(triplet.getMove());
		} catch (ApplicativeException e) {
			runState = TAPE_ERROR;
		}
		currentState = triplet.getNewState();
	
		}
		return runState;
	}

	public Symbol getCurrentSymbol(){
		return tape.cellRead();
	}
	
	public State getCurrentState() {
		return currentState;
	}
	
	public void setTape(String tstring, int hpos){
		tape.setTape(tstring, hpos);
	}
	
	public void setCurrentState(State s){
		currentState = s;
	}
	
	public String getTapeString() {
		return tape.getTapeString();
	}
		
	public int getHeadPosition(){
		return tape.getHeadPosition();
	}
	
	@Override
	public String toString(){
		return currentState+", "+tape.toString();
	}
	
}
