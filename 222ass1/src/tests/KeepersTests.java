package tests;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cluedo.cards.Card;
import cluedo.cards.Character;
import cluedo.cards.Room;
import cluedo.cards.Weapon;
import cluedo.main.Board;
import cluedo.main.Game;
import cluedo.moves.Move;
import cluedo.structs.Location;
import cluedo.structs.Player;
import cluedo.structs.Solution;

public class KeepersTests {

	/**
	 * tests if a player can use a after turn card to force player on right to
	 * show card
	 */
	@Test
	public void testATIShow() {

		List<Player> players = new ArrayList<Player>();
		
		List<Card> cards = new ArrayList<Card>();
		cards.add(new Weapon("Axe"));
		players.add(new Player(new Location(6, 20), Character.CHARACTERS[0],
				null, cards));
		
		players.add(new Player(new Location(5, 20), Character.CHARACTERS[1],
				null, null));

		players.add(new Player(new Location(5, 20), Character.CHARACTERS[2],
				null, null));
		
		
		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, null, players);

		Card card = game.playerShowCard(players.get(0));

		if (!card.toString().equals("Axe")) {

			fail(Character.CHARACTERS[0] + " should have shown the axe card");

		}

	}
}
