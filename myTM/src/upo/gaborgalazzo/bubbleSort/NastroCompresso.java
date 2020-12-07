package upo.gaborgalazzo.bubbleSort;

import java.util.ArrayList;
import java.util.List;


public class NastroCompresso {
	private List<Macrocella> nastro;
	private int originalLength;
	private int emme;
	
	
	/***
	 * genera un nastro compresso da un array
	 * @param nastroNormale array di partenza
	 * @param emme dell'accelerazioen lineare
	 * @param max  maggiore  o uguale al numero massimo che compare nell'array
	 */
	NastroCompresso (int[] nastroNormale, int emme, int max) {
		originalLength = nastroNormale.length;
		this.emme = emme;
		nastro = new ArrayList<Macrocella>();
		for (int i = 0; i < nastroNormale.length; i = i+this.emme) {		
			ArrayList<Integer> macrocella = new ArrayList<Integer>();
			int j = 0;
			for (; j < this.emme && i+j < nastroNormale.length; j++) {
				macrocella.add(nastroNormale[i+j]);
			}
			if (macrocella.size() < emme) {
				for (; j < this.emme; j++) {
					macrocella.add(max);
				}
				
			}				
			nastro.add(new Macrocella(macrocella, emme));
		}	
		
	}

	
	/***
	 * genera un nastro compresso vuoto
	 * @param emme
	 */
	NastroCompresso(int emme) {
		originalLength = 0;
		this.emme = emme;
		nastro = new ArrayList<Macrocella>();
	}
	
	/***
	 * restituisce il nastro non compresso
	 * @return
	 */
	public int[]  decomprimi (){
		int[] nastroNormale = new  int[originalLength];
		for (int i = 0; i < nastro.size(); ++i) {
			for (int j = 0; j < emme && i* emme+j < originalLength; j++) {
				nastroNormale[i*emme+j]=nastro.get(i).get(j);
			}
		}
		return nastroNormale;
	}
	
	public int getLength() {
		return nastro.size();
	}
	
	/***
	 * restituisce la i-esima macrocella, se esiste
	 * @param i
	 * @return i-esima macrocella o se i non e' un indice legittimo lancia l'eccezione IllegalArgumentException
	 */
	public Macrocella get(int i) {
		if (i<0 || i>nastro.size()) throw new IllegalArgumentException();
		else return nastro.get(i);
	}
	
	/***
	 * sostituisce la porzione di nastro a partire da pos con nc, fino alla fine del sorgente o del target (whichever comes first)
	 * @param nc
	 * @param pos
	 */
	public void replace(NastroCompresso nc, int pos) {
		for(int j = 0; j < nc.getLength() && pos + j < this.getLength(); j++) {
			this.nastro.set(pos+j,nc.get(j));
		}
	}
	
	/***
	 * Restituisce una porzione del nastro compresso dall'indice di inizio, incluso, all'indice di fine escluso
	 * @param inizioIncluso
	 * @param fineEsclusa
	 * @return un segmento del nastro: dall'indice di inizio, incluso, all'indice di fine escluso
	 */
	public NastroCompresso subNastroCompresso (int inizioIncluso, int fineEsclusa) {
		NastroCompresso nc = new NastroCompresso(this.emme);
		for (int i=inizioIncluso; i< fineEsclusa; i++) {
			nc.nastro.add(this.get(i));
		}
		nc.originalLength = nc.nastro.size()*emme;
		return nc;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
	    if (!(o instanceof NastroCompresso)) return false;
	    NastroCompresso nc = (NastroCompresso) o;
	    if (this.originalLength != nc.originalLength) {
				return false;
			} else if (this.emme != nc.emme) {
				return false;
			} else if (this.getLength() != nc.getLength()) {
				return false;
			} else {
			int j = 0;
			while (j < this.getLength()) {
				if (!this.get(j).equals(nc.get(j))) {
					return false;
				}
				j++;
				
			}
			}
			return true;
		}
	
	@Override
	public int hashCode() {
		 int dgst = emme;
		for (int i=0; i < this.getLength(); ++i) {
			dgst = 37 * dgst + (this.get(i)==null? 0 : this.get(i).hashCode());
		}
	    return dgst;
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("[");
		for (int i =0; i < this.getLength(); ++i) {
			buf.append(this.get(i));
		}
		buf.append("]");
	
		return new String(buf);
		
	}
	
	
}

