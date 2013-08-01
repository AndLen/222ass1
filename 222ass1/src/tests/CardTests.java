package tests;

import static org.junit.Assert.fail;

import org.junit.Test;

import cluedo.cards.Card;
import cluedo.cards.Character;
import cluedo.cards.CharacterI;
import cluedo.cards.Room;
import cluedo.cards.RoomI;
import cluedo.cards.Weapon;
import cluedo.cards.WeaponI;

/**
 * 
 * @author Michael
 * 
 */
public class CardTests {

	@Test
	public void testGetSymbol() {

		Card card = new Character(CharacterI.CHARACTERS[1]);

		if (!card.getSymbol().equals("Jm")) {
			fail("Jack Mustard Symbol should be: Jm was: " + card.getSymbol());
		}

		card = new Room(RoomI.ROOMS[1]);

		if (!card.getSymbol().equals("TH")) {
			fail("Theatre Symbol should be: TH was: " + card.getSymbol());
		}

		card = new Weapon(WeaponI.WEAPONS[1]);
		if (!card.getSymbol().equals("cA")) {
			fail("Candlestick Symbol should be: cA was: " + card.getSymbol());
		}

	}
}
