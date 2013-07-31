package cluedo.main;

/**
 * @author lensenandr
 *
 */
public class Board {
	private static final int BOARD_WIDTH = 24;
	private static final int BOARD_HEIGHT = 29;
	private static Tile[][] board = new Tile[BOARD_WIDTH][BOARD_HEIGHT];

	static{
		//Spa
		Tile spa = new Tile("Sp");
		fillRoom(0,0,5,5, spa);
		fillRoom(0, 6, 4, 7, spa);

		//Theatre
		Tile theatre = new Tile("Th");
		fillRoom(8, 0, 12, 7 , theatre);

		//Living Room
		Tile livingRoom = new Tile("Li");
		fillRoom(14, 0, 19, 7 , livingRoom);
		fillRoom(15, 8, 17, 8, livingRoom);

		//Observatory
		Tile observatory = new Tile("Ob");
		fillRoom(22, 0, 23, 8 , observatory);
	}

	private static void fillRoom(int x, int y, int width, int height, Tile type){
		for(;x<=width;x++){
			//Uses a second variable or else y gets massive, heh. Dumb bug.
			for(int y1 = y;y1<=height;y1++){
				board[x][y1] = type;
			}
		}
	}
	public String toString(){
		Tile[][]b2 = board;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < BOARD_HEIGHT; i++){
			for(int j = 0; j < BOARD_WIDTH; j++){
				if(board [j][i] == null){
					sb.append("  ");
				}
				else sb.append(board[j][i].toString());
			}
			//End of row
			sb.append("\n");
		}
		return sb.toString();
	}
}
