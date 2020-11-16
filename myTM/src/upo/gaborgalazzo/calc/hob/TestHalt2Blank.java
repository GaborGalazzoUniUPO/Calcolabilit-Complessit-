package upo.gaborgalazzo.calc.hob;
import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import org.junit.runners.MethodSorters;

import it.uniupo.utilityLib.TMCodec;
import it.uniupo.utilityLib.TTRepStandard;
import it.uniupo.utilityLib.Tools;

import org.junit.FixMethodOrder;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class TestHalt2Blank {

	String testHaltInst= "0001010110101110011011101101110111000110";
	String testHaltInst2= "00010101101011100110111011011101110001100001101000";
	Halt2Blank testRed;
	TTRepStandard mdt;
	
	@Before
	public void setUp(){
		testRed = new Halt2Blank();
	}
	
	@Test
	public void test01Create(){
		assertNotNull("Errore! la mdt dell'oggetto Halt2Blank non e' creata correttamente", testRed.getMdt());
	}
	
	@Test
	public void test10GetInput(){
		String input = Halt2Blank.getInput(testHaltInst);
		assertTrue("l'input calcolato non corrisponde a quello di test", input.equals("110"));
	}
	
	@Test
	public void test20GetInput2(){
		String input = Halt2Blank.getInput(testHaltInst2);
		assertTrue("l'input calcolato non corrisponde a quello di test", input.equals("1100001101000"));
	}
	
	@Test
	public void test30GetMdT() {
		TTRepStandard ttr = Halt2Blank.getMdT(testHaltInst);
			assertTrue("la seconda riga di delta non corrisponde",ttr.getCurrentSymbol(1) == '1');
			assertTrue("nella MdT di test delta(q1,1) e' definito!", ttr.isDefined(1, '1') == true);
	}
	
	@Test
	public void test40WriteInput() {
		String input = Halt2Blank.getInput(testHaltInst);
		testRed.writeInput(input);
		assertTrue("per spostarsi dal primo blank e scrivere 3 caratteri, la MdT deve avere almeno 4 stati", testRed.getMdt().getMaxStateNumber() >= 4);
	}
	
	@Test
	public void test50GetCharsInInput(){
		HashSet<Character> cset = Halt2Blank.getCharsInInput(testHaltInst);
		assertTrue("l'input e' 110, contiene 2 caratteri",cset.size() == 2);
		assertTrue("l'input e' 110, 0 e' nell'input",cset.contains('0'));
		assertTrue("l'input e' 110, 1 e' nell'input",cset.contains('1'));
	}
	
	@Test
	public void test60GoBeginningOfInput() {
		testRed = new Halt2Blank();
		String input = Halt2Blank.getInput(testHaltInst);
		testRed.writeInput(input);
		int maxWrite = testRed.getMdt().getMaxStateNumber();
		testRed.goBeginningOfInput(testHaltInst);
		
		int lineGoLeftOnB = -1; //cerca una linea di delta aggiunta da goBeginningOfInput
								//per cui delta(qi,blank) specifica spostam a sinistra per qualche i
								//(in realta' ci si aspetta che ce ne sia una sola a questo punto)
		for (int i = maxWrite-1; (i<=testRed.getMdt().getMaxStateNumber()); ++i){
			int tmp = testRed.getMdt().whereDefined(i,Environment.BLANK_CHAR);
			if ((tmp > -1) && (testRed.getMdt().getMove(tmp) == 'L')){
				lineGoLeftOnB = tmp;
			}
		}
			//qui controlla che per l'ultimo stato usato da writeInput qM
			//sia definito che delta(qM,blank) specifichi L o S
		int lineMaxBlank = testRed.getMdt().whereDefined(maxWrite,Environment.BLANK_CHAR);
		if ((lineMaxBlank>=0) && ((testRed.getMdt().getMove(lineMaxBlank) == 'L') ||
				(testRed.getMdt().getMove(lineMaxBlank) == 'S'))){
			lineGoLeftOnB = lineMaxBlank;
		}
		assertTrue("dimenticato di collegare la scrittura dell'input con il ritorno all'inizio del nastro?", lineGoLeftOnB > 0);
		
		//cerca una linea di delta aggiunta da goBeginningOfInput
		//per cui delta(qi,0) specifica spostam a sinistra
		int lineGoLeftOn0 = -1;
		for (int i = maxWrite-1; (i<=testRed.getMdt().getMaxStateNumber()); ++i){
			int tmp = testRed.getMdt().whereDefined(i,'0');
			if ((tmp > -1) && (testRed.getMdt().getMove(tmp) == 'L')){
				lineGoLeftOn0 = tmp;
			}
		}
		
		//cerca una linea di delta aggiunta da goBeginningOfInput
		//per cui delta(qi,1) specifica spostam a sinistra
		assertTrue("manca una riga di delta per andare a sinistra su 0", lineGoLeftOn0 > 0);
		int lineGoLeftOn1 = -1;
		for (int i = maxWrite-1; (i<=testRed.getMdt().getMaxStateNumber()); ++i){
			int tmp = testRed.getMdt().whereDefined(i,'1');
			if ((tmp > -1) && (testRed.getMdt().getMove(tmp) == 'L')){
				lineGoLeftOn1 = tmp;
			}
		}
		assertTrue("manca una riga di delta per andare a sinistra su 1", lineGoLeftOn1 > 0);
	}
	
	@Test
	public void test65NumberOfStatesSimulatingMdT() {
		Halt2Blank tr2 = new Halt2Blank();
		String input = Halt2Blank.getInput(testHaltInst);
		tr2.writeInput(input);
		tr2.goBeginningOfInput(testHaltInst);
		int totStatiInputandGoBack = tr2.getMdt().getMaxStateNumber();
		
		TTRepStandard mdtOrig = Halt2Blank.getMdT(testHaltInst);
		int totStatiOrig = mdtOrig.getMaxStateNumber();
		
		testRed = new Halt2Blank();
		testRed.buildSimulatingMdT(testHaltInst);
		
		assertTrue("la MdT simulante deve avere almeno tutti gli stati che servono per scrivere l'input sul nastro piu'"
				+ "quelli che servono per tornare indietro, piu' quelli della mdt da simulare", testRed.getMdt().getMaxStateNumber() >= (totStatiInputandGoBack + totStatiOrig));
	}
	
	@Test
	public void test70buildSimulatingMdT() { //controlla che la macchina simulata vada all'inizio dell'input
		
		Halt2Blank tr2 = new Halt2Blank();
		String input = Halt2Blank.getInput(testHaltInst);
		tr2.writeInput(input);
		int maxWrite = tr2.getMdt().getMaxStateNumber();
		testRed = new Halt2Blank();
		testRed.buildSimulatingMdT(testHaltInst);
				
		int lineGoLeftOnB = -1;
		for (int i = maxWrite-1; (i<=testRed.getMdt().getMaxStateNumber()); ++i){
			int tmp = testRed.getMdt().whereDefined(i,Environment.BLANK_CHAR);
			if ((tmp > -1) && (testRed.getMdt().getMove(tmp) == 'L')){
				lineGoLeftOnB = tmp;
			}
		}
		
		int lineMaxBlank = testRed.getMdt().whereDefined(maxWrite,Environment.BLANK_CHAR);
		if ((lineMaxBlank>0) && ((testRed.getMdt().getMove(lineMaxBlank) == 'L') ||
				(testRed.getMdt().getMove(lineMaxBlank) == 'S'))){
			lineGoLeftOnB = lineMaxBlank;
		}
		assertTrue("scritto l'input sul nastro? la MdT torna indietro? dimenticato di collegare la scrittura dell'input con il ritorno all'inizio del nastro?", lineGoLeftOnB > 0);
		
        		
		int lineGoLeftOn0 = -1;
		for (int i = maxWrite-1; (i<=testRed.getMdt().getMaxStateNumber()); ++i){
			int tmp = testRed.getMdt().whereDefined(i,'0');
			if ((tmp > -1) && (testRed.getMdt().getMove(tmp) == 'L')){
				lineGoLeftOn0 = tmp;
			}
		}
		assertTrue("manca una riga di delta per andare a sinistra su 0", lineGoLeftOn0 > 0);
		int lineGoLeftOn1 = -1;
		for (int i = maxWrite-1; (i<=testRed.getMdt().getMaxStateNumber()); ++i){
			int tmp = testRed.getMdt().whereDefined(i,'1');
			if ((tmp > -1) && (testRed.getMdt().getMove(tmp) == 'L')){
				lineGoLeftOn1 = tmp;
			}
		}
		assertTrue("manca una riga di delta per andare a sinistra su 1", lineGoLeftOn1 > 0);
	}
	
	@Test   
	public void test80buildSimulatingMdT(){
		TTRepStandard ttr = Halt2Blank.getMdT(testHaltInst);
		testRed.buildSimulatingMdT(testHaltInst);

		int maxOrig = ttr.getMaxStateNumber(); 
		int diff = testRed.getMdt().getMaxStateNumber() - maxOrig;		
		
		assertTrue("Se la MdT simulante ha meno stati della simulata, c'e' qualcosa che non va",diff>0);
		
		for(int i = 0; i<=maxOrig; ++i){
			if (ttr.whereDefined(i, '0')>0){
				int lineOrig = ttr.whereDefined(i, '0');
				int lineSimulator = testRed.getMdt().whereDefined(diff+i,'0');
			assertTrue("the original and new MdTs must be defined on the corresponding state,symbol pairs (symbol 0)", lineSimulator>0);
			assertTrue("the original and new MdTs must be defined to write the same new symbol on the corresponding state,symbol pairs (symbol 0)",(ttr.getNewSymbol(lineOrig) == (testRed.getMdt().getNewSymbol(lineSimulator))));
			assertTrue("the original and new MdTs must be defined to move the head the same way on the corresponding state,symbol pairs (symbol 0)",(ttr.getMove(lineOrig) == (testRed.getMdt().getMove(lineSimulator))));
			int nuovoStatoOrig = Tools.getStateNumber(ttr.getNewState(lineOrig));
			int nuovoStatoSimulante = Tools.getStateNumber(testRed.getMdt().getNewState(lineSimulator));
			assertTrue(nuovoStatoSimulante == nuovoStatoOrig + diff);
			}
			if (ttr.whereDefined(i, '1')>0){
				int lineOrig = ttr.whereDefined(i, '1');
				int lineSimulator = testRed.getMdt().whereDefined(diff+i,'1');
				assertTrue("the original and new MdTs must be defined on the corresponding state,symbol pairs (symbol 1)",lineSimulator>0);
				assertTrue("the original and new MdTs must be defined to write the same new symbol on the corresponding state,symbol pairs (symbol 1)",(ttr.getNewSymbol(lineOrig) == (testRed.getMdt().getNewSymbol(lineSimulator))));
				assertTrue("the original and new MdTs must be defined to move the head the same way on the corresponding state,symbol pairs (symbol 1)",(ttr.getMove(lineOrig) == (testRed.getMdt().getMove(lineSimulator))));
				int nuovoStatoOrig = Tools.getStateNumber(ttr.getNewState(lineOrig));
				int nuovoStatoSimulante = Tools.getStateNumber(testRed.getMdt().getNewState(lineSimulator));
				assertTrue(nuovoStatoSimulante == nuovoStatoOrig + diff);
				}
		}
		
	}
	
	@Test
	public void test90Reduction(){
		testRed = new Halt2Blank();
		Halt2Blank tr2 = new Halt2Blank();
		tr2.buildSimulatingMdT(testHaltInst);
		String cod = testRed.reductionHalt2Blank(testHaltInst);
		assertTrue("la codifica non e' corretta",tr2.getMdt().toString().equals(TMCodec.decodeMdT(cod).toString()));
	}
	

}
