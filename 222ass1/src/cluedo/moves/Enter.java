package cluedo.moves;

import java.util.ArrayList;
import java.util.List;

import cluedo.main.Game;
import cluedo.structs.Location;
import cluedo.tiles.DoorTile;

/**
 * when the player enters into a room
 * 
 * @author Michael
 * 
 */
public class Enter extends Move {


	public Enter(Location oldPosition, Location newPosition, int diceRoll,
			Game game) {
		super(oldPosition, newPosition,diceRoll,game);
	}

	// public Enter(Chamber room) {
	// this.room = room;
	// }

	@Override
	public boolean isValid(Game game) {
		// TODO
		//
		// Move move = new Move(oldPosition, newPosition, diceRoll, game);
		// try {
		// move.isValid(game);
		// // if this return is reached the move does not end in a room
		// // therefore move is invalid
		// return false;
		// }
		// // i want to recieve this exception (move is likely to be valid)
		// catch (CluedoException e) {
		// }

		// we can now check if a door was used to enter this room

		// TODO if we count the taps back from each door of the room entered
		// then check that that list contains the oldPosition

		// i could -1 off dice make the oldpos one step outside of door (by
		// using the x/y dirs from the doortile class) then calculate all
		// positions possible.

		// for(each doorTile to the room entered){
		List<Location> list = new ArrayList<Location>();
		// diceRoll--; // by using the position infront of the door, one
		// tap/move
		// has already been taken
		for (DoorTile d : game.getDoors()) {
			
			// the door needs to belong to the same room as the one entered
			
			if(d.roomRep().equals(game.getRoom(newPosition))){
			Location outsideDoor = new Location(d.getX() + d.getxDir(),
					d.getY() + d.getyDir()); // the position one step before the
												// door.
			list.addAll(game.getMovesTo(outsideDoor, diceRoll));
			}
		}

		if (list.contains(oldPosition)) {
			return true;
		}
		return false;
	}


}
