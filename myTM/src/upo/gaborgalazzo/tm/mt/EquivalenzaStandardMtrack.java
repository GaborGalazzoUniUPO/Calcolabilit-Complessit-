package upo.gaborgalazzo.tm.mt;


import it.uniupo.utilityLib.*;

import java.util.Arrays;

public class EquivalenzaStandardMtrack {


    public static TTRepMultitrack oneTrack2Multitrack(TTRepStandard oneTrackDelta, int numberOfTracks) {

        /*
         * simulazione di una MdT con nastro ad una sola traccia, con MdT che usa nastro multitraccia
         */
        TTRepMultitrack multiTrackDelta = TTRep.getInstanceMultitrack(numberOfTracks);
        for (int j = 0; j < oneTrackDelta.getNumberOfLines(); ++j) {

            multiTrackDelta.addLine(
                    oneTrackDelta.getCurrentState(j),
                    createCharacterArray(oneTrackDelta.getCurrentSymbol(j), numberOfTracks),
                    oneTrackDelta.getNewState(j),
                    createCharacterArray(oneTrackDelta.getNewSymbol(j), numberOfTracks),
                    oneTrackDelta.getMove(j)
            );

        }
        return multiTrackDelta;


    }

    private static char[] createCharacterArray(char c, int length) {
        char[] arr = new char[length];
        Arrays.fill(arr, Environment.BLANK_CHAR);
        arr[0] = c;
        return arr;
    }


    public static TTRepStandard multitrack2OneTrack(TTRepMultitrack multiTrackDelta, char[] inputAlphabet, char[] workAlphabet) {

        /*
         * simulazione di una MdT che usa nastro multitraccia, con una MdT standard
         * (ricordate che l’alfabeto di input non contiene il blank)
         *
         */
        TTRepStandard oneTrackDelta = TTRep.getInstance();
        int numberOfTracks = multiTrackDelta.getNumberOfTracks();

        CartesianProduct cp = new CartesianProduct(workAlphabet, numberOfTracks, Environment.BLANK_CHAR);

        System.out.println(cp.toString());


        oneTrackDelta.addLine("q0", Environment.BLANK_CHAR, "q_replace", Environment.BLANK_CHAR, 'R'); //inizializzazione del nastro

        for (int j = 0; j < multiTrackDelta.getNumberOfLines(); ++j) {
			oneTrackDelta.addLine(
					multiTrackDelta.getCurrentState(j),
					cp.getChar(multiTrackDelta.getCurrentSymbols(j)),
					multiTrackDelta.getNewState(j),
					cp.getChar(multiTrackDelta.getNewSymbols(j)),
					multiTrackDelta.getMove(j)
			);
		}

		for(char c: inputAlphabet)
			oneTrackDelta.addLine(
					"q_replace",
					c,
					"q_replace",
					c,
					'R'
			);

		oneTrackDelta.addLine(
				"q_replace",
				' ',
				"q_s_replace",
				' ',
				'L'
		);

        for(char c: inputAlphabet)
        	oneTrackDelta.addLine(
        			"q_s_replace",
					c,
					"q_s_replace",
					cp.getChar(createCharacterArray(c, numberOfTracks)),
					'L'
				);

		oneTrackDelta.addLine(
				"q_s_replace",
				' ',
				"q_s_replace",
				cp.getChar(createCharacterArray(Environment.BLANK_CHAR,numberOfTracks)),
				'L'
		);


        return oneTrackDelta;
    }


}

