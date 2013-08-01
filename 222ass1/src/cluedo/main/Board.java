package cluedo.main;

import java.util.ArrayList;
import java.util.List;

import cluedo.structs.Location;
import cluedo.structs.Player;
import cluedo.tiles.CorridorTile;
import cluedo.tiles.DoorTile;
import cluedo.tiles.RoomTile;
import cluedo.tiles.Tile;

/**
 * Represents the current "physical" board. Essentially the main data structure
 * for our program.
 * 
 * @author lensenandr
 * 
 */
public class Board {
	private static final int BOARD_WIDTH = 24;
	private static final int BOARD_HEIGHT = 29;
	// This needs to stay private as it's not final. Not static anymore so
	// should be easy to edit safely!
	private Tile[][] gameBoard;

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
		return gameBoard[l.getX()][l.getY()];
	}

	private void fillRoom(int x, int y, int x2, int y2, Tile type) {
		for (; x <= x2; x++) {
			// Uses a second variable or else y gets massive, heh. Dumb bug.
			for (int y1 = y; y1 <= y2; y1++) {
				gameBoard[x][y1] = type;
			}
		}
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
			// Print y axis labels
			sb.append(digitToString(i));
			for (int j = 0; j < BOARD_WIDTH; j++) {
				Player p = hasPlayer(i, j, g);
				if (p != null) {
					sb.append(p.getMySymbol() + " ");
				} else
					sb.append(gameBoard[j][i].toString() + " ");
			}
			// End of row
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Inefficient...but only <=6 players, so less expensive than a map
	 * probably.
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

	private String digitToString(int i) {
		// Needs additional space
		if (i < 10) {
			return i + "  ";
		}
		return i + " ";
	}

	/**
	 * gets all moves that are on gameBoard ignoring walls and doors
	 * 
	 * 
	 * @param oldPosition
	 * @param dice
	 * @return a list of all poistions on gameBoard where a player may move
	 */
	public List<Location> getMovesTo(Location oldPosition, int dice) {
		// TODO need to implement it so not only the max amount of moves is
		// possible(at the moment player must move dice squares and not back and
		// forward)

		// TODO Does this allow diagonal moves or just straight lines?
		// e.g. -> -> | or just -> -> ->
		// v
		List<Location> list = new ArrayList<Location>();
		Location newPosition = new Location();

		for (int i = 0, j = dice; i <= dice; i++, j--) {

			newPosition.setX(i);
			newPosition.setY(j);

			if (newPosition.getX() < 0 || newPosition.getX() > BOARD_WIDTH) {
				continue;
			}

			if (newPosition.getY() < 0 || newPosition.getY() > BOARD_HEIGHT) {
				continue;
			}
			// this position is on the gameBoard
			list.add(new Location(newPosition));

		}

		return list;

	}

	/**
	 * Adds all our doors and rooms TODO: Add doors at bottom of method (should
	 * all be 1x1 square and probably use " D ").
	 */
	private void populateBoard() {
		// Fill everywhere with corridor to start with.
		Tile corridor = new CorridorTile();
		fillRoom(0, 0, BOARD_WIDTH - 1, BOARD_HEIGHT - 1, corridor);
		Tile door = new DoorTile();
		// Spa
		Tile spa = new RoomTile("SP");
		fillRoom(0, 0, 5, 5, spa);
		fillRoom(0, 6, 4, 7, spa);
		gameBoard[5][5] = door;

		// Theatre
		Tile theatre = new RoomTile("TH");
		fillRoom(8, 0, 12, 7, theatre);
		gameBoard[10][7] = door;

		// Living Room
		Tile livingRoom = new RoomTile("LI");
		fillRoom(14, 0, 19, 7, livingRoom);
		fillRoom(15, 8, 17, 8, livingRoom);
		gameBoard[16][8] = door;

		// Observatory
		Tile observatory = new RoomTile("OB");
		fillRoom(22, 0, 23, 8, observatory);
		gameBoard[22][7] = door;

		// Hall
		Tile hall = new RoomTile("HA");
		fillRoom(19, 11, 23, 17, hall);
		gameBoard[19][13] = door;
		gameBoard[19][14] = door;
		gameBoard[22][11] = door;

		// Guest House
		Tile guestHouse = new RoomTile("GU");
		fillRoom(20, 21, 23, 28, guestHouse);
		fillRoom(21, 20, 23, 20, guestHouse);
		gameBoard[20][20] = door;

		// Dining room
		Tile diningRoom = new RoomTile("DI");
		fillRoom(10, 19, 15, 22, diningRoom);
		fillRoom(9, 23, 16, 28, diningRoom);
		gameBoard[12][19] = door;
		gameBoard[15][21] = door;

		// Kitchen
		Tile kitchen = new RoomTile("KI");
		fillRoom(0, 21, 5, 21, kitchen);
		fillRoom(0, 22, 6, 28, kitchen);
		gameBoard[6][22] = door;

		// Patio
		Tile patio = new RoomTile("PA");
		fillRoom(0, 10, 3, 18, patio);
		fillRoom(4, 11, 7, 17, patio);
		gameBoard[5][11] = door;
		gameBoard[7][12] = door;
		gameBoard[7][16] = door;
		gameBoard[5][17] = door;

		// Pool
		Tile pool = new RoomTile("PO");
		fillRoom(10, 11, 17, 16, pool);
		gameBoard[14][11] = door;
		gameBoard[10][16] = door;
		gameBoard[17][16] = door;
	}
}
