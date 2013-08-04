package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import cluedo.cards.Character;
import cluedo.cards.Room;
import cluedo.cards.Weapon;
import cluedo.main.Board;
import cluedo.main.Game;
import cluedo.structs.Location;
import cluedo.structs.Player;
import cluedo.structs.Solution;

/**
 * 
 * @author Michael
 * 
 */
public class BoardTests {

	@Test
	/**
	 * checks the list of possible moves is correct
	 */
	public void testCorrectMoves() {
		// TODO will need to change test for walls. AND needs to have the
		// correct answers added

		Board board = new Board();
		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(8, 8), Character.CHARACTERS[1],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Set<Location> movesTo = game.getMovesTo(new Location(8, 8), 4);

	}
}
