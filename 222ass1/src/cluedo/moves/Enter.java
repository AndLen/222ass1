package cluedo.moves;

import cluedo.main.Board;
import cluedo.objects.Chamber;

/**
 * when the player enters into a room
 * @author Michael
 *
 */
public class Enter implements MoveI {
	
	private final Chamber room; 
	
	public Enter(Chamber room){
		this.room = room;
	}

	@Override
	public boolean isValid(Board board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void apply(Board board) {
		// TODO Auto-generated method stub

	}

}
