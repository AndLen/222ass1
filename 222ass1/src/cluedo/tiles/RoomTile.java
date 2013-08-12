package cluedo.tiles;

/**
 * Tile which corresponds to a room. The string is the two-char representation
 * of the room on the board (e.g. PA, GU etc). A roomtile is one in which a
 * player can make a suggestion (or in the case of the pool, an accusation).
 * 
 * @author Andrew & Michael
 * 
 */
public class RoomTile extends Tile {

	public RoomTile(String s, int x, int y) {
		super(s, x, y);
	}

	/**
	 * What room this room tile belongs to. This is identical for a RoomTile as
	 * the stringRep, but varies for it's child (DoorTile).
	 * 
	 * @return
	 */
	public String roomRep() {
		return stringRep;
	}
}
