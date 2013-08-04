package tests;

import static org.junit.Assert.fail;

import org.junit.Test;

import cluedo.cards.Card;
import cluedo.cards.Character;
import cluedo.cards.Room;
import cluedo.cards.Weapon;

/**
 * 
 * @author Michael
 * 
 */
public class CardTests {

	@Test
	public void testGetSymbol() {

		Card card = new Character(Character.CHARACTERS[1]);

		if (!card.getSymbol().equals("Jm")) {
			fail("Jack Mustard Symbol should be: Jm was: " + card.getSymbol());
		}

		card = new Room(Room.ROOMS[1]);

		if (!card.getSymbol().equals("TH")) {
			fail("Theatre Symbol should be: TH was: " + card.getSymbol());
		}

		card = new Weapon(Weapon.WEAPONS[1]);
		if (!card.getSymbol().equals("cA")) {
			fail("Candlestick Symbol should be: cA was: " + card.getSymbol());
		}

	}
}
