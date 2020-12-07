package upo.gaborgalazzo.bubbleSort;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;



import org.junit.jupiter.api.Test;

class TestBubble {

	private Bubble b;

	
	@Test
	public void test () {
		b = new Bubble(2,2);
		
		int[] v = {2,0, 1, 0, 2,0,0,0,1,2,0,0,1};

	
		int[] ordinato= b.accelBubble(v);
		System.out.println(ordinato);
		
		Arrays.sort(v);

		assertTrue(Arrays.equals(ordinato, v));
		
		
	}
	
	
	@Test
	public void test2 () {
		b = new Bubble(4,3);
		
		int[] v = {0,2,3,0,0,3,2,2,0,1,0,2,3};
	
		int[] ordinato = b.accelBubble(v);
		
		Arrays.sort(v);
		assertTrue(Arrays.equals(ordinato, v));

		
	}


	@Test
	public void test3 () {
		b = new Bubble(4,3);

		int[] v = {3,2,1,0,3,2,1,0,3,2,1,0,3,2};

		int[] ordinato = b.accelBubble(v);

		Arrays.sort(v);
		assertTrue(Arrays.equals(ordinato, v));


	}

	@Test
	public void test4 () {
		b = new Bubble(4,3);

		int[] v = {3,3,3,3,2,2,2,2,1,1,1,1,0,0,0,0};

		int[] ordinato = b.accelBubble(v);

		Arrays.sort(v);
		assertTrue(Arrays.equals(ordinato, v));


	}
}
