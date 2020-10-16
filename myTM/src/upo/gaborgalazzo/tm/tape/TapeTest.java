package upo.gaborgalazzo.tm.tape;

import it.uniupo.mdtLib.*;
import org.junit.Before;
import org.junit.Test;
import upo.gaborgalazzo.tm.std.DefaultEnvironmentStandardTM;
import upo.gaborgalazzo.tm.std.MdT;
import upo.gaborgalazzo.tm.std.Run;

import static it.uniupo.mdtLib.EnvironmentStaticInterface.RUNNING;

public class TapeTest {

    private final String TEST_DELTA_FILE = "test_TM2W";

    private DynamicEnvironment workEnv;

    @Before
    public void setUp(){
        EnvironmentStaticInterface e = DefaultEnvironmentStandardTM.getInstance();
        workEnv = DynamicEnvironment.getInstance(e);
        workEnv.setDeltaFile(TEST_DELTA_FILE);

    }

    @Test
    public void findCharacter(){

        UserSimulatorInteraction ui = UserSimulatorInteraction.getInstance();

        TransitionTable tt = ReadMachine.readTT();
        while (tt == null) {
            ui.customFileConfig();
            tt = ReadMachine.readTT();
        }
        MdTInterface mdt = new MdT2W(tt);

        mdt.initializeConfig(State.getState("q0"),"       *","0");

        while (RUNNING == mdt.step()){
            System.out.println(mdt);
        }

    }
}
