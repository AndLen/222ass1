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
	private char[][] board = new char[25][25];
	private static final int BOARD_WIDTH = 25;

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

		generateSolution();
		generatePlayers();
		generateBoard();
		drawBoard();
	}

	/**
	 * Initial board layout sans players.
	 */
	private void generateBoard() {
		// Do stuff here with loading the rooms etc.

	}

	/**
	 * Draws an ASCII representation of the board to our command-line output.
	 */
	private void drawBoard() {
		for (int i = 0; i < BOARD_WIDTH; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				if (board[i][j] == 0) {
					// Nothing interesting here
					System.out.print(" ");
				}
			}
			// New line after each row.
			System.out.println();
		}

	}

	/**
	 * Generates objects for the remaining players - excluding the objects
	 * already used in the solution, obviously!
	 */
	private void generatePlayers() {
		// TODO Auto-generated method stub

	}

	/**
	 * creates all the character cards and shuffles the pile
	 * 
	 * @param characters
	 *            the list to be returned
	 */
	private void setUpCharCards() {
		List<Character> characters  = new ArrayList<Character>();
		for (String s : CharacterI.CHARACTERS) {
			characters.add(new cluedo.cards.Character(s));
		}

		Collections.shuffle(characters);
	}

	/**
	 * creates all the weapons cards and shuffles the pile
	 * 
	 * @return weapons the list to be returned
	 */
	private List<Weapon> setUpWeapCards() {
		List<Weapon> weapons = new ArrayList<Weapon>();
		for (String s : WeaponI.WEAPONS) {
			weapons.add(new cluedo.cards.Weapon(s));
		}

		Collections.shuffle(weapons);
		return weapons;
	}

	/**
	 * creates all the weapons cards and shuffles the pile
	 * 
	 * @param weapons
	 *            the list to be returned
	 * @return
	 */
	private List<Room> setUpRoomCards() {
		List<Room> rooms = new ArrayList<Room>();
		for (String s : RoomI.ROOMS) {
			rooms.add(new cluedo.cards.Room(s));
		}

		Collections.shuffle(rooms);
		return rooms;
	}

	/**
	 * Constructs our random solution for this game.
	 */
	private void generateSolution() {
		// TODO Auto-generated method stub

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
