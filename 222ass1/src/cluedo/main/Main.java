package cluedo.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import cluedo.cards.Card;
import cluedo.cards.Character;
import cluedo.cards.Room;
import cluedo.cards.Weapon;
import cluedo.structs.Location;
import cluedo.structs.Player;
import cluedo.structs.Solution;

/**
 * Controls the running of the game, including the main data structures and
 * drawing of our board in ASCII representation on the command-line.
 * 
 * @author Andrew
 * 
 */
public class Main {
	private final int numPlayers;
	private final Scanner input;
	private Game game;

	public Main() {
		// Needed as we can't assign a final variable in a while(true)...
		int numPlayers2 = 0;
		input = new Scanner(System.in);
		System.out.println("Welcome to Cluedo!");
		System.out.println("Please enter the number of players:");

		// Ensures we get appropriate input
		while (true) {
			try {
				String stringPlayers = input.nextLine();
				numPlayers2 = Integer.parseInt(stringPlayers);
				// Need 3-6 players
				if (numPlayers2 >= 3 && numPlayers2 <= 6) {
					break;
				} else {
					System.out.println("Need 3-6 players (inclusive).");
				}
			} catch (NumberFormatException e) {
				System.out.println("Not a valid number.");
			}
		}
		numPlayers = numPlayers2;
		System.out.println("Starting a game with " + numPlayers + " players.");

		game = setupGame();
		System.out.print(game.boardToString());
		game.runGame();
		System.out.println("Thanks for playing!");
	}

	/**
	 * Constructs our players and their appropriate cards, and adds this to the
	 * Board.
	 */
	private Game setupGame() {

		Board board = new Board();

		// First, get all our cards in shuffled lists
		List<String> names = Arrays.asList(Character.CHARACTERS);
		Collections.shuffle(names);
		List<Character> cCards = setUpCharCards();
		List<Weapon> wCards = setUpWeapCards();
		List<Room> rCards = setUpRoomCards();

		// Construct our solution from 1st cards in shuffled list
		Solution sol = new Solution(cCards.remove(0), rCards.remove(0),
				wCards.remove(0));

		// Now merge all the cards
		List<Card> cardsLeft = new ArrayList<Card>();
		cardsLeft.addAll(cCards);
		cardsLeft.addAll(wCards);
		cardsLeft.addAll(rCards);
		Collections.shuffle(cardsLeft);

		// And finally our players.
		List<Player> players = new ArrayList<Player>();
		int numCardsEach = cardsLeft.size() / numPlayers;
		for (int i = 0; i < numPlayers; i++) {
			String myName = names.get(i);
			// 1st char in 1st name capitalised + 1st char in surname
			// capitalised.
			String mySymbol = myName.charAt(0)
					+ ""
					+ java.lang.Character.toLowerCase(myName.charAt(myName
							.lastIndexOf(" ") + 1));
			Location myStart = Player.startLocation(myName);
			List<Card> myCards = new ArrayList<Card>();
			for (int j = 0; j < numCardsEach; j++) {
				// Automatically shifts stuff down for next time as .remove().
				myCards.add(cardsLeft.remove(0));
			}
			players.add(new Player(myStart, myName, mySymbol, myCards));
		}
		Game newGame = new Game(board, sol, cardsLeft, players);
		return newGame;
	}

	/**
	 * creates all the character cards and shuffles the pile
	 * 
	 * @return characters the list to be returned
	 */
	private List<Character> setUpCharCards() {
		List<Character> characters = new ArrayList<Character>();
		for (String s : Character.CHARACTERS) {
			characters.add(new cluedo.cards.Character(s));
		}

		Collections.shuffle(characters);
		return characters;
	}

	/**
	 * creates all the weapons cards and shuffles the pile
	 * 
	 * @return weapons the list to be returned
	 */
	private List<Weapon> setUpWeapCards() {
		List<Weapon> weapons = new ArrayList<Weapon>();
		for (String s : Weapon.WEAPONS) {
			weapons.add(new cluedo.cards.Weapon(s));
		}

		Collections.shuffle(weapons);
		return weapons;
	}

	/**
	 * creates all the room cards and shuffles the pile
	 * 
	 * @return rooms the list to be returned
	 */
	private List<Room> setUpRoomCards() {
		List<Room> rooms = new ArrayList<Room>();
		for (String s : Room.ROOMS) {
			rooms.add(new cluedo.cards.Room(s));
		}

		Collections.shuffle(rooms);
		return rooms;
	}

	/**
	 * Launch point.
	 * 
	 * @param args
	 *            Do nothing.
	 */
	public static void main(String args[]) {
		new Main();
	}
}
