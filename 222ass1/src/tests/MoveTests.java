package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cluedo.cards.Character;
import cluedo.cards.CharacterI;
import cluedo.cards.Room;
import cluedo.cards.RoomI;
import cluedo.cards.Weapon;
import cluedo.cards.WeaponI;
import cluedo.main.Board;
import cluedo.main.Game;
import cluedo.moves.CluedoException;
import cluedo.moves.Enter;
import cluedo.moves.Move;
import cluedo.structs.Location;
import cluedo.structs.Player;
import cluedo.structs.Solution;

public class MoveTests {

	@Test
	/**
	 * test if a player is being landied on originating in the corridor and ending in the corridor
	 */
	public void testLandOnPlayer() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(9, 8), CharacterI.CHARACTERS[0],
				null, null));

		players.add(new Player(new Location(8, 8), CharacterI.CHARACTERS[1],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				CharacterI.CHARACTERS[1]), new Room(RoomI.ROOMS[1]),
				new Weapon(WeaponI.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(8, 8),
				1, game);

		try {
			if (move.isValid(game)) {
				// move is invalid
				fail("a Player cannot move onto another player");

			}
		} catch (CluedoException e) {
			// TODO Auto-generated catch block
			fail("exception thrown");
			e.printStackTrace();
		}
		// TODO move one of the players so that they land on the other

	}

	@Test
	/**
	 * test if a player can enter a room
	 */
	public void testEnterRoom() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(6, 20), CharacterI.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				CharacterI.CHARACTERS[1]), new Room(RoomI.ROOMS[1]),
				new Weapon(WeaponI.WEAPONS[1])), null, players);

		Enter move = new Enter(players.get(0).getLocation(),
				new Location(2, 27), 2, game);

		// try {
		if (!move.isValid(game)) {
			// move is invalid
			fail("a Player can enter room");

		}
		// } catch (CluedoException e) {
		// TODO Auto-generated catch block
		// fail("exception thrown");
		// e.printStackTrace();
		// }
		// TODO move one of the players so that they land on the other

	}

	@Test
	/**
	 * test if a player can enter a room
	 */
	public void testEnterRoom1() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(5, 20), CharacterI.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				CharacterI.CHARACTERS[1]), new Room(RoomI.ROOMS[1]),
				new Weapon(WeaponI.WEAPONS[1])), null, players);

		Enter move = new Enter(players.get(0).getLocation(),
				new Location(3, 28), 3, game);

		if (!move.isValid(game)) {
			// move is invalid
			fail("a Player can enter room");

		}

	}
	
	@Test
	/**
	 * test if a player can enter a room
	 */
	public void testEnterRoom2() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(8, 18), CharacterI.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				CharacterI.CHARACTERS[1]), new Room(RoomI.ROOMS[1]),
				new Weapon(WeaponI.WEAPONS[1])), null, players);

		Enter move = new Enter(players.get(0).getLocation(),
				new Location(3, 14), 3, game);

		if (!move.isValid(game)) {
			// move is invalid
			fail("a Player can enter room");

		}

	}

	@Test
	/**
	 * test if a player can enter a room
	 */
	public void testEnterRoom3() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(8, 18), CharacterI.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				CharacterI.CHARACTERS[1]), new Room(RoomI.ROOMS[1]),
				new Weapon(WeaponI.WEAPONS[1])), null, players);

		Enter move = new Enter(players.get(0).getLocation(),
				new Location(3, 14), 3, game);

		if (!move.isValid(game)) {
			// move is invalid
			fail("a Player can enter room");

		}

	}
	
	@Test
	/**
	 * test if a player can enter a room
	 */
	public void testEnterRoom4() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(13, 7), CharacterI.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				CharacterI.CHARACTERS[1]), new Room(RoomI.ROOMS[1]),
				new Weapon(WeaponI.WEAPONS[1])), null, players);

		Enter move = new Enter(players.get(0).getLocation(),
				new Location(16, 5), 6, game);

		if (!move.isValid(game)) {
			// move is invalid
			fail("a Player can enter room");

		}

	}
	
	@Test
	/**
	 * test if a player cannot walk through walls
	 */
	public void testNotGhost() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(13, 6), CharacterI.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				CharacterI.CHARACTERS[1]), new Room(RoomI.ROOMS[1]),
				new Weapon(WeaponI.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(),
				new Location(20, 7), 8 , game);

		try {
			if (move.isValid(game)) {
				// move is invalid
				fail("a Player cannot walk through walls");

			}
		} catch (CluedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
