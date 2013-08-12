package cluedo.tiles;

/**
 * An implementation of the TileI interface that provides some common
 * code/method implementations across all Tiles. Not a concrete class as we
 * shouldn't have any old generic tile - each tile should be a certain type!
 * 
 * @author Andrew
 * 
 */
public abstract class Tile implements TileI {
	protected String stringRep;
	/**
	 * the position on the board
	 */
	private int x;
	private int y;

	public Tile(String s, int x, int y) {
		stringRep = s;
		this.x = x;
		this.y = y;
	}

	/**
	 * String rep of the tile
	 */
	@Override
	public String toString() {
		return stringRep;
	}

	/**
	 * @return the x
	 */
	@Override
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	@Override
	public int getY() {
		return y;
	}
}
