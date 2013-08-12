package cluedo.moves;

import java.util.ArrayList;
import java.util.List;

import cluedo.main.Game;
import cluedo.structs.Location;
import cluedo.tiles.DoorTile;

/**
 * when the player enters into a room
 * 
 * @author Andrew & Michael
 * 
 */
public class Enter extends Move {


	public Enter(Location oldPosition, Location newPosition, int diceRoll,
			Game game) {
		super(oldPosition, newPosition,diceRoll,game);
	}


	@Override
	public boolean isValid(Game game) {
		// TODO
		//
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
