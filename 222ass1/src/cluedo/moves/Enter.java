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
		super(oldPosition, newPosition, diceRoll, game);
	}

	@Override
	public boolean isValid(Game game) {
		List<Location> list = new ArrayList<Location>();
		for (DoorTile d : game.getDoors()) {

			// the door needs to belong to the same room as the one entered

			if (d.roomRep().equals(game.getRoom(newPosition))) {
				Location outsideDoor = new Location(d.getX() + d.getxDir(),
						d.getY() + d.getyDir()); // the position one step before
													// the
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
