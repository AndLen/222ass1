/**
 * 
 */
package cluedo.moves;

import cluedo.main.Board;

/**
 * 
 * different types of moves are moving certain amount of spaces, moving into a
 * room, moving out of a room, moving through a secret passage e.t.c
 * 
 * @author Michael
 * 
 */
public interface MoveI {

	/**
	 * 
	 * @return true if the move is allowed to be made, false if not
	 * @throws Exception 
	 * @throws CluedoException 
	 */
	public boolean isValid(Board board) throws CluedoException;

	/**
	 * applies the move
	 */
	public void apply(Board board);

}
