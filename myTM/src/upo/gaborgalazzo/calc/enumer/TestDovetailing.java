package upo.gaborgalazzo.calc.enumer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import it.uniupo.enumLib.Pair;

public class TestDovetailing {

Es0DovetailingIntInt dt;

	
	@Before
	public void setUp() {
		dt = new Es0DovetailingIntInt();
	}

	@Test
	public void test000Create() {
		assertNotNull(dt);
	}
	

	
	@Test (timeout=2000)
	public void test010Dovetailinggen() {
		Pair target = new Pair(6,4);
		 
				boolean found = false;
				while (!found) {
					if (dt.next().equals(target)) {
						found = true;
					}
				}
				assertTrue(found);
		
		}
	
	
	@Test (timeout = 5000)
	public void test015DovetailingX0() {
		Pair target = new Pair(0,13);
		 
				boolean found = false;
				while (!found) {
					if (dt.next().equals(target)) {
						found = true;
					}
				}
				assertTrue(found);

		
	}
	
	
	@Test (timeout = 2000)
	public void test020DovetailingY0() {
		Pair target = new Pair(8,0);
	
				boolean found = false;
				while (!found) {
					if (dt.next().equals(target)) {
						found = true;
					}
				}
				assertTrue(found);

	}
	


}
