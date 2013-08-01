package cluedo.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import cluedo.cards.*;
import cluedo.cards.Character;

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
		game.begin();
		System.out.println("Thanks for playing!");
	}

	/**
	 * Constructs our players and their appropriate cards, and adds this to the Board.
	 */
	private Game setupGame() {
		
		Board board = new Board();
		//First, get all our cards in shuffled lists
		List<CharacterI> cCards = setUpCharCards();
		List<WeaponI> wCards = setUpWeapCards();
		List<RoomI> rCards = setUpRoomCards();
		//Construct our solution. Has no name or location as not a "real" player!
		Player solution = new Player(null,null,cCards.get(0),rCards.get(0),wCards.get(0));
		//And finally our players.
		for(int i = 1; i <= numPlayers; i++){
			//TODO: Change it so players won't always get the same character as their card.
			CharacterI myName = cCards.get(i);
			Location myStart = Player.startLocation(myName);
			board.addPlayer(new Player(myStart,myName,cCards.get(i),rCards.get(i),wCards.get(i)));
		}
		Game newGame = new Game(board, solution);
		return newGame;
	}


	/**
	 * creates all the character cards and shuffles the pile
	 *
	 * @return characters
	 *            the list to be returned
	 */
	private List<CharacterI> setUpCharCards() {
		List<CharacterI> characters = new ArrayList<CharacterI>();
		for (String s : CharacterI.CHARACTERS) {
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
	private List<WeaponI> setUpWeapCards() {
		List<WeaponI> weapons = new ArrayList<WeaponI>();
		for (String s : WeaponI.WEAPONS) {
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
	private List<RoomI> setUpRoomCards() {
		List<RoomI> rooms = new ArrayList<RoomI>();
		for (String s : RoomI.ROOMS) {
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
