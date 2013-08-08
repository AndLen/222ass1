package tests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

//import com.sun.java.util.jar.pack.*;

import cluedo.cards.Card;
import cluedo.cards.Character;
import cluedo.cards.Clocks;
import cluedo.cards.Room;
import cluedo.cards.Weapon;
import cluedo.main.Board;
import cluedo.main.Game;
import cluedo.moves.Move;
import cluedo.structs.Location;
import cluedo.structs.Player;
import cluedo.structs.Solution;

public class GameTests {

	/**
	 * tests if die on the nth card (8th here)
	 * uses reflection to access private method inside Game
	 */
	@Test
	public void testClocks() {
		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(6, 20), Character.CHARACTERS[0],
				null, null));

		List<Card> intrigueCards = new ArrayList<Card>();
		for (int i = 0; i < 8; i++) {
			intrigueCards.add(new Clocks());
		}

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, intrigueCards, players);

		Class<?> c = game.getClass();
//		for (Method mt : c.getDeclaredMethods()) {
//			System.out.println(mt);
//		}
		try {

			Method m = c
					.getDeclaredMethod(
							"takeIntrigueTurn",
							Player.class);
			m.setAccessible(true);
			
			// move onto an intriguetile 7 times
			// and off again
			for (int i = 0; i < 7; i++) {

				if (!(boolean) m.invoke(game, players.get(0))) {
					fail("Premature death");
				}
			}
			if ((boolean) m.invoke(game, players.get(0))) {
				fail("should be dead");
			}
		} catch (IllegalAccessException e) {
			fail("Illegal access exception");
		} catch (InvocationTargetException e) {
			fail("Invocation Target Exception");
		} catch (NoSuchMethodException e) {
			fail("No such Method exception");
		}

	}
}
