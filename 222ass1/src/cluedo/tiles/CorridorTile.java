package cluedo.tiles;

/**
 * A corridor on the board. This is pretty much any tile that isn't anything
 * more interesting (a room, door or intrigue tile) so prints itself as a blank
 * space!
 * 
 * @author Andrew & Michael
 * 
 */
public class CorridorTile extends Tile {

	public CorridorTile(int x, int y) {
		super("  ", x, y);
	}

	public CorridorTile(String string, int x, int y) {
		super(string, x, y);
	}

}
