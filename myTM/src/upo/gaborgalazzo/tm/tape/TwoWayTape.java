package upo.gaborgalazzo.tm.tape;

import it.uniupo.mdtLib.*;

import java.util.ArrayList;

public class TwoWayTape implements TapeInterface {

    private ArrayList<Symbol> tape;   //il nastro
    private int head = 0;			 //la posizione della testina di lettura e scrittura
    private Symbol blank;

    public TwoWayTape() {
        DynamicEnvironment workEnv = DynamicEnvironment.getInstance();
        blank = workEnv.getBlankSymbol();
        this.init();
    }

    public void init(){
        tape = new ArrayList<Symbol>(); //crea il nastro
        tape.add(blank);				//ci mette una cella vuota
        head = 0;						//la testina sulla prima cella
    }

    public Symbol cellRead() {
        return tape.get(head);			//legge il simbolo nella cella osservata dalla testina
    }

    public void cellWrite(Symbol s) {
        tape.set(head, s);				//scrive s sulla cella osservata
    }

    public void movement(Move m) throws ApplicativeException {
        //m e' un movimento e puo' essere: Move.RIGHT, Move.LEFT, Move.STILL
        if ((head == tape.size() - 1) && (m == Move.RIGHT)) {
            tape.add(blank);			//questa e' la fabbrica di celle
        }
        head = head + m.getDirection();  //m.getDirection restituisce -1 per Move.LEFT, 0 per Move.STILL, 1 per Move.Right
        if (head < 0) {					//testina caduta dal nastro
           tape.add(0,blank);
           head = 0;
        }
    }

    public int length() {
        return tape.size();
    }

    public String getTapeString() {		//restituisce la stringa scritta sul nastro
        StringBuilder buf = new StringBuilder(tape.size());
        for (int j = 0; j < tape.size(); ++j) {
            buf.append(tape.get(j).toString());
        }
        return new String(buf);
    }

    public int getHeadPosition() {
        return head;
    }

    public void setTape(String ts, int h) { //scrive ts sul nastro e mette la testina in posizione h
        this.init();
        int i = 0;
        if ((ts!= null) && !ts.isEmpty() && (h >= 0)) {
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

    public String toString() {	//scrive la configurazione di nastro, compresa la posizione della testina
        StringBuilder buf = new StringBuilder(tape.size());
        buf.append('|');
        for (int j = 0; j < tape.size(); ++j) {
            if (j == head) {
                buf.append(HEAD1);  //HEAD1 HEAD2 concatenati sono "->" (sono costanti di TapeInterface)
                buf.append(HEAD2);
            }
            buf.append(tape.get(j).toString());
        }
        return new String(buf);
    }

}
