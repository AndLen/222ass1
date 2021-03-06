package cluedo.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cluedo.structs.Location;
import cluedo.structs.Player;
import cluedo.tiles.CorridorTile;
import cluedo.tiles.DoorTile;
import cluedo.tiles.IntrigueTile;
import cluedo.tiles.RoomTile;
import cluedo.tiles.Tile;

/**
 * Represents the current "physical" board. Essentially the main data structure
 * for our program.
 * 
 * @author Andrew & Michael
 * 
 */
public class Board {
	// Public as static and final -- who cares if other classes can see these!
	// -- makes things easier.
	public static final int BOARD_WIDTH = 24;
	public static final int BOARD_HEIGHT = 29;
	// Physical board.
	private final Tile[][] gameBoard;

	private List<DoorTile> listOfDoors;

	public Board() {
		gameBoard = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
		populateBoard();
	}

	/**
	 * Gives what's at a certain place on the board.
	 * 
	 * @param l
	 * @return
	 */
	public Tile tileAtLocation(Location l) {
		if (!Location.isValid(l)) {
			throw new IllegalArgumentException("Location " + l.getX() + " "
					+ l.getY() + "isn't valid");
		}
		return gameBoard[l.getX()][l.getY()];
	}

	/*
	 * Used to print the gameBoard :-)
	 */
	public String toString(Game g) {
		// More efficient using a StrBlder.
		StringBuilder sb = new StringBuilder();
		// Extra to compensate for shift due to vertical axis.
		sb.append("   ");
		// Print x-coords across the top
		for (int a = 0; a < BOARD_WIDTH; a++) {
			sb.append(digitToString(a));
		}
		sb.append("\n");
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			// Print y axis labels (left)
			sb.append(digitToString(i));
			for (int j = 0; j < BOARD_WIDTH; j++) {
				// hasplayer x, y
				Player p = hasPlayer(j, i, g);
				if (p != null) {
					sb.append(p.getMySymbol() + " ");
				} else
					sb.append(gameBoard[j][i].toString() + " ");
			}
			// Print y axis labels (right)
			sb.append(digitToString(i));
			// End of row
			sb.append("\n");
		}
		// Extra to compensate for shift due to vertical axis.
		sb.append("   ");
		// Print x-coords across the bottom
		for (int a = 0; a < BOARD_WIDTH; a++) {
			sb.append(digitToString(a));
		}
		sb.append("\n");
		// End of board.
		sb.append("\n");
		return sb.toString();
	}

	/**
	 * Is this location a player? Inefficient...but only <=6 players, so less
	 * expensive than a map probably.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private Player hasPlayer(int i, int j, Game g) {
		Location l = new Location(i, j);
		for (Player p : g.getPlayers()) {
			if (p.getLocation().equals(l)) {
				return p;
			}
		}
		// No player found
		return null;
	}

	/**
	 * Formats a number for printing to the board.
	 * 
	 * @param i
	 * @return
	 */
	private String digitToString(int i) {
		// Needs additional space
		if (i < 10) {
			return i + "  ";
		}
		return i + " ";
	}

	/**
	 * gets all moves that are on gameBoard ignoring walls and doors given a
	 * dice roll - i.e. a player can move in diceroll steps in x or y or a
	 * combination of the two.
	 * 
	 * 
	 * @param oldPosition
	 * @param dice
	 * @return a list of all positions on gameBoard where a player may move
	 */
	public Set<Location> getMovesTo(Location oldPosition, int dice) {

		Set<Location> list = new TreeSet<Location>();

		for (int i = -dice; i <= dice; i++) {
			for (int j = dice; j >= -dice; j--) {
				Location newPosition = new Location(oldPosition.getX() + i,
						oldPosition.getY() + j);

				if (!Location.isValid(newPosition)) {
					// Not valid, loop around again.
					continue;
				}
				// this position is on the gameBoard

				// NW and SE corners
				if (i + j > dice || i + j < -dice) {
					continue;
				}

				// NE and SW corners
				if (Math.abs(i) + Math.abs(j) > dice) {
					continue;
				}
				// We got here, it must be valid!
				list.add(new Location(newPosition));

			}
		}

		return list;

	}

	/**
	 * Fills our board array with less code.
	 */
	private void fillRoom(int x, int y, int x2, int y2, String type,
			String acronym) {
		for (; x <= x2; x++) {
			// Uses a second variable or else y gets massive, heh. Dumb bug.
			for (int y1 = y; y1 <= y2; y1++) {
				if (type.equals("Corridor")) {
					gameBoard[x][y1] = new CorridorTile(x, y1);
				} else if (type.equals("Room")) {
					gameBoard[x][y1] = new RoomTile(acronym, x, y1);
				} else
					throw new IllegalArgumentException("Not a supported room.");
			}
		}
	}

	/**
	 * Adds all our doors and rooms as well as intrigue tiles.
	 */
	private void populateBoard() {
		// Fill everywhere with corridor to start with.
		fillRoom(0, 0, BOARD_WIDTH - 1, BOARD_HEIGHT - 1, "Corridor", "");
		// Tile door = new DoorTile();
		// Spa
		fillRoom(0, 0, 5, 5, "Room", "SP");
		fillRoom(0, 6, 4, 7, "Room", "SP");
		gameBoard[5][5] = new DoorTile("SP", 0, 1, 5, 5);

		// Theatre
		fillRoom(8, 0, 12, 7, "Room", "TH");
		gameBoard[10][7] = new DoorTile("TH", 0, 1, 10, 7);

		// Living Room
		fillRoom(14, 0, 19, 7, "Room", "LI");
		fillRoom(15, 8, 17, 8, "Room", "LI");
		gameBoard[16][8] = new DoorTile("LI", 0, 1, 16, 8);

		// Observatory
		fillRoom(22, 0, 23, 8, "Room", "OB");
		gameBoard[22][7] = new DoorTile("OB", -1, 0, 22, 7);

		// Hall
		fillRoom(19, 11, 23, 17, "Room", "HA");
		gameBoard[19][13] = new DoorTile("HA", -1, 0, 19, 13);
		gameBoard[19][14] = new DoorTile("HA", -1, 0, 19, 14);
		gameBoard[22][11] = new DoorTile("HA", 0, -1, 22, 11);

		// Guest House
		fillRoom(20, 21, 23, 28, "Room", "GU");
		fillRoom(21, 20, 23, 20, "Room", "GU");
		gameBoard[20][21] = new DoorTile("GU", 0, -1, 20, 21);
		gameBoard[21][20] = new DoorTile("GU", -1, 0, 21, 20);

		// Dining room
		fillRoom(10, 19, 15, 22, "Room", "DI");
		fillRoom(9, 23, 16, 28, "Room", "DI");
		gameBoard[12][19] = new DoorTile("DI", 0, -1, 12, 19);
		gameBoard[15][21] = new DoorTile("DI", 1, 0, 15, 21);

		// Kitchen
		fillRoom(0, 21, 5, 21, "Room", "KI");
		fillRoom(0, 22, 6, 28, "Room", "KI");
		gameBoard[6][22] = new DoorTile("KI", 0, -1, 6, 22);

		// Patio
		fillRoom(0, 10, 3, 18, "Room", "PA");
		fillRoom(4, 11, 7, 17, "Room", "PA");
		gameBoard[5][11] = new DoorTile("PA", 0, -1, 5, 11);
		gameBoard[7][12] = new DoorTile("PA", 1, 0, 7, 12);
		gameBoard[7][16] = new DoorTile("PA", 1, 0, 7, 16);
		gameBoard[5][17] = new DoorTile("PA", 0, 1, 5, 17);

		// Pool
		fillRoom(10, 11, 17, 16, "Room", "PO");
		gameBoard[14][11] = new DoorTile("PO", 0, -1, 14, 11);
		gameBoard[10][16] = new DoorTile("PO", 0, 1, 10, 16);
		gameBoard[17][16] = new DoorTile("PO", 0, 1, 17, 16);

		// add all doors to the list
		listOfDoors = new ArrayList<DoorTile>();
		for (Tile[] ta : gameBoard) {
			for (Tile t : ta) {
				if (t instanceof DoorTile) {
					listOfDoors.add((DoorTile) t);
				}
			}
		}

		setupIntrigueTiles();

	}

	/**
	 * adds Intrigue tiles to board, replacing corridor tiles.
	 */
	private void setupIntrigueTiles() {
		gameBoard[3][8] = new IntrigueTile(3, 8);
		gameBoard[7][4] = new IntrigueTile(7, 4);
		gameBoard[11][9] = new IntrigueTile(11, 9);
		gameBoard[13][0] = new IntrigueTile(13, 0);
		gameBoard[16][10] = new IntrigueTile(16, 10);
		gameBoard[21][6] = new IntrigueTile(21, 6);
		gameBoard[8][14] = new IntrigueTile(8, 14);
		gameBoard[14][17] = new IntrigueTile(14, 17);
		gameBoard[4][18] = new IntrigueTile(4, 18);
		gameBoard[20][18] = new IntrigueTile(20, 18);
		gameBoard[8][23] = new IntrigueTile(8, 23);
		gameBoard[18][22] = new IntrigueTile(18, 22);

	}

	/**
	 * @return the setOfDoors
	 */
	public List<DoorTile> getListOfDoors() {
		return listOfDoors;
	}

	/**
	 * Finds the first free tile for this location (i.e. with no player in!).
	 * 
	 * @param players
	 *            on the board
	 * @param symbol
	 *            of tile to find
	 * @return free location or null if there's none.
	 */
	public Location getFreeTile(Collection<Player> players, String symbol) {
		for (int i = 0; i < BOARD_WIDTH; i++) {
			for (int j = 0; j < BOARD_HEIGHT; j++) {
				if (gameBoard[i][j].toString().equals(symbol)) {
					boolean nobodyHere = true;
					for (Player p : players) {
						if (p.getLocation().equals(new Location(i, j))) {
							nobodyHere = false;
							break;
						}
					}
					if (nobodyHere) {
						// Awesome, found a spot. That one will do fine.
						return new Location(i, j);
					}
				}
			}
		}
		// Drat. How did this happen!?
		throw new IllegalArgumentException("No tile found!!");
	}

}
