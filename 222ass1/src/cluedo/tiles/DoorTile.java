package cluedo.tiles;

public class DoorTile extends Tile {

	private int xDir;
	

	private int yDir;

	/**
	 * 
	 * @param yDir
	 *            if a door is facing south yDir = 1, north = -1, East/West = 0
	 *            unless North west e.t.c
	 * @param xDir
	 *            if a door is facing west xDir = -1, east = 1, North/South = 0
	 *            unless North West e.t.c
	 */
	public DoorTile(int xDir, int yDir, int x, int y) {
		super(" #", x, y);
		if (xDir < -1 || xDir > 1) {
			throw new IllegalArgumentException("xDir must be -1, 0 or 1");
		}
		if (yDir < -1 || yDir > 1) {
			throw new IllegalArgumentException("yDir must be -1, 0 or 1");
		}

		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the xDir
	 */
	public int getxDir() {
		return xDir;
	}

	/**
	 * @return the yDir
	 */
	public int getyDir() {
		return yDir;
	}
	
}
