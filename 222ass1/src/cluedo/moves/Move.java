package cluedo.moves;

import cluedo.main.Board;
import cluedo.main.Tile;

/**
 * use to get where player may move to, enter room, walk along corridor...
 * 
 * @author Michael
 * 
 */
public class Move implements MoveI {

	private int x;
	private int y;
	private Tile tile;
	private int diceRoll;
	
	/**
	 * 
	 * @param x
	 *            the x coordinate of the cell moving to
	 * @param y
	 *            the y coordinate of the cell moving to
	 */
	public Move(int x, int y) {
		this.x = x;
		this.y = y;

	}
	
	/**
	 * 
	 * @param tile the tile to be moved to
	 * @param diceRoll the number rolled on the dice
	 */
	public Move(Tile tile, int diceRoll){
		this.tile = tile;
		this.diceRoll = diceRoll;
	}
	

	@Override
	public boolean isValid(Board board) {
		// TODO Auto-generated method stub
		// can we jump over a player? i know they cant when they are blocking a
		// door, but can they if the are in hallway
		return false;
	}

	@Override
	public void apply(Board board) {
		// TODO Auto-generated method stub

	}

}
