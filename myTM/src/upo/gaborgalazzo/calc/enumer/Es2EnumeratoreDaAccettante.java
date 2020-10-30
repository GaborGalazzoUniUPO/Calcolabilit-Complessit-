package upo.gaborgalazzo.calc.enumer;

import it.uniupo.enumLib.DovetailingSI;
import it.uniupo.enumLib.Enumeratore;
import it.uniupo.enumLib.Pair;
import it.uniupo.enumLib.PairStringInt;

public class Es2EnumeratoreDaAccettante implements Enumeratore {
	private final DovetailingSI dovetailingSI;
	Es1SeEnumeratoRE esercizio;

	private PairStringInt currentPair;
    
    public Es2EnumeratoreDaAccettante() {
    	esercizio = new Es1SeEnumeratoRE();
    	dovetailingSI = new DovetailingSI();
    	/*
    	 * forse non completo
    	 */
    }
    
    public void reinit() {
    	/*
    	 * non serve
    	 */
    }

    public String next() {
		do {
			currentPair = dovetailingSI.next();
		}while (!esercizio.acceptorLimitato(currentPair.left(), currentPair.right()));
		return currentPair.left();
	}
	
}
