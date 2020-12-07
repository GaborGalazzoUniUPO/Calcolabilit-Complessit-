package upo.gaborgalazzo.bubbleSort;

import java.util.ArrayList;

public class Macrocella {
	
	private ArrayList<Integer> contenuto;
	private int emme;
	
	Macrocella(ArrayList<Integer> al, int emme){
		if (al.size() != emme) throw new IllegalArgumentException();
		this.contenuto = al;
		this.emme = emme;
	}
	
	/***
	 * restituisce l'i-esimo elemento della macrocella, se esiste, altrimenti lancia una IllegalArgumentException
	 * @param i
	 * @return
	 */
	public int get(int i) {
		if(i < 0 && i >= emme) throw new IllegalArgumentException();
		else return contenuto.get(i);
	}
	
	public int getEmme() {
		return emme;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
	    if (!(o instanceof Macrocella)) return false;
	    Macrocella mc = (Macrocella) o;	
		if (this.emme != mc.emme) return false;
		else {
			int j = 0;
			while (j < emme) {
				if(this.contenuto.get(j) != mc.contenuto.get(j)) {
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
		for (int i=0; i < this.emme; ++i) {
			dgst = 37* dgst + this.get(i);
		}
	    return dgst;
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("[");
		for (int i =0; i < this.getEmme(); ++i) {
			buf.append(this.get(i));
		}
		buf.append("]");
	
		return new String(buf);
		
	}
	
}
