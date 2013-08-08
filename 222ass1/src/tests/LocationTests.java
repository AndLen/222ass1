package tests;

import static org.junit.Assert.fail;

import org.junit.Test;

import cluedo.structs.Location;

public class LocationTests {

	
	
	/**
	 * test if to identical locations hash to the same thing
	 */
	@Test
	public void testLocationCompareTo(){
		
		Location l1 = new Location(1, 2);
		Location l2 = new Location(1, 2);
		
		if(l1.compareTo(l2) != 0){
			fail("l1 isnot equal to the int 3");
		}
		
	}

	/**
	 * test if to identical locations hash to the same thing
	 */
	@Test
	public void testLocationEquals(){
		
		Location l1 = new Location(1, 2);
		Location l2 = null;
		
		if(l1.equals(l2)){
			fail("l1 isnot equal to null");
		}
		
	}
	
	/**
	 * test if to identical locations hash to the same thing
	 */
	@Test
	public void testLocationEquals2(){
		
		Location l1 = new Location(1, 2);
		
		
		if(l1.equals(3)){
			fail("l1 isnot equal to the int 3");
		}
		
	}	
	
	/**
	 * test if to identical locations hash to the same thing
	 */
	@Test
	public void testLocationHash(){
		
		Location l1 = new Location(1, 2);
		Location l2 = new Location(1, 2);
		
		if(l1.hashCode() != l2.hashCode()){
			fail("hashcodes not equal");
		}
		
	}	
	
}
