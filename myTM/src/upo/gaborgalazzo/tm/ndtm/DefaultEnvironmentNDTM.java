/****************************************
 * Default environment parameters for
 * a single tape/single track non 
 * deterministic TM.
 * 
 * The default environment is a singleton
 * class. It is created at the beginning of
 * the execution and used to initialize
 * the dynamic environment (the latter can
 * be customized).
 * 
 * @author Lavinia Egidi
 * @date Jan 2015
 *******************************************/
package upo.gaborgalazzo.tm.ndtm;

import it.uniupo.mdtLib.EnvironmentStaticInterface;
import it.uniupo.mdtLib.State;
import it.uniupo.mdtLib.Symbol;

public class DefaultEnvironmentNDTM implements EnvironmentStaticInterface  {

	//***************these constants cannot be modified
	//MODEL
		private final int MODEL = NDTM;
		private static final int NUMBER_OF_HEADS = 1; //number of heads
		private static final int NUMBER_OF_T = 1; // number of tracks
		private static final boolean NON_DETERMINISTIC = true;
	
	//***************these constants can be customized***********
	//files
		private static final String DELTA_FILE = "./data/delta_ndtm";
		private static final String RUN_LOG_FILE = "./data/runlog_ndtm";
					
	//**************the constants above can be customized************
		
		//attributes:
		
		//path and files
				private final String path = PATH;
				private final String deltaFile = DELTA_FILE;
				private final String runLogFile = RUN_LOG_FILE;
		//blank and initial config
				private  final Symbol blankSymbol; 
				private  final State initialState;
				private  final String initialHeadPos;
		//halt and error states
				private final boolean printErrorState = PRINT_ERROR_STATE;
				private final boolean printHalt = PRINT_HALT;

		//mdt variations
				private final int numberOfHeads = NUMBER_OF_HEADS;
				private final int numberOfT = NUMBER_OF_T;
				private final boolean outBehavior = OUT_BEHAVIOR;
				private final boolean nonDet = NON_DETERMINISTIC;
		
		
	public DefaultEnvironmentNDTM(){
		blankSymbol = Symbol.getSymbol(BLANK);
		initialState = State.getState(INITIAL_STATE_NAME);
		initialHeadPos = setInitialHeadPosition();
	}
	
	@Override
	public int getModel(){
		return MODEL;
	}
	
	@Override
	public boolean isTapeTwoWay(){
		return TWOWAY_TAPE;
	}
	
	private String setInitialHeadPosition(){
		return new String(Integer.toString(INITIAL_HEAD_POS));	
	}
	
	@Override
	public String getPath() {
		return path;
	}

	@Override
	public String getDeltaFile() {
		return deltaFile;
	}


	@Override
	public String getRunLogFile() {
		return runLogFile;
	}

	@Override
	public char getBlankChar() {
		return BLANK;
	}

	@Override
	public Symbol getBlankSymbol() {
		return blankSymbol;
	}


	@Override
	public State getInitialState() {
		return initialState;
	}


	@Override
	public String getInitialHeadPos() {
		return initialHeadPos;
	}

	@Override
	public boolean isStdOutputRequired() {
		return outBehavior;
	}


	@Override
	public boolean isPrintingErrorStateRequired() {
		return printErrorState;
	}


	@Override
	public boolean isPrintingHaltRequired() {
		return printHalt;
	}

	@Override
	public int getNumberOfTapes(){
		return numberOfHeads;
	}

	@Override
	public int getNumberOfTracks(){
		return numberOfT;
	}
	
	@Override
	public String getHeadPosSep(){ //not meaningful for a single tape MdT
		return null;
	}
	
	@Override
	public boolean isNonDet() {
		return nonDet;
	}
	
	
	
}
