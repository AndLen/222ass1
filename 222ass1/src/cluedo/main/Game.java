package cluedo.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import cluedo.cards.Card;
import cluedo.moves.CluedoException;
import cluedo.moves.Move;
import cluedo.structs.Dice;
import cluedo.structs.Location;
import cluedo.structs.Player;
import cluedo.structs.Solution;
import cluedo.tiles.CorridorTile;
import cluedo.tiles.DoorTile;
import cluedo.tiles.RoomTile;
import cluedo.tiles.Tile;

/**
 * Responsible for managing and running the game.
 * 
 * @author Andrew
 * 
 */
public class Game {
	private final Board gameBoard;
	private boolean gameFinished = false;
	private final Dice die = new Dice();
	private final Solution solution;
	private final List<Card> cardsInPool;
	// Our human players.
	private final Map<String, Player> players;

	private Player nextPlayer;

	// Used to find who the next person clockwise to go is!
	private final Map<Player, Player> playerToNextPlayer;

	public Game(Board b, Solution s, List<Card> cardsLeft,
			List<Player> playersList) {
		gameBoard = b;
		solution = s;
		// All our leftover cards get put in the middle.
		cardsInPool = cardsLeft;

		// Make maps.
		players = new HashMap<String, Player>();
		for (Player p : playersList) {
			players.put(p.getMyName(), p);
		}
		playerToNextPlayer = setUpMap();

		// DEBUG - shows our mapping of next players.
		// System.out.println(playerToNextPlayer.entrySet());
	}

	public void runGame() {
		nextPlayer = getOrder();
		while (true) {
			boolean alive = takeTurn(nextPlayer);

			// Did anyone win?
			if (gameFinished) {
				break;
			}

			nextPlayer = playerToNextPlayer.get(nextPlayer);
			if (!alive) {
				players.remove(nextPlayer.getMyName());
				// And re-intialise the map
				setUpMap();
			}
		}
		System.out.println("Congrats to " + nextPlayer
				+ "! You have won the game.");
	}

	/**
	 * Returns true if the player is still alive <_<
	 * 
	 * @param p
	 * @return
	 */
	private boolean takeTurn(Player p) {
		int roll = die.getRoll();
		System.out
				.println("It is " + p + "'s turn!\n" + p + " rolls a " + roll);

		Move m;
		boolean validMove = false;
		// Okay, let's get the player to make a move that's valid.
		do {
			Location newLocation = getLocationInput();
			// TODO: Make this auto-select the right move! (probably in the move
			// constructor).
			m = new Move(p.getLocation(), newLocation, roll, this);
			try {
				validMove = m.isValid(this);
			} catch (CluedoException e) {
				System.out.println("Invalid move: " + e.getMessage());
				continue;
			}
			if (!validMove) {
				System.out.println("Invalid move.");
			}
		} while (!validMove);
		// Cool, we got a valid move.
		m.apply(this);
		System.out.print(gameBoard.toString(this));

		// Print what cards you have
		Iterator<Card> cardIt = p.myCardsIterator();
		System.out.print("Your cards: ");
		while (cardIt.hasNext()) {
			System.out.print(cardIt.next().getCard() + ", ");
		}
		System.out.println();

		// If we're in the pool, we can make an accusation, otherwise we are
		// making an announcement
		boolean canAccuse = inParticularRoomLocation(p.getLocation(), "Po");
		boolean wantsToSpeak = getAnnounceInput(canAccuse);
		
		//TODO: Add viewing extra cards if in pool.
		if (wantsToSpeak) {

			if (canAccuse) {
				Solution s = getAccusationInput();
				if (s.equals(solution)) {
					gameFinished = true;
					return true;
				} else {
					System.out.println("Wrong, you're dead");
					return false;
				}
			} else {
				// TODO: Wants to announce
				System.out.println("Well tough shit");
				// Otherwise just an announcement.
			}
		}
		// We didn't die
		return true;
	}

	private Solution getAccusationInput() {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean getAnnounceInput(boolean inPool) {
		System.out.printf(
				"Would you like to make an %s? Type \"yes\" or \"no\"\n",
				inPool ? "accusation" : "announcement");
		Scanner sc = new Scanner(System.in);
		String data = sc.nextLine();
		data = data.toLowerCase(); // If they decided caps would be fun
		if (data.contains("yes")) {
			return true;
		} else if (data.contains("no")) {
			return false;
		} else {
			System.out.println("Not a valid choice");
			return getAnnounceInput(inPool);
		}
	}

	/**
	 * Requests input for the player to move to on the board and checks their
	 * validity.
	 * 
	 * @return
	 */
	private Location getLocationInput() {
		System.out
				.println("Please enter the x,y co-ordinates to move to in the form x,y:");
		try {
			Scanner sc = new Scanner(System.in);
			String data = sc.nextLine();
			// Split by the comma
			String[] numbers = data.split(",");
			if (numbers.length == 2) {
				// Trim them in case of whitespace.
				int x = Integer.parseInt(numbers[0].trim());
				int y = Integer.parseInt(numbers[1].trim());
				Location newLoc = new Location(x, y);
				if (Location.isValid(newLoc)) {
					return newLoc;
				}
			}
			System.out.println("Invalid Co-ordinates, try again.\n");
			return getLocationInput();
		} catch (NumberFormatException e) {
			System.out.println("Invalid Co-ordinates, try again.\n");
			return getLocationInput();
		}
	}

	/*
	 * Next three methods are used to initialising the map of player to next
	 * player - surprisingly hard to code!
	 */
	private Map<Player, Player> setUpMap() {
		Map<Player, Player> map = new HashMap<Player, Player>();
		for (Player p : players.values()) {
			Player next = nextPlayerSetup(p);
			map.put(p, next);
		}
		return map;
	}

	private Player nextPlayerSetup(Player kas) {
		Player next = null;
		String nextString = kas.getMyName();
		do {
			nextString = nextPlayerString(nextString);

		} while (players.get(nextString) == null);
		next = players.get(nextString);
		return next;
	}

	private String nextPlayerString(String charName) {
		if (charName.equals("Kasandra Scarlett")) {
			return "Jack Mustard";
		} else if (charName.equals("Jack Mustard")) {
			return "Diane White";
		} else if (charName.equals("Diane White")) {
			return "Jacob Green";
		} else if (charName.equals("Jacob Green")) {
			return "Eleanor Peacock";
		} else if (charName.equals("Eleanor Peacock")) {
			return "Victor Plum";
		} else if (charName.equals("Victor Plum")) {
			return "Kasandra Scarlett";
		}
		throw new IllegalArgumentException("Invalid Char name");
	}

	/*
	 * End map intialisation code.
	 */

	/**
	 * Constructs the order in which players take turns.
	 * 
	 * @return TODO: Implement map return.
	 */
	private Player getOrder() {
		int highestRoll = 0;
		Player highestRollPlayer = null;
		// Find who rolled the highest number
		for (Player p : players.values()) {
			int roll = die.getRoll();
			System.out.println(p.getMyName() + " rolled a " + roll + "!");
			if (roll > highestRoll) {
				highestRoll = roll;
				highestRollPlayer = p;
			}
		}
		System.out.println(highestRollPlayer.getMyName() + " goes first.");
		return highestRollPlayer;
	}

	/**
	 * Gives an ASCII representation of the board. Printing this out will print
	 * the board (with newlines and all)
	 * 
	 * @return
	 */
	public String boardToString() {
		return gameBoard.toString(this);
	}

	/**
	 * Changes the location of the player at the old location to the new
	 * location Assumes a valid final location
	 * 
	 * @param oldL
	 * @param newL
	 */
	public void changePlayerLocation(Location oldL, Location newL) {
		Player p = findPlayerAt(oldL);
		p.updateLocation(newL);
	}

	/**
	 * 
	 * @param oldL
	 * @return
	 */
	private Player findPlayerAt(Location oldL) {
		for (Player p : players.values()) {
			if (p.getLocation().equals(oldL)) {
				return p;
			}
		}
		throw new IllegalArgumentException("Illegal move");
	}

	/**
	 * Gives an UNMODIFIABLE view of the list -- this maintains encapsulation
	 * while allowing easy examination of the players in the game.
	 * 
	 * @return
	 */
	public final List<Player> getPlayers() {
		return Collections.unmodifiableList(new ArrayList<Player>(players
				.values()));
	}

	public Set<Location> getMovesTo(Location oldPosition, int diceRoll) {
		return gameBoard.getMovesTo(oldPosition, diceRoll);
	}

	public boolean isCorridorLocation(Location newPosition) {
		return (gameBoard.tileAtLocation(newPosition) instanceof CorridorTile);
	}

	public boolean isRoomLocation(Location newPosition) {
		return (gameBoard.tileAtLocation(newPosition) instanceof RoomTile);
	}

	/**
	 * Checks if a location is a particular room (not just ANY room)
	 * 
	 * @param newPosition
	 * @param roomAcronym
	 *            e.g. "PO" for pool etc.
	 * @return
	 */
	public boolean inParticularRoomLocation(Location newPosition,
			String roomAcronym) {
		Tile r = gameBoard.tileAtLocation(newPosition);
		return (r instanceof RoomTile && r.toString().equals(roomAcronym));
	}

	public boolean isDoorLocation(Location newPosition) {
		return (gameBoard.tileAtLocation(newPosition) instanceof DoorTile);
	}
	
	/**
	 * Doors in our game.
	 * @return
	 */
	public List<DoorTile> getDoors(){
		return gameBoard.getListOfDoors();
	}

}
