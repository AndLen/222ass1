package tests;

import static org.junit.Assert.fail;

import org.junit.Test;

import cluedo.cards.Card;
import cluedo.cards.Character;
import cluedo.cards.Room;
import cluedo.cards.Weapon;

/**
 * 
 * @author Andrew & Michael
 * 
 */
public class CardTests {

	/**
	 * tests correct symbols are returned
	 */
	@Test
	public void testGetSymbol() {

		Card card = new Character(Character.CHARACTERS[1]);

		if (!card.getSymbol().equals("Jm")) {
			fail("Jack Mustard Symbol should be: Jm was: " + card.getSymbol());
		}

	}

	/**
	 * tests correct symbols are retruned
	 */
	@Test
	public void testGetSymbol2() {

		Card card = new Room(Room.ROOMS[1]);

		if (!card.getSymbol().equals("TH")) {
			fail("Theatre Symbol should be: TH was: " + card.getSymbol());
		}

	}

	/**
	 * tests correct symbols are retruned
	 */
	@Test
	public void testGetSymbol3() {

		Card card = new Weapon(Weapon.WEAPONS[1]);
		if (!card.getSymbol().equals("cA")) {
			fail("Candlestick Symbol should be: cA was: " + card.getSymbol());
		}

	}

	/**
	 * tests weapon equals method
	 */
	@Test
	public void testWeaponEquals() {

		Card card = new Weapon(Weapon.WEAPONS[1]);
		Card card2 = new Weapon(Weapon.WEAPONS[1]);

		if (!card.equals(card2)) {
			fail("Candlestick cards should be equal");
		}

	}

	/**
	 * tests weapon equals method
	 */
	@Test
	public void testWeaponNotEquals() {

		Card card = new Weapon(Weapon.WEAPONS[1]);
		Card card2 = new Weapon(Weapon.WEAPONS[0]);

		if (card.equals(card2)) {
			fail("the weapons are not equal");
		}

	}

	/**
	 * tests weapon equals method
	 */
	@Test
	public void testWeaponNotEquals2(){
		
		Card card = new Weapon(Weapon.WEAPONS[1]);
		
		if (card.equals(null)) {
			fail("a weapon is not equal to null");
		}
		
		
	}
	
	/**
	 * tests weapon equals method
	 */
	@Test
	public void testWeaponNotEquals3(){
		
		Card card = new Weapon(Weapon.WEAPONS[1]);
		
		if (card.equals(3)) {
			fail("a weapon is not equal to 3");
		}
		
		
	}
	
	/**
	 * tests weapon equals method
	 */
	@Test
	public void testWeaponEquals2(){
		
		Card card = new Weapon(Weapon.WEAPONS[1]);
		
		if (!card.equals(card)) {
			fail("a weapon is equal to iteself");
		}
		
		
	}
	
}
