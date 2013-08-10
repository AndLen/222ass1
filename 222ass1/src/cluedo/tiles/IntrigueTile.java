package cluedo.tiles;

/**
 * intrigue tile represented by a ? on the board when a player lands on this
 * they get to pick up a card from the intrigue deck
 * 
 * @author Michael
 * 
 */
public class IntrigueTile extends CorridorTile{

	public IntrigueTile(int x, int y) {
		super(" ?", x, y);
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
