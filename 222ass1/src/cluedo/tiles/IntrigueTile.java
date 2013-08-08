package cluedo.tiles;

/**
 * intrigue tile represented by a ? on the board when a player lands on this
 * they get to pick up a card from the intrigue deck
 * 
 * @author Michael
 * 
 */
public class IntrigueTile extends Tile{

	public IntrigueTile(int x, int y) {
		super(" ?", x, y);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
