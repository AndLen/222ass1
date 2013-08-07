package tests;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cluedo.cards.Character;
import cluedo.cards.Room;
import cluedo.cards.Weapon;
import cluedo.main.Board;
import cluedo.main.Game;
import cluedo.moves.Enter;
import cluedo.moves.Move;
import cluedo.structs.Location;
import cluedo.structs.Player;
import cluedo.structs.Solution;

public class MoveTests {

	@Test
	/**
	 * test if a player can make multiple turns
	 *  
	 */
	public void testZigZagMove() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(7, 4), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(),
				new Location(18, 25), 32, game);

		if (!move.isValid(game)) {

			fail("a Player can make zig zags");

		}

	}

	@Test
	/**
	 * test if a player is being landied on originating in the corridor and ending in the corridor
	 */
	public void testLandOnPlayer() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(9, 8), Character.CHARACTERS[0],
				null, null));

		players.add(new Player(new Location(8, 8), Character.CHARACTERS[1],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(8, 8),
				1, game);

		if (move.isValid(game)) {
			// move is invalid
			fail("a Player cannot move onto another player");

		}

	}

	@Test
	/**
	 * test if a player can enter a room
	 */
	public void testEnterRoom() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(6, 20), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(2, 27),
				2, game);

		// try {
		if (!move.isValid(game)) {
			// move is invalid
			fail("a Player can enter room");

		}
		// } catch (CluedoException e) {
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
		players.add(new Player(new Location(5, 20), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

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
		players.add(new Player(new Location(8, 18), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

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
		players.add(new Player(new Location(8, 18), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

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
		players.add(new Player(new Location(13, 7), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(16, 5),
				6, game);

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
		players.add(new Player(new Location(13, 6), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(20, 7),
				8, game);

		if (move.isValid(game)) {
			// move is invalid
			fail("a Player cannot walk through walls");

		}

	}

	@Test
	/**
	 * test if a player can use secret passage
	 * Spa to guest house
	 */
	public void testSecretPassage() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(3, 6), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(),
				new Location(22, 25), 8, game);

		if (!move.isValid(game)) {
			// move is invalid
			fail("a Player can move between spa and guest house");

		}

	}

	@Test
	/**
	 * test if a player can use secret passage
	 * guest house to spa
	 */
	public void testSecretPassage2() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(21, 28), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(0, 0),
				1, game);

		if (!move.isValid(game)) {
			// move is invalid
			fail("a Player can move between guest house and spa");

		}

	}

	@Test
	/**
	 * test if a player can use secret passage
	 * kitchen and observatory
	 */
	public void testSecretPassage3() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(2, 23), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(23, 1),
				1, game);

		if (!move.isValid(game)) {
			// move is invalid
			fail("a Player can move between kitchen and observatory");

		}

	}

	@Test
	/**
	 * test if a player can use secret passage
	 * observatory and kitchen
	 */
	public void testSecretPassage4() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(23, 2), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(1, 25),
				1, game);

		if (!move.isValid(game)) {
			// move is invalid
			fail("a Player can move between observatory and kitchen");

		}

	}

	@Test
	/**
	 * test if a player cannot use a secret passage that doesnt exist
	 *  patio to spa
	 */
	public void testNonExistentSecretPassage() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(4, 14), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(1, 6),
				1, game);

		if (move.isValid(game)) {

			fail("a Player cannot move between patio and spa");

		}

	}

	@Test
	/**
	 * test if a player cannot use a secret passage that doesnt exist
	 *  guest house to kitchen
	 */
	public void testNonExistentSecretPassage2() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(20, 27), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(1, 21),
				1, game);

		if (move.isValid(game)) {

			fail("a Player cannot move between guest house and kitchen");

		}

	}

	@Test
	/**
	 * test if a player cannot exit a room into a corridor tile to far away,
	 *  when not high enough dice roll
	 */
	public void testInvalidExitRoom() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(4, 27), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(6, 19),
				1, game);

		if (move.isValid(game)) {

			fail("a Player can exit a room");

		}

	}

	@Test
	/**
	 * test if a player cannot move within the room
	 *  
	 */
	public void testInvalidMoveWithinRoom() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(4, 27), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(4, 26),
				1, game);

		if (move.isValid(game)) {

			fail("a Player can exit a room");

		}

	}

	@Test
	/**
	 * test if a player can exit a room into a corridor
	 *  
	 */
	public void testExitRoom() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(4, 27), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(6, 20),
				3, game);

		if (!move.isValid(game)) {

			fail("a Player can exit a room");

		}

	}

	@Test
	/**
	 * test if a player can exit a room into a corridor
	 *  dianew white got stuck on 5,17 and couldnt exit
	 */
	public void testExitRoom2() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(5, 17), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(5, 18),
				1, game);

		if (!move.isValid(game)) {

			fail("a Player can exit a room");

		}

	}

	@Test
	/**
	 * test if a player can enter a room Kitchen and use the passage in one move
	 */
	public void testInvalidPassage() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(7, 22), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(22, 1),
				6, game);

		if (move.isValid(game)) {

			fail("a Player cannot enter a rrom then use a passage");

		}
	}

	@Test
	/**
	 * test if can not use a door, to enter living room
	 */
	public void testInvalidEnter() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(21, 7), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(17, 7),
				1, game);

		if (move.isValid(game)) {

			fail("a Player cannot enter the room (havent rolled engouh)");

		}

	}

	@Test
	/**
	 * test if can not use a door, to enter living room
	 */
	public void testInvalidEnter2() {

		List<Player> players = new ArrayList<Player>();
		players.add(new Player(new Location(13, 7), Character.CHARACTERS[0],
				null, null));

		Game game = new Game(new Board(), new Solution(new Character(
				Character.CHARACTERS[1]), new Room(Room.ROOMS[1]), new Weapon(
				Weapon.WEAPONS[1])), null, players);

		Move move = new Move(players.get(0).getLocation(), new Location(16, 7),
				1, game);

		if (move.isValid(game)) {

			fail("a Player cannot enter the room (havent rolled engouh)");

		}

	}

}
