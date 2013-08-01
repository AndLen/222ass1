package cluedo.moves;

import cluedo.main.Game;
import cluedo.structs.Chamber;
import cluedo.structs.Location;

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
	public boolean isValid(Game game) {
		// TODO 

		Move move = new Move(oldPosition, newPosition, diceRoll,game);
		try {
			move.isValid(game);
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
	public void apply(Game game) {
		// TODO Auto-generated method stub

	}

}
