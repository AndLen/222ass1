package cluedo.tiles;

public abstract class Tile implements TileI {
	protected String stringRep;
	/**
	 * the positionon the board
	 */
	private int x;
	private int y;

	public Tile(String s, int x, int y) {
		stringRep = s;
		this.x = x;
		this.y = y;
	}	

	public void draw() {
		System.out.print(stringRep);

	}
	
	/**
	 * String rep of the tile 
	 */
	public String toString(){
		return stringRep;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
}
