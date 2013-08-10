package cluedo.tiles;

/**
 * A tile object is one which is drawable and has co-ordinates on the board - it
 * represents the physical 1x1 square on a board.
 * 
 * @author Andrew & Michael
 * 
 */
public interface TileI {
	
	/**
	 * Draw the tile to System.out
	 */
	public void draw();

	/**
	 * String rep of the tile
	 */
	public String toString();

	/**
	 * @return the x-coord
	 */
	public int getX();

	/**
	 * @return the y-coord
	 */
	public int getY();

}
