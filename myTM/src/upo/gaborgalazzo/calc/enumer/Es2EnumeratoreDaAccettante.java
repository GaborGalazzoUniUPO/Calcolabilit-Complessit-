package upo.gaborgalazzo.calc.enumer;

import it.uniupo.enumLib.Enumeratore;

public class Es2EnumeratoreDaAccettante implements Enumeratore {
    Es1SeEnumeratoRE esercizio; 
    
    public Es2EnumeratoreDaAccettante() {
    	esercizio = new Es1SeEnumeratoRE();
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
		/*
		 * da implementare
		 */
		return null;
	}
	
}
