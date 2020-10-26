package upo.gaborgalazzo.calc.enumer;

import it.uniupo.enumLib.Pair;

//genera tutte le coppie di numero naturali, in ordine arbitrario
//sono ammesse ripetizioni
//ogni coppia deve essere generata in tempo finito
//il metodo next() genera la successiva coppia
public class Es0DovetailingIntInt {

	/*
	 * potete aggiungere le variabili di istanza che volete
	 */

	private int currentNum = 0;
	private int currentDenum = 0;
	private int d = 0;

	
	public Es0DovetailingIntInt(){
		currentNum = 0;
		currentDenum = 0;
		d = 0;
	}
	
	public Pair next() {
		Pair result = new Pair(currentNum, currentDenum);
		if(currentNum == 0){
			currentNum = ++d;
			currentDenum = 0;
		}else{
			currentNum--;
			currentDenum++;
		}
		return result;
	}
	
	
}
