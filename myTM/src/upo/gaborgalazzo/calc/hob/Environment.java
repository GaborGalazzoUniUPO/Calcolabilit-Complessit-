package upo.gaborgalazzo.calc.hob;

public class Environment {
	public static final char BLANK_CHAR = ' '; /*attenzione! se si cambia il BLANK_CHAR si devono chiamare
	 											* i metodi TransitionTableRep.encodeMdT e decodeMdT con
	 											* Environment.BLANK_CHAR come parametro aggiuntivo
	 											*/
	public static final int BASE = 10;
	public final static int LENGTH = 2;
	static final String DELTA_IN = "src/main/resources/delta_vecchia";
	static final String DELTA_OUT = "src/main/resources/delta_nuova";
	

}
