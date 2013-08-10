package cluedo.main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import cluedo.cards.*;
import cluedo.cards.Character;
import cluedo.moves.Move;
import cluedo.structs.Dice;
import cluedo.structs.Location;
import cluedo.structs.Player;
import cluedo.structs.Solution;
import cluedo.tiles.CorridorTile;
import cluedo.tiles.DoorTile;
import cluedo.tiles.IntrigueTile;
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
	// and anticlockwise
	private Map<Player, Player> playerToPrevoiusPlayer = new HashMap<Player, Player>();

	private int clockCounter; // when this reaches 8 player is dead
	private final Queue<Keepers> intrigueCards;

	public Game(Board b, Solution s, List<Card> cardsLeft,
			List<Keepers> intrigueCards, List<Player> playersList) {
		this.intrigueCards = new ArrayDeque<Keepers>();
		gameBoard = b;
		solution = s;
		// All our leftover cards get put in the middle.
		cardsInPool = cardsLeft;

		if (intrigueCards != null) {
			// sometimes we dont care about these cards (alot of the movement
			// tests) so it is null
			this.intrigueCards.addAll(intrigueCards);
			clockCounter = 8 - intrigueCards.size();
		}

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

			String oldPlayer = nextPlayer.getMyName();
			nextPlayer = playerToNextPlayer.get(nextPlayer);
			if (!alive) {
				// Remove whoever just dieds
				players.remove(oldPlayer);
				// And re-intialise the map
				setUpMap();
				System.out.println(oldPlayer + " has died!");
				// Redraw so we can see person is dead.
				System.out.println(gameBoard.toString(this));

			}
			if (players.size() == 1) {
				System.out.print("Only one person left, ");
				for (String s : players.keySet()) {
					System.out.println(s + " has won the game!");
				}
				return;
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
	public boolean takeTurn(Player p) {
		int roll = die.getRoll();
		System.out
				.println("It is " + p + "'s turn!\n" + p + " rolls a " + roll);

		roll += checkDiceIntigue(p);

		Move m;
		boolean validMove = false;
		// Okay, let's get the player to make a move that's valid.
		do {
			Location newLocation = getLocationInput();
			m = new Move(p.getLocation(), newLocation, roll, this);
			validMove = m.isValid(this);
			if (!validMove) {
				System.out.println("Invalid move.");
			}
		} while (!validMove);
		// Cool, we got a valid move.
		m.apply(this);
		System.out.print(gameBoard.toString(this));

		// Print what cards you have
		System.out.print("Your cards: ");
		System.out.println(p.myCards());

		if (isIntrigueLocation(p.getLocation())) {
			// return takeIntrigueTurn(p);

			if (!takeIntrigueTurn(p)) {
				return false;
			} else {
				ATIntrigueAvaliable(p);
				return true;
			}

		}

		if (isRoomLocation(p.getLocation())) {
			// If we're in a room, then we can do some other stuff...
			return takeRoomTurn(p);
		}

		// We didn't die
		return true;
	}

	private int checkDiceIntigue(Player p) {
		// TODO Auto-generated method stub
		List<Keepers> tempList = new ArrayList<Keepers>();

		for (Keepers k : p.getKeeperCards()) {
			if (k instanceof AfterRollCard) {
				// player has an after turn card so play it
				tempList.add(k);

			}
		}

		System.out.println(p.getMyName() + " has " + tempList
				+ " intrigue cards avaliable to play");
		if (tempList.size() != 0) {
			// TODO needs to get input from player on whether to play the card
			// or not
			if (true) {
				return 6;
			}
		}
		return 0;
	}

	/**
	 * after turn intrigue - ATIntigue
	 * 
	 * checks if they can make ATI move, prompts and executes if available
	 */
	private void ATIntrigueAvaliable(Player p) {
		// TODO Auto-generated method stub
		List<Keepers> tempList = new ArrayList<Keepers>();

		for (Keepers k : p.getKeeperCards()) {
			if (k instanceof AfterTurnCard) {
				// player has an after turn card so play it
				tempList.add(k);

			}
		}

		System.out.println(p.getMyName() + " has " + tempList
				+ " intrigue cards avaliable to play");

		// TODO get input from player on which card to play if any
		Keepers k = null; // change null to the card selected
		if (k != null) {
			k.apply(this, p);
		}

	}

	/**
	 * @return false if dead
	 * @author Michael
	 */
	private boolean takeIntrigueTurn(Player p) {
		// TODO Auto-generated method stub
		// pick up a card off the pile
		Keepers c = intrigueCards.poll();

		if (c instanceof Clocks) {
			// Print Tick or Tock
			System.out.println(c.toString());
			// System.out.println(clockCounter);
			clockCounter++;
			// System.out.println(clockCounter);
			if (clockCounter >= 8) {
				// player dies when the eigth clock card has been picked up
				// it then goes back into the pile to kill more people.
				// System.out.println("size: " + intrigueCards.size());
				intrigueCards.add(c);
				return false;
			}
			return true;

		}
		System.out.println(c.toString());
		p.getKeeperCards().add(c);
		return true;
	}

	/**
	 * Returns false if died.
	 * 
	 * @param p
	 */
	private boolean takeRoomTurn(Player p) {
		// If we're in the pool, we can make an accusation, otherwise we are
		// making a suggestion
		boolean canAccuse = inParticularRoomLocation(p.getLocation(), "PO");
		if (canAccuse) {
			System.out.println("Cards in pool: ");
			if (cardsInPool.isEmpty()) {
				System.out.println("No cards in the pool.");
			} else {
				for (Card c : cardsInPool) {
					System.out.print(c + ", ");
				}
				System.out.println();
			}
		}
		boolean wantsToSpeak = getAnnounceInput(canAccuse);

		if (wantsToSpeak) {
			Solution possibleSol = getAccusationInput(canAccuse, p);
			if (canAccuse) {
				if (possibleSol.equals(solution)) {
					gameFinished = true;
				} else {
					return false;
				}
			} else {

				// Need to move the appropriate player to the room
				Player toMove = players
						.get(possibleSol.getCharSol().toString());
				if (toMove != null) {
					Location newLocation = gameBoard.getFreeTile(players
							.values(), possibleSol.getRoomSol().getSymbol());
					// Found them a spot, move them and re-draw.
					changePlayerLocation(toMove.getLocation(), newLocation);
					System.out.print(boardToString());
					System.out.print(toMove + " was moved to the "
							+ possibleSol.getRoomSol() + " !\n");
				}

				System.out.println(p + " suggests " + possibleSol.getCharSol()
						+ " in " + possibleSol.getRoomSol() + " with the "
						+ possibleSol.getWeaponSol());

				if (!refute(p, playerToNextPlayer.get(p), possibleSol)) {
					System.out.println("No one can refute that!");
				}

			}
		}
		return true; // still alive.
	}

	private boolean refute(Player originPlayer, Player nextPlayer, Solution sol) {
		if (originPlayer.equals(nextPlayer)) {
			return false;
		}
		Iterator<Card> cards = nextPlayer.myCardsIterator();
		boolean canRefute = false;
		while (cards.hasNext()) {
			if (sol.containsCard(cards.next().toString())) {
				canRefute = true;
				break;
			}
		}
		if (canRefute) {
			System.out.print(nextPlayer + ": please refute " + originPlayer
					+ "'s accusation\n");
			System.out.print(nextPlayer + ", your cards are:\n");
			System.out.println(nextPlayer.myCards());
			while (true) {
				Scanner sc = new Scanner(System.in);
				System.out
						.println("Please enter the card you wish to refute with:");
				String possCard = sc.nextLine();
				possCard = possCard.trim();
				if (sol.containsCard(possCard)) {
					System.out.println(nextPlayer + " refuted with: "
							+ possCard);
					return true;
				}

			}
		}
		return refute(originPlayer, playerToNextPlayer.get(nextPlayer), sol);
	}

	/**
	 * Requests input for the player to make an accusation and checks their
	 * validity.
	 * 
	 * @return
	 */
	private Solution getAccusationInput(boolean inPool, Player p) {
		System.out
				.println("Type the character name, room name and weapon name seperated by commas.");
		Scanner sc = new Scanner(System.in);
		String data = sc.nextLine();
		String stringCards[] = data.split(",");

		if (stringCards.length == 3) {
			try {
				// Trim them for a little leeway on typing
				Character c = new Character(stringCards[0].trim());
				Room r = new Room(stringCards[1].trim());
				Weapon w = new Weapon(stringCards[2].trim());
				// If we're not in the pool and try to suggest a room we're not
				// in, that's invalid!
				if (!inPool
						&& !inParticularRoomLocation(p.getLocation(),
								r.getSymbol())) {
					throw new IllegalArgumentException(
							"Can't suggest something unless you're in the right room");
				}
				return new Solution(c, r, w);
			} catch (IllegalArgumentException e) {
				System.out.println("Not valid: " + e.getMessage());
			}
		} else {
			System.out.println("Not enough cards.");
		}
		return getAccusationInput(inPool, p);
	}

	/**
	 * Requests input for the player to move back to their start
	 */
	protected Character getCharcterInput() {
		System.out.println("Type the character name to move back to start");
		Scanner sc = new Scanner(System.in);
		String data = sc.nextLine();

		try {
			// Trim them for a little leeway on typing
			Character c = new Character(data.trim());

			return c;
		} catch (IllegalArgumentException e) {
			System.out.println("Not valid: " + e.getMessage());
		} finally {
			sc.close();
		}

		return getCharcterInput();
	}

	/**
	 * Requests input for the player to make an announcement and checks their
	 * validity.
	 * 
	 * @return
	 */
	private boolean getAnnounceInput(boolean inPool) {
		System.out.printf(
				"Would you like to make an %s? Type \"yes\" or \"no\"\n",
				inPool ? "accusation" : "suggestion");
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
	protected Location getLocationInput() {
		System.out
				.println("Please enter the x,y co-ordinates to move to in the form x,y:");
		try {
			Scanner sc = new Scanner(System.in);
			String data = sc.nextLine();
			// Split by the comma
			String numbers[] = data.split(",");
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

		// should set up a map so players are reverse order
		for (Entry<Player, Player> e : map.entrySet()) {
			playerToPrevoiusPlayer.put(e.getValue(), e.getKey());
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

	public boolean isIntrigueLocation(Location newPosition) {
		return (gameBoard.tileAtLocation(newPosition) instanceof IntrigueTile);
	}

	/**
	 * Includes DoorTile as child of RoomTile
	 * 
	 * @param newPosition
	 * @return
	 */
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
		Tile t = gameBoard.tileAtLocation(newPosition);
		if (t instanceof RoomTile) {
			// Okay, it's a room/door. Is it the right one?
			RoomTile rT = (RoomTile) t;
			return (rT.roomRep().equals(roomAcronym));
		}
		// not a room or a door.
		return false;
	}

	/**
	 * 
	 * @param location
	 *            the location for the tile rep
	 * @return the intitals of the room that the room belongs to
	 */
	public String getRoom(Location location) {
		Tile t = gameBoard.tileAtLocation(location);
		if (t instanceof RoomTile) {
			RoomTile rt = (RoomTile) t;
			return rt.roomRep();
		}
		return null;
	}

	/**
	 * Doors in our game.
	 * 
	 * @return
	 */
	public List<DoorTile> getDoors() {
		return gameBoard.getListOfDoors();
	}

	/**
	 * @return the playerToPrevoiusPlayer
	 */
	public Map<Player, Player> getPlayerToPrevoiusPlayer() {
		return playerToPrevoiusPlayer;
	}

	public Card playerShowCard(Player player) {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out
					.println("Please enter the card you wish to show the player on your left:");
			String possCard = sc.nextLine();
			possCard = possCard.trim();

			Card card = null;
			try {
				card = new Weapon(possCard);
				if (playerContainsCard(player, card)) {
					return card;
				}
			} catch (IllegalArgumentException e) {
				// card is not a weapon
				try {
					card = new Character(possCard);

					if (playerContainsCard(player, card)) {
						return card;
					}
				} catch (IllegalArgumentException f) {
					// card is not a character

					try {
						card = new Room(possCard);

						if (playerContainsCard(player, card)) {
							return card;
						}
					} catch (IllegalArgumentException g) {
						// card is not a room
						System.out.println("You do not have that card");
						continue;
					}
				}

			}

			// if(player.getMyCards().contains(new Weapon(possCard))){
			// System.out.println(player + " shouwed you: " + possCard);
			// return possCard;
			// }
		}
	}

	private boolean playerContainsCard(Player player, Card card) {
		return player.getMyCards().contains(card);
	}

}
