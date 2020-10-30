package upo.gaborgalazzo.calc.enumer;


import it.uniupo.enumLib.Orpal;
import it.uniupo.enumLib.Riconoscitore;
import it.uniupo.enumLib.SLO;
import it.uniupo.enumLib.StringGenerator;
//import it.uniupo.enumLib.WaW;

public class Es3RicSseEnumOrdinato {
	Riconoscitore ric = new Orpal();
//	Riconoscitore ric = new WaW();

	StringGenerator stringGenerator = new StringGenerator();
	//se il linguaggio e' ricorsivo, ha un enumeratore ordinato
	//usate il metodo che decide il linguaggio (ric.belogsTo())
	//per costruire l'enumeratore
	   public String enumeratoreOrdinato(int num) {
	   	stringGenerator.reinit();
		int i = -1;
		String current;
		do {
			current = stringGenerator.next();
			if (ric.belongsTo(current)) {
				i++;
			}
		}while (i!=num);
		   return current;
	}
	
	   //se il linguaggio ha un enumeratore ordinato, e' ricorsivo
	   //usate l'enumeratore ordinato appena costruito
	   // per costruire un metodo che decide il linguaggio
	  boolean belongsTo(String stringa) {
		  stringGenerator.reinit();
		  String current;
		  do {
			  current = stringGenerator.next();
			  if (ric.belongsTo(current)) {
				 if(current.equals(stringa)){
				 	return true;
				 }
				 if(SLO.compare(current, stringa) > 0){
				 	return false;
				 }
			  }
		  }while (true);
	}
}
