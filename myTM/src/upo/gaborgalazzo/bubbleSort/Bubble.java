package upo.gaborgalazzo.bubbleSort;



public class Bubble {

	private static final int MACROCELLE_TABELLA = 2; //questo e' un parametro fisso per il bubble sort


	private int max; //maggiore o uguale all'intero massimo che comparira' negli array da ordinare
	private int emme;	//emme dell'accelerazione lineare
    private TabellaAccelerazione tabella; //la tabella dell'accelerazione lineare

	
	
	public Bubble (int m, int max) {
		this.emme = m;
	    this.max = max;
	    this.tabella = new TabellaAccelerazione(emme, this.max, MACROCELLE_TABELLA);
	}


	
	/***
	 * il bubble sort accelerato, che si avvale della tabella per fare ad ogni iterazioen emme iterazioni
	 * del bubble sort
	 * @param daordinare lista da ordinare
	 * @return lista ordinata
	 * 
	 * per questo metodo servono:
	 * 
	 *1) costruttore NastroCompresso (int[] nastroNormale, int emme, int max)  che comprime il nastro;
	 *2) il metodo della classe NastroCompresso: NastroCompresso subNastroCompresso (int inizioIncluso, int fineEsclusa)
	 *che restituisce una porzione di NastroCompresso come NastroCompresso;
	 *3) il metodo della classe NastroCompresso: void replace(NastroCompresso nc, int pos) che sostituisce
	 *nc alla porzione di NastroCompresso che comincia a pos (si ferma alla fine di nc o, se finisce prima,
	 *alla fine del NastroCompresso destinazione)
	 *4) il metodo della classe TabellaAcelerazione: NastroCompresso getMacroStep(NastroCompresso key) che
	 *restituisce la "key" modificata da emme passi di bubble sort
	 *5)il metodo equals di NastroCompresso
	 *6)ancora della classe NastroCompresso: int[]  decomprimi () che riconverte NastroCompresso in int[]
	 */
	public int[] accelBubble(int[] daordinare) {
		
		NastroCompresso nastroCompresso = new NastroCompresso(daordinare,emme,max);
		for(int j = 0; j<nastroCompresso.getLength()*emme; j++)
		for(int i = 0; i<nastroCompresso.getLength()-1; i++){
			NastroCompresso sub = nastroCompresso.subNastroCompresso(i,i+2);
			NastroCompresso swap = tabella.getMacroStep(sub);
			nastroCompresso.replace(swap,i);
		}
		return nastroCompresso.decomprimi();
	}
	
	
	
	
	
	
		
}
	


