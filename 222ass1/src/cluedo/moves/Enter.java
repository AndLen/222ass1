package cluedo.moves;

import cluedo.main.Board;
import cluedo.main.Location;
import cluedo.objects.Chamber;

/**
 * when the player enters into a room
 * 
 * @author Michael
 * 
 */
public class Enter implements MoveI {

	private Location oldPosition;
	private int diceRoll;
	private Location newPosition;
	private final Chamber room;

	public Enter(Location oldPosition, Location newPosition, int diceRoll,
			Chamber room) {
		super();
		this.oldPosition = oldPosition;
		this.diceRoll = diceRoll;
		this.newPosition = newPosition;
		this.room = room;
	}

	public Enter(Chamber room) {
		this.room = room;
	}

	@Override
	public boolean isValid(Board board) {
		// TODO 

		Move move = new Move(oldPosition, newPosition, diceRoll);
		try {
			move.isValid(board);
			// if this return is reached the move does not end in a room
			// therefore move is invalid
			return false;
		}
		// i want to recieve this exception (moves is likley to be valid)
		catch (CluedoException e) {}
		
		// we can now check if a door was usedto enter this room
		
		
		return false;
	}

	@Override
	public void apply(Board board) {
		// TODO Auto-generated method stub

	}

}
