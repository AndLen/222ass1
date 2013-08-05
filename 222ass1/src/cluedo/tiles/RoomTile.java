package cluedo.tiles;

/**
 * Tile which corresponds to a room.
 * 
 * @author Andrew
 * 
 */
public class RoomTile extends Tile {
	
	public RoomTile(String s, int x, int y){
		super(s,x,y);
	}

	
	/**
	 * What room this room tile belongs to.
	 * @return
	 */
	public String roomRep(){
		return stringRep;
	}
}
