/**************************************************
 * A two-way, single track tape
 * 
 * @author Lavinia Egidi
 * @date January 2015
 ************************************************/
package upo.gaborgalazzo.tm.ndtm;

import java.util.ArrayList;

import it.uniupo.mdtLib.ApplicativeException;
import it.uniupo.mdtLib.DynamicEnvironment;
import it.uniupo.mdtLib.EnvironmentStaticInterface;
import it.uniupo.mdtLib.Move;
import it.uniupo.mdtLib.Symbol;
import it.uniupo.mdtLib.TapeInterface;

public class TwoWayTape implements TapeInterface {
	
	/*
	*nastro infinito
	*/

	private ArrayList<Symbol> tape;
	private int head = 0;
	private Symbol blank;


	public TwoWayTape() {
		EnvironmentStaticInterface workEnv = DynamicEnvironment.getInstance();
		blank = workEnv.getBlankSymbol();
		this.init();
	}
	
	public void init(){
		tape = new ArrayList<Symbol>();
		tape.add(blank);
		head = 0;
	}

	public Symbol cellRead() {
		return tape.get(head);
	}

	public void cellWrite(Symbol s){
	   tape.set(head, s);	
	}
	
	public void movement(Move m) throws ApplicativeException{
		EnvironmentStaticInterface workEnv = DynamicEnvironment.getInstance();

		if ((head == tape.size()-1) && (m == Move.RIGHT)){
			tape.add(workEnv.getBlankSymbol());
		}
		head = head + m.getDirection();
		if(head<0){
			tape.add(0,workEnv.getBlankSymbol());
			head=0;
		}
	}

public int length() {
		return tape.size();
	}
	
	public String getTapeString(){
		StringBuilder buf = new StringBuilder(tape.size());
		for(int j=0; j<tape.size(); ++j ){
			buf.append(tape.get(j).toString());	
		}
		return new String(buf);
	}

	public int getHeadPosition(){
		return head;
	}
	
	public void setTape(String ts, int h) {
		this.init();
		int i = 0;
		if ((ts!= null) && !ts.isEmpty() && h >= 0 && h < ts.length()) {
			for (i = 0; i < ts.length() - 1; ++i) {
				this.cellWrite(Symbol.getSymbol(ts.charAt(i)));
				try {
					this.movement(Move.RIGHT);
				} catch (ApplicativeException e) {
					e.printStackTrace();
				}
			}
			this.cellWrite(Symbol.getSymbol(ts.charAt(ts.length() - 1)));			
		}
		while (i < h+1 ) {
			try {
				this.movement(Move.RIGHT);
			} catch (ApplicativeException e) {
				e.printStackTrace();
			}
			this.cellWrite(blank);
			++i;
		}
		this.setHeadPosition(h);
	}

	public void setHeadPosition(int h) {
		head = h;
	}
	
	 public String toString(){
		StringBuilder buf = new StringBuilder(tape.size());
		buf.append('|');
		for(int j=0; j<tape.size(); ++j ){
			if(j==head){
				buf.append(HEAD1);
				buf.append(HEAD2);
			}
			buf.append(tape.get(j).toString());	
		}
		return new String(buf);
	}

	
}
