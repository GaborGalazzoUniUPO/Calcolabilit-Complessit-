/*******************************
 * This class manages the execution of a TM,
 * distinguishing a complete run, from a step by step
 * run. It keeps track of the fact that the machine is
 * still running or has halted, and it counts the steps
 * taken.
 * It is independent of the kind of TM that is being executed.
 * It could be a library class.
 * 
 * @author Lavinia Egidi
 * @date Jan 2015
 **********************************/
package upo.gaborgalazzo.tm.ndtm;

import it.uniupo.mdtLib.EnvironmentStaticInterface;

import static it.uniupo.mdtLib.EnvironmentStaticInterface.RUNNING;
import static it.uniupo.mdtLib.EnvironmentStaticInterface.NOT_RUNNING;

import it.uniupo.mdtLib.ConfigOutput;
import it.uniupo.mdtLib.MdTInterface;
import it.uniupo.mdtLib.RunInterface;

public class Run implements RunInterface {

	private char runType;
	private char stateOfRun;
	private MdTInterface mdt;
	private int numberOfSteps;

	public Run(MdTInterface tm) {
		runType = EnvironmentStaticInterface.DEFAULT_RUNTYPE;
		mdt = tm;
		numberOfSteps = 0;
		stateOfRun = EnvironmentStaticInterface.NOT_RUNNING;
	}

	@Override
	public void setRunType(char c) {
		runType = c;
	}
	
	@Override
	public void stopExecution(){
		stateOfRun = NOT_RUNNING;
	}

	@Override
	public char getRunType() {
		return runType;
	}
	
	@Override
	public char getStateOfRun() {
		return stateOfRun;
	}
	
	@Override
	public int getNumberOfSteps(){
		return numberOfSteps;
	}
	
	public MdTInterface getMdT(){
		return mdt;
	}	
	
	@Override
	public String getMdTConfig(){
		return mdt.toString();
	}

	@Override
	public void exec() {
		stateOfRun = RUNNING;
		ConfigOutput.writeStdOut(this);
		ConfigOutput.writeLog(this);
		if (runType == 'r') {
			while (stateOfRun == RUNNING) {
				stateOfRun = mdt.step();
				numberOfSteps++;
				ConfigOutput.writeStdOut(this);
				ConfigOutput.writeLog(this);
			}
			stateOfRun = NOT_RUNNING;			
			} else {
			stateOfRun = mdt.step();
			numberOfSteps++;
			ConfigOutput.writeStdOut(this);
			ConfigOutput.writeLog(this);
			if(stateOfRun != RUNNING) {
				stateOfRun = NOT_RUNNING;
			}
		}
	}

}
