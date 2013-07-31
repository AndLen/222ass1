package cluedo.main;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lensenandr
 * 
 */
public class Board {
	private static final int BOARD_WIDTH = 24;
	private static final int BOARD_HEIGHT = 29;
	private static Tile[][] board = new Tile[BOARD_WIDTH][BOARD_HEIGHT];

	static {
		// Spa
		Tile spa = new Tile("Sp");
		fillRoom(0, 0, 5, 5, spa);
		fillRoom(0, 6, 4, 7, spa);

		// Theatre
		Tile theatre = new Tile("Th");
		fillRoom(8, 0, 12, 7, theatre);

		// Living Room
		Tile livingRoom = new Tile("Li");
		fillRoom(14, 0, 19, 7, livingRoom);
		fillRoom(15, 8, 17, 8, livingRoom);

		// Observatory
		Tile observatory = new Tile("Ob");
		fillRoom(22, 0, 23, 8, observatory);

		// Hall
		Tile hall = new Tile("Ha");
		fillRoom(19, 11, 23, 17, hall);

		// Guest House
		Tile guestHouse = new Tile("Gu");
		fillRoom(20, 21, 23, 28, guestHouse);
		fillRoom(21, 20, 23, 20, guestHouse);

		// Dining room
		Tile diningRoom = new Tile("Di");
		fillRoom(10, 19, 15, 22, diningRoom);
		fillRoom(9, 23, 16, 28, diningRoom);

		// Kitchen
		Tile kitchen = new Tile("Ki");
		fillRoom(0, 21, 5, 21, kitchen);
		fillRoom(0, 22, 6, 28, kitchen);

		// Patio
		Tile patio = new Tile("Pa");
		fillRoom(0, 10, 3, 18, patio);
		fillRoom(4, 11, 7, 17, patio);

		// Pool
		Tile pool = new Tile("Po");
		fillRoom(10, 11, 17, 16, pool);
	}

	private static void fillRoom(int x, int y, int x2, int y2, Tile type) {
		for (; x <= x2; x++) {
			// Uses a second variable or else y gets massive, heh. Dumb bug.
			for (int y1 = y; y1 <= y2; y1++) {
				board[x][y1] = type;
			}
		}
	}

	/*
	 * Used to print the board :-)
	 */
	public String toString() {
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
				if (board[j][i] == null) {
					sb.append("   ");
				} else
					sb.append(board[j][i].toString() + " ");
			}
			// End of row
			sb.append("\n");
		}
		return sb.toString();
	}

	private String digitToString(int i) {
		// Needs additional space
		if (i < 10) {
			return i + "  ";
		}
		return i + " ";
	}

	
	/**
	 * gets all moves that are on board ignoring walls and doors
	 * 
	 * @param oldPosition
	 * @param dice
	 * @return a list of all poistions on board where a player may move
	 */
	public static List<Location> getMovesTo(Location oldPosition, int dice) {
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
			// this position is on the board
			list.add(new Location(newPosition));

		}

		return list;

	}
}
