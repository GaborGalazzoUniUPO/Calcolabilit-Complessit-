package upo.gaborgalazzo.calc.enumer;

import it.uniupo.enumLib.Enumeratore;
import it.uniupo.enumLib.Orpal;
//import it.uniupo.enumLib.WaW;

public class Es1SeEnumeratoRE {
	Enumeratore enumeratore = new Orpal();
//	Enumeratore enumeratore = new WaW();
	
	// se un linguaggio ha un enumeratore,
	//allora e' r.e.
	//costruite il metodo che lo accetta, a partire
	//dall'enumeratore "enumeratore"
	 void acceptor(String x) {
		enumeratore.reinit();
		while(true){
			if(enumeratore.next().equals(x)){
				return;
			}
		}
	}
	
	//per il prossimo esercizio serve una macchina accettante
	//che simuli la macchina accettante per un numero di passi
	//finito, specificato in input
	  boolean acceptorLimitato(String x, int passi) {
		enumeratore.reinit();
		  for(int i = 0; i<passi; i++){
			  if(enumeratore.next().equals(x)){
				  return true;
			  }
		  }
		return false;
	}
	
}
