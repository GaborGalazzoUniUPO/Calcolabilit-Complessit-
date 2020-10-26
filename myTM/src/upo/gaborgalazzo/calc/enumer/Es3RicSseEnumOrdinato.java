package upo.gaborgalazzo.calc.enumer;


import it.uniupo.enumLib.Orpal;
import it.uniupo.enumLib.Riconoscitore;
//import it.uniupo.enumLib.WaW;

public class Es3RicSseEnumOrdinato {
	Riconoscitore ric = new Orpal();
//	Riconoscitore ric = new WaW();
	
	//se il linguaggio e' ricorsivo, ha un enumeratore ordinato
	//usate il metodo che decide il linguaggio (ric.belogsTo())
	//per costruire l'enumeratore
	   public String enumeratoreOrdinato(int num) { 
		/*
		 * 	da implementare
		 */
		   return null;
	}
	
	   //se il linguaggio ha un enumeratore ordinato, e' ricorsivo
	   //usate l'enumeratore ordinato appena costruito
	   // per costruire un metodo che decide il linguaggio
	  boolean belongsTo(String stringa) {
		/*
		 * da implementare
		 */
		  return false;
	}
}
