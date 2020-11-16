/******************************************************************
 * Questa classe implementa una riduzione dal problema dell'Halt al
 * problema della terminazione su nastro vuoto.
 * Il metodo
 * public void reductionHalt2Blank(String haltInstance)
 * su input haltInstance, stringa della forma cx,
 * costruisce la codifica della che termina su nastro vuoto se e solo se
 * la MdT c termina su input x.  
 *******************************************************************/
package upo.gaborgalazzo.calc.hob;

import it.uniupo.utilityLib.*;

import java.util.HashSet;


public class Halt2Blank
{
	TTRepStandard mdt; // questa sara' la MdT che termina su nastro vuoto
	// se e solo se la MdT
	// c termina su x

	Halt2Blank()
	{        //costruttore gia' fatto: non c'e' niente da aggiungere
		mdt = TTRep.getInstance();
	}

	public TTRepStandard getMdt()
	{ //getter gia' fatto: ignorare questo metodo
		return mdt;
	}

	/*
	 * public static String getInput(String haltInstance)
	 * metodo che estrae dall'istanza haltInstance (della forma cx) la stringa x
	 */
	public static String getInput(String haltInstance)
	{
		return haltInstance.split("000",3)[2];
	}

	/*
	 * public static TTRepStandard getMdT(String haltInstance)
	 * metodo che estrae dall'istanza haltInstance (della forma cx) la mdt
	 * codificata da c
	 */
	public static TTRepStandard getMdT(String haltInstance)
	{
		/****
		 * da implementare
		 * utile il metodo
		 * TTRepStandard TMCodec.decodeMdT(String) della libreria utilityLib
		 */
		return TMCodec.decodeMdT(haltInstance.substring(0,haltInstance.indexOf("000",4)+3));
	}

	/*
	 * public void writeInput(String input)
	 * metodo che aggiunge alla mdt le righe di delta che scrivono la
	 * stringa input sul nastro
	 */
	public void writeInput(String input)
	{
		mdt.addLine("q0",TransitionTableRepInterface.BLANK_CHAR,"q1",TransitionTableRepInterface.BLANK_CHAR,'R');
		for (char c:input.toCharArray() )
		{
			mdt.addLine("q" + (mdt.getMaxStateNumber()), TransitionTableRepInterface.BLANK_CHAR,"q"+(mdt.getMaxStateNumber()+1), c, 'R' );
		}

	}

	/*
	 * metodo che estrae dall'istanza haltInstance (della forma cx) i caratteri
	 * di cui e' composto x ---gia' fatto
	 */
	public static HashSet<Character> getCharsInInput(String haltInstance)
	{
		HashSet<Character> cset = new HashSet<Character>();
		String[] tokens = haltInstance.split("000", 3);
		String input = tokens[tokens.length - 1];
		for (int i = 0; i < input.length(); ++i)
		{
			cset.add(input.charAt(i));
		}
		return cset;
	}

	/*
	 * public void goBeginningOfInput(String haltInstance)
	 * metodo che aggiunge alla mdt le righe di delta per tornare
	 * all'inizio della stringa x
	 */
	public void goBeginningOfInput(String haltInstance)
	{
		mdt.addLine("q"+(mdt.getMaxStateNumber()), TransitionTableRepInterface.BLANK_CHAR,"q"+(mdt.getMaxStateNumber()+1),TransitionTableRepInterface.BLANK_CHAR,'L');
		String q = "q"+(mdt.getMaxStateNumber());
		for (Character c:getCharsInInput(haltInstance))
		{
			mdt.addLine(q, c,q,c,'L');
		}
		mdt.addLine(q, TransitionTableRepInterface.BLANK_CHAR,"q"+(mdt.getMaxStateNumber()+1),TransitionTableRepInterface.BLANK_CHAR,'R');

	}

	/*
	 * public void buildSimulatingMdT(String haltInstance)
	 * metodo che costruisce la MdT simulante
	 */
	public void buildSimulatingMdT(String haltInstance)
	{
		/****
		 * da implementare
		 * attenzione! i metodi writeInput(input) e
		 * goBeginningOfInput(haltInstance) vanno utilizzati!!
		 * Per comporre due MdT mdt1 e mdt2 in una mdt3: 
		 * mdt3 = Tools.compose(mdt1,mdt2)
		 */
		String input = getInput(haltInstance);
		writeInput(input);
		goBeginningOfInput(haltInstance);
		mdt = Tools.compose(mdt, getMdT(haltInstance));
	}

	/*
	 * public String reductionHalt2Blank(String haltInstance)
	 * metodo che calcola la riduzione
	 */
	public String reductionHalt2Blank(String haltInstance)
	{
		/****
		 * da implementare
		 * notate che la riduzione non restituisce una MdT ma la sua codifica
		 * utile il metodo String TMCodec.encodeMdT(TTRepStandard)
		 */

		buildSimulatingMdT(haltInstance);
		return TMCodec.encodeMdT(mdt);
	}


}
