package upo.gaborgalazzo.bubbleSort;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TestTabella {

	private TabellaAccelerazione ta;
	private static final int NUMERO_DI_MACROCELLE = 2;
	

	
	@Test
	void test() {
		int emme = 2;
		int max = 2;
		ta = new TabellaAccelerazione(emme, max,NUMERO_DI_MACROCELLE);
		int[] keyV = {2,0,0,0}; 
		NastroCompresso key = new NastroCompresso(keyV,emme, max);
		int[] valueV = {0,0,2,0};
		int[] result = ta.getMacroStep(key).decomprimi();
	    System.out.println(keyV.length+"--->"+ result.length);

//	    System.out.println(stampa(keyV)+"--->"+ stampa(result));

		assertTrue (Arrays.equals(valueV,result));
		
		System.arraycopy(new int[]{0,1,2,2},0,keyV,0,keyV.length);
		key = new NastroCompresso(keyV,emme, max);
		System.arraycopy(new int[]{0,1,2,2},0,valueV,0,valueV.length);	   
        result = ta.getMacroStep(key).decomprimi();
		assertTrue (Arrays.equals(valueV,result));
		
		System.arraycopy(new int[]{2,2,0,0},0,keyV,0,keyV.length);
		key = new NastroCompresso(keyV,emme, max);
		System.arraycopy(new int[]{2,0,2,0},0,valueV,0,valueV.length);	   
	    result = ta.getMacroStep(key).decomprimi();
//	    System.out.println(stampa(keyV)+"--->"+ stampa(result));
		assertTrue (Arrays.equals(valueV,result));		
	}
	
	@Test
	void test2() {
		int emme = 3;
		int max = 3;
		ta = new TabellaAccelerazione(emme, max,NUMERO_DI_MACROCELLE);
		int[] keyV = {3,0,0,0,0, 0}; 
		NastroCompresso key = new NastroCompresso(keyV,emme, max);
		int[] valueV = {0,0,0,3,0,0};
		int[] result = ta.getMacroStep(key).decomprimi();
		assertTrue (Arrays.equals(valueV,result));
		
		System.arraycopy(new int[]{0,1,2,3,2,2},0,keyV,0,keyV.length);
		key = new NastroCompresso(keyV,emme, max);
		System.arraycopy(new int[]{0,1,2,3,2,2},0,valueV,0,valueV.length);	   
        result = ta.getMacroStep(key).decomprimi();
		assertTrue (Arrays.equals(valueV,result));
		
		System.arraycopy(new int[]{2,2,0,0,0,0},0,keyV,0,keyV.length);
		key = new NastroCompresso(keyV,emme, max);
		System.arraycopy(new int[]{2,0,0,2,0,0},0,valueV,0,valueV.length);	   
	    result = ta.getMacroStep(key).decomprimi();
		assertTrue (Arrays.equals(valueV,result));		
	}
	
	@Test
	void test3() {
		int emme = 4;
		int max = 3;
		ta = new TabellaAccelerazione(emme, max,NUMERO_DI_MACROCELLE);
		int[] keyV = {3,0,0,0,0,0,0,0}; 
		NastroCompresso key = new NastroCompresso(keyV,emme, max);
		int[] valueV = {0,0,0,0,3,0,0,0};
		int[] result = ta.getMacroStep(key).decomprimi();
		assertTrue (Arrays.equals(valueV,result));
		
		System.arraycopy(new int[]{0,1,2,3,2,2,3,1},0,keyV,0,keyV.length);
		key = new NastroCompresso(keyV,emme, max);
		System.arraycopy(new int[]{0,1,2,2,3,2,3,1},0,valueV,0,valueV.length);	   
        result = ta.getMacroStep(key).decomprimi();
		assertTrue (Arrays.equals(valueV,result));
		
		System.arraycopy(new int[]{3,3,0,0,0,0,0,0},0,keyV,0,keyV.length);
		key = new NastroCompresso(keyV,emme, max);
		System.arraycopy(new int[]{3,0,0,0,3,0,0,0},0,valueV,0,valueV.length);	   
	    result = ta.getMacroStep(key).decomprimi();
		assertTrue (Arrays.equals(valueV,result));		
		
		System.arraycopy(new int[]{0,1,2,3,3,2,1,0},0,keyV,0,keyV.length);
		key = new NastroCompresso(keyV,emme, max);
		System.arraycopy(new int[]{0,1,2,3,3,2,1,0},0,valueV,0,valueV.length);	   
	    result = ta.getMacroStep(key).decomprimi();
		assertTrue (Arrays.equals(valueV,result));		
	}

}
