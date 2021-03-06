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

import cluedo.cards.AfterRollCard;
import cluedo.cards.AfterTurnCard;
import cluedo.cards.Card;
import cluedo.cards.Character;
import cluedo.cards.Clocks;
import cluedo.cards.Keepers;
import cluedo.cards.Room;
import cluedo.cards.Weapon;
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
 * Responsible for managing and running the game, including the turn logic and
 * maintaining the needed data structures.
 * 
 * @author Andrew & Michael
 * 
 */
public class Game {
	private final Board gameBoard;
	private boolean gameFinished = false;
	private final Solution solution;
	private final List<Card> cardsInPool;
	// Our human players.
	private final Map<String, Player> players;

	private Player nextPlayer;

	// Used to find who the next person clockwise to go is!
	private Map<Player, Player> playerToNextPlayer;
	// and anticlockwise
	private Map<Player, Player> playerToPreviousPlayer = new HashMap<Player, Player>();

	private int clockCounter; // when this reaches 8 player is dead
	private final Queue<Keepers> intrigueCards;

	/**
	 * Each of the parameters should be relevant to this instance of the game.
	 * 
	 * @param b
	 * @param s
	 * @param cardsLeft
	 * @param intrigueCards
	 *            can be null if not included.
	 * @param playersList
	 */
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
			clockCounter = 0;
		}

		// Make maps.
		players = new HashMap<String, Player>();
		for (Player p : playersList) {
			players.put(p.getMyName(), p);
		}
		setUpMap();
	}

	/**
	 * To actually start the game - seperated from the constructor for easier
	 * management.
	 */
	public void runGame() {
		nextPlayer = getOrder();
		while (true) {
			String alive = takeTurn(nextPlayer);

			// Did anyone win?
			if (gameFinished) {
				break;
			}

			String oldPlayer = nextPlayer.getMyName();
			nextPlayer = playerToNextPlayer.get(nextPlayer);
			if (alive != null) {
				// Remove whoever just dieds
				killSomeone(oldPlayer);

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
	 * Removes the given player from the game and renews the player order.
	 * 
	 * @param corpse
	 *            player name to remove
	 */
	private void killSomeone(String corpse) {
		// Sanity check..shouldn't happen.
		if (corpse == null) {
			return;
		}
		players.remove(corpse);
		// And re-intialise the map
		setUpMap();
		System.out.println(corpse + " has died!");
		// Redraw so we can see person is dead.
		System.out.println(gameBoard.toString(this));
	}

	/**
	 * Runs a turn for the given player - rolls the dice, gets a move and
	 * processes it etc.
	 * 
	 * @param p
	 * @return player name if they died, null otherwise
	 */
	public String takeTurn(Player p) {
		int roll1 = new Dice().getRoll();
		int roll2 = new Dice().getRoll();
		int roll;
		if (roll2 == 1) {
			// Rolled a '?' so they get an intrigue card.
			System.out.println(p + " rolled a ?, they get an intrigue card.");
			addIntrigueCard(p);
			// only first dice gives any moves.
			roll = roll1;
		} else {
			roll = roll1 + roll2;
		}
		System.out
				.println("It is " + p + "'s turn!\n" + p + " rolls a " + roll);

		int toAdd = checkDiceIntigue(p);
		if (toAdd != 0) {
			roll += toAdd;
			System.out.println("Dice roll is now: " + roll);
		}
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

		// Print what cards you have
		System.out.print("Your cards: ");
		System.out.println(p.myCards());

		if (isIntrigueLocation(p.getLocation())) {
			// return takeIntrigueTurn(p);
			String corpse = addIntrigueCard(p);
			if (corpse != null) {
				return corpse;
			}

		}

		if (isRoomLocation(p.getLocation())) {
			// If we're in a room, then we can do some other stuff...
			String dead = takeRoomTurn(p);
			// if we didn't die in that room, we can use special cards.
			if (dead == null)
				ATIntrigueAvaliable(p);
			else
				return dead;
		} else {
			ATIntrigueAvaliable(p);
		}

		// We didn't die
		return null;
	}

	/**
	 * Check for the card adding 6 to the dice.
	 * 
	 * @param p
	 * @return the amount to add (0 or 6!)
	 */
	private int checkDiceIntigue(Player p) {
		List<Keepers> tempList = new ArrayList<Keepers>();

		for (Keepers k : p.getKeeperCards()) {
			if (k instanceof AfterRollCard) {
				// player has an after roll card so play it
				tempList.add(k);

			}
		}
		if (tempList.size() == 0) {
			// No point going any further
			return 0;
		}
		System.out.println(p.getMyName() + " has " + tempList
				+ " intrigue cards avaliable to play");

		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out
					.println("Would you like to add 6 to your dice roll? \"yes\" or \"no\"");

			String data = sc.nextLine();
			data = data.toLowerCase().trim(); // If they decided caps or
												// whitespace would be fun
			if (data.contains("yes")) {
				// tempList.get(0).apply(this, p);
				p.getKeeperCards().remove(tempList.get(0));
				return 6;
			} else if (data.contains("no")) {
				return 0;
			} else {
				System.out.println("Not a valid choice");
			}

		}
	}

	/**
	 * after turn intrigue - ATIntigue
	 * 
	 * checks if they can make ATI move, prompts and executes if available
	 */
	private void ATIntrigueAvaliable(Player p) {
		List<Keepers> tempList = new ArrayList<Keepers>();

		for (Keepers k : p.getKeeperCards()) {
			if (k instanceof AfterTurnCard) {
				// player has an after turn card so play it
				tempList.add(k);

			}
		}
		if (tempList.size() == 0) {
			// nothing to play.
			return;
		}
		System.out.println(p.getMyName() + " has " + tempList
				+ " intrigue cards avaliable to play");

		Keepers k = null;
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out
					.println("Type \"show card\" , \"move someone back\" , \"extra turn\" or \"no\"\n based on what you want to do from above.");
			String data = sc.nextLine();
			data = data.toLowerCase().trim(); // If they decided caps or extra
												// whitespace would be fun
			if (data.equals("show card")) {
				k = new AfterTurnCard(
						"Play at the end of your turn. The player on your right must show you a card.");
			} else if (data.equals("move someone back")) {
				k = new AfterTurnCard(
						"Play at the end of your turn. Move anyone back to their start space.");
			} else if (data.equals("extra turn")) {
				k = new AfterTurnCard(
						"Play at the end of your turn. Take another turn.");
			} else if (data.equals("no")) {
				// Nothing to do.
				return;
			}
			// Picked a card and has it.
			if (k != null && tempList.contains(k)) {
				break;
			}
			System.out.println("Not a valid choice");

		}
		// Shouldn't ever be null.
		String toKill = k.apply(this, p);
		// Oh man, someone died!
		if (toKill != null) {
			killSomeone(toKill);
		}

	}

	/**
	 * Processes adding an intrigue card to a player's hand.
	 * 
	 * @return name of person who died (if they did)
	 * @author Andrew & Michael
	 */
	private String addIntrigueCard(Player p) {
		// pick up a card off the pile
		Keepers c = intrigueCards.poll();

		if (c instanceof Clocks) {
			// Print Tick or Tock
			System.out.println(c.toString());
			// System.out.println(clockCounter);
			clockCounter++;
			// System.out.println(clockCounter);
			if (clockCounter >= 8) {
				// player dies when the eighth clock card has been picked up
				// it then goes back into the pile to kill more people.
				intrigueCards.offer(c);
				return p.getMyName();
			}
			return null;

		}
		System.out.println("They got: \"" + c.toString() + "\"");
		p.getKeeperCards().add(c);
		return null;
	}

	/**
	 * If a player is in a room, takes a room turn - processing what room
	 * they're in and what they can do (e.g. Pool can accuse, Patio can
	 * suggest.) Returns name of person who died if they did
	 * 
	 * @param p
	 */
	private String takeRoomTurn(Player p) {
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
		boolean wantsToSpeak = wantsToSuggest(canAccuse);
		// If they do want to actually say something do some more stuff,
		// otherwise just return.
		if (wantsToSpeak) {
			Solution possibleSol = getAccusationInput(canAccuse, p);
			if (canAccuse) {
				if (possibleSol.equals(solution)) {
					gameFinished = true;
				} else {
					return p.getMyName();
				}
			} else {

				// Need to move the appropriate player to the room
				Player toMove = players
						.get(possibleSol.getCharSol().toString());
				if (toMove != null) {
					Location newLocation = gameBoard.getFreeTile(players
							.values(), possibleSol.getRoomSol().getSymbol());
					// Found them a spot in the room, move them.
					changePlayerLocation(toMove.getLocation(), newLocation);
					System.out.print(toMove + " was moved to the "
							+ possibleSol.getRoomSol() + " !\n");
				}
				// Print out their suggestion and get someone to refute it!
				System.out.println(p + " suggests " + possibleSol.getCharSol()
						+ " in " + possibleSol.getRoomSol() + " with the "
						+ possibleSol.getWeaponSol());

				if (!refute(p, playerToNextPlayer.get(p), possibleSol)) {
					System.out.println("No one can refute that!");
				}

			}
		}
		return null; // still alive.
	}

	/**
	 * Sees if a player can refute a suggestion that's been made, and makes them
	 * do so if they can.
	 * 
	 * @param originPlayer
	 *            who made the suggestion
	 * @param nextPlayer
	 *            whose turn it is to try to refute
	 * @param sol
	 *            what was suggested
	 * @return if managed to refute
	 */
	private boolean refute(Player originPlayer, Player nextPlayer, Solution sol) {
		if (originPlayer.equals(nextPlayer)) {
			return false;
		}
		Iterator<Card> cards = nextPlayer.myCardsIterator();
		boolean canRefute = false;
		// Can't actually refute unless they have a card in the solution.
		while (cards.hasNext()) {
			if (sol.containsCard(cards.next().toString())) {
				canRefute = true;
				break;
			}
		}
		if (canRefute) {
			// They do have at least 1 card, let them pick which one
			System.out.print(nextPlayer + ": please refute " + originPlayer
					+ "'s accusation\n");
			System.out.print(nextPlayer + ", your cards are:\n");
			System.out.println(nextPlayer.myCards());
			while (true) {
				// get valid input.
				Scanner sc = new Scanner(System.in);
				System.out
						.println("Please enter the card you wish to refute with:");
				String possCard = sc.nextLine();
				possCard = possCard.trim();
				if (sol.containsCard(possCard)) {
					System.out.println(nextPlayer + " refuted with: "
							+ possCard);
					// they refuted, so we're done here
					return true;
				}

			}
		}
		// Okay, this player can't refute so try the next one.
		return refute(originPlayer, playerToNextPlayer.get(nextPlayer), sol);
	}

	/**
	 * Requests input for the player to make an accusation or suggestion and
	 * checks their validity.
	 * 
	 * @inPool true if in pool (accusation) , false if in other room
	 *         (suggestion)
	 * @return their accusation/suggestion
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
	 * Sees if the player wants to make a suggestion/accusation or not.
	 * 
	 * @return
	 */
	private boolean wantsToSuggest(boolean inPool) {
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
			// Try again!
			return wantsToSuggest(inPool);
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
			// Go until we get a valid co-ordinate (you have to move!).
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
	/**
	 * Map setup part 1
	 * 
	 * @return
	 */
	private void setUpMap() {
		playerToNextPlayer = new HashMap<Player, Player>();
		for (Player p : players.values()) {
			// For each player find the player that should be on their left
			// (clockwise)
			Player next = nextPlayerSetup(p);
			playerToNextPlayer.put(p, next);
		}

		// should set up a map so players are reverse order
		for (Entry<Player, Player> e : playerToNextPlayer.entrySet()) {
			playerToPreviousPlayer.put(e.getValue(), e.getKey());
		}
	}

	/**
	 * Map setup part 2
	 * 
	 * @param kas
	 * @return
	 */
	private Player nextPlayerSetup(Player kas) {
		Player next = null;
		String nextString = kas.getMyName();
		do {
			// Get the next player's name while there isn't a person on their
			// left (i.e. find the first person on the left who is actually in
			// the game.)
			nextString = nextPlayerString(nextString);

		} while (players.get(nextString) == null);
		next = players.get(nextString);
		return next;
	}

	/**
	 * Map setup part 3
	 * 
	 * @param charName
	 * @return
	 */
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
	 * Constructs the order in which players take turns by rolling a die. In the
	 * case of a draw, the person who rolled the highest number first gets to go
	 * first.
	 * 
	 */
	private Player getOrder() {
		int highestRoll = 0;
		Player highestRollPlayer = null;
		// Find who rolled the highest number
		for (Player p : players.values()) {
			int roll = new Dice().getRoll();
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
		// Print the board since someone has moved.
		System.out.print(boardToString());
	}

	/**
	 * Returns player at location (duh)
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

	/**
	 * Moves diceRoll tiles away from oldPosition (in a straight/diagonal line)
	 * 
	 * @param oldPosition
	 * @param diceRoll
	 * @return
	 */
	public Set<Location> getMovesTo(Location oldPosition, int diceRoll) {
		return gameBoard.getMovesTo(oldPosition, diceRoll);
	}

	/**
	 * True if location is corridor or intrigue tile
	 * 
	 * @param newPosition
	 * @return
	 */
	public boolean isCorridorLocation(Location newPosition) {
		return (gameBoard.tileAtLocation(newPosition) instanceof CorridorTile);
	}

	/**
	 * Only intrigue (Not corridor)
	 * 
	 * @param newPosition
	 * @return
	 */
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
	 * Gets map of players before previous player (reverse order)
	 * 
	 * @return the playerToPreviousPlayer
	 */
	public Map<Player, Player> getPlayerToPreviousPlayer() {
		return playerToPreviousPlayer;
	}

	/**
	 * Gets a player to show a card to the person on their left.
	 * 
	 * @param player
	 * @return Card to show
	 */
	public Card playerShowCard(Player player) {
		while (true) {
			// Get valid input
			Scanner sc = new Scanner(System.in);

			System.out
					.println(player
							+ "Your cards are: "
							+ player.myCards()
							+ "\nPlease enter the card you wish to show the player on your left:");
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
		}
	}

	/**
	 * Checks if a given player has this card or not.
	 * 
	 * @param player
	 *            to check.
	 * @param card
	 *            true if they do.
	 * @return
	 */
	private boolean playerContainsCard(Player player, Card card) {
		return player.getMyCards().contains(card);
	}

	/**
	 * Get the player from a given name.
	 * 
	 * @param string
	 * @return
	 */
	public Player getPlayer(String string) {
		return players.get(string);
	}

}
