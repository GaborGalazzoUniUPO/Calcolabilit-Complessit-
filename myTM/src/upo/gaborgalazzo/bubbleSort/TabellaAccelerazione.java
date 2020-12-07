package upo.gaborgalazzo.bubbleSort;

import java.util.Arrays;
import java.util.HashMap;

public class TabellaAccelerazione {
	
	private int numMacroCelle;
	private int emme;
	private int max;
    private HashMap<NastroCompresso,NastroCompresso> builtInTable;
	
	TabellaAccelerazione(int emme, int max, int numMacroCelle){
	 this.builtInTable = new HashMap<NastroCompresso,NastroCompresso>();
	 this.max = max;
	 this.emme = emme;
	 this.numMacroCelle = numMacroCelle;
	 fillTable();
	}
	 
	public NastroCompresso getMacroStep(NastroCompresso key) {
		return builtInTable.get(key);	
	}

	
	
	/****
	 * riempie la builtInTable con coppie (key,value) dove key e value sono NastroCompresso e value e' key
	 * modificata da emme passi di bubble sort
	 * genera in sequenza tutti i possibili int[] di lunghezza numMacroCelle*emme, su alfabeto {0,1,2,...,max},
	 * chiama il metodo che esegue gli emme passi di bubble sort, 
	 * trasforma gli int[] in NastroCompresso e inserisce nella tabella le coppie
	 * 
	 * Serviranno seguenti metodi di questa stessa classe:
	 * 1) int[] initialPattern() per generare int[] contenente tutti 0
	 * 2) boolean succ(int[] a)  per generare l'int[] successivo: restituisce true se ha generato 
	 * di nuovo quello con tutti 0, false altrimenti
	 * 3) int[] bubbleSortOnePass(int[] a)  per eseguire gli emme passi
	 * 
	 * 4) per creare un NastroCompresso da un int[]: costruttore NastroCompresso(int[] nastroNormale, int emme, int max)
	 * 
	 * 5) per riempire l'HashMap: put(key, value)
	 */
		public void fillTable () {  

			int[] seq = initialPattern();
			do{
				int[] sort = bubbleSortEmmeSteps(seq);
				builtInTable.put(new NastroCompresso(seq,emme,max), new NastroCompresso(sort,emme,max));
			}while (!succ(seq));
		}
		
		
		/****
		 * esegue m passi di bubble sort sul suo input e restituisce un nuovo array
		 * @param a
		 * @return int[] su cui sono stati fatti emme passi di bubble sort
		 */
		public  int[] bubbleSortEmmeSteps(int[] a) {
			int[] result = Arrays.copyOf(a,a.length);
			for(int i = 0; i<emme; i++){
				if(result[i]>result[i+1])
				{
					result[i] = result[i + 1] + result[i];
					result[i + 1] = result[i] - result[i + 1];
					result[i] = result[i] - result[i + 1];
				}
			}
			return result;
		}
		
		
		//*****i due metodi seguenti generano tutti i possibili int[] di lunghezza numMacroCelle*emme
		//*****su alfabeto {0,1,2,...,max}
		
		/***
		 * genera il primo pattern da usare come key nella builInTable
		 * @return il pattern, che contiene tutti 0
		 */
		private int[] initialPattern() {
			int[] a = new int[numMacroCelle*emme];
			Arrays.fill(a,0);
			return a;
		}	
		
		/***
		 * genera il prossimo int[] su cui basare la key nella builtInTable
		 * @param a
		 * @return restituisce true se c'e' un riporto, ovvero se e' stato generato di nuovo il pattern iniziale
		 */
		private boolean succ(int[] a) {
			int i = a.length-1;
			boolean riporto = true;
			while (riporto && i >= 0) {
				if (a[i] < max) {
					a[i]++;
					riporto = false;
				} else {
					a[i]=0;
			}
				i--;
			}
			return riporto;
			
		}	
				
		
		
		
		
}
