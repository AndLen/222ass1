package cluedo.tiles;

public class DoorTile extends RoomTile {

	private int xDir;
	private int yDir;
	private String roomInitial;
	

	/**
	 * 
	 * @param yDir
	 *            if a door is facing south yDir = 1, north = -1, East/West = 0
	 *            unless North west e.t.c
	 * @param xDir
	 *            if a door is facing west xDir = -1, east = 1, North/South = 0
	 *            unless North West e.t.c
	 */
	public DoorTile(String roomInitial, int xDir, int yDir, int x, int y) {
		super(" #", x, y);

		if (xDir < -1 || xDir > 1) {
			throw new IllegalArgumentException("xDir must be -1, 0 or 1");
		}
		if (yDir < -1 || yDir > 1) {
			throw new IllegalArgumentException("yDir must be -1, 0 or 1");
		}
		this.roomInitial = roomInitial;
		this.xDir = xDir;
		this.yDir = yDir;

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
	
	/**
	 * Room it belongs to - NOT the "#" rep for the door.
	 */
	public String roomRep(){
		return roomInitial;
	}


}
