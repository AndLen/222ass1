package cluedo.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cluedo.cards.Card;
import cluedo.structs.Location;
import cluedo.structs.Player;
import cluedo.structs.Solution;
import cluedo.tiles.CorridorTile;
import cluedo.tiles.DoorTile;
import cluedo.tiles.RoomTile;

/**
 * Responsible for managing and running the game.
 * 
 * @author Andrew
 * 
 */
public class Game {
	private final Board gameBoard;
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
		
		//Make maps.
		players = new HashMap<String,Player>();
		for(Player p: playersList){
			players.put(p.getMyName(), p);
		}
		playerToNextPlayer = setUpMap();

		//DEBUG - shows our mapping of next players.
		//System.out.println(playerToNextPlayer.entrySet());
	}

	public void beginGame() {
		nextPlayer = getOrder();
		// TODO Auto-generated method stub

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
			int roll = (int) (1 + Math.random() * 6);
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

	public boolean isDoorLocation(Location newPosition) {
		return (gameBoard.tileAtLocation(newPosition) instanceof DoorTile);
	}

	/**
	 * @return the gameBoard
	 */
	public Board getGameBoard() {
		return gameBoard;
	}
	
}
