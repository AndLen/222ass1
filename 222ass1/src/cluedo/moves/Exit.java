/**
 * 
 */
package cluedo.moves;

import java.util.Set;
import java.util.TreeSet;

import cluedo.main.Game;
import cluedo.structs.Location;
import cluedo.tiles.DoorTile;

/**
 * when a user exits a room and enters a corridor
 * 
 * @author Michael
 * 
 */
public class Exit implements MoveI {

	private Location oldPosition;
	private int diceRoll;
	private Location newPosition;

	public Exit(Location oldPosition, Location newPosition, int diceRoll,
			Game game) {
		super();
		this.oldPosition = oldPosition;
		this.diceRoll = diceRoll;
		this.newPosition = newPosition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cluedo.moves.MoveI#isValid(cluedo.main.Game)
	 */
	@Override
	public boolean isValid(Game game) {
		// TODO Auto-generated method stub

		Set<Location> moves = new TreeSet<Location>();

		// check that one of the positions infront of a door is in the set.
		// for each door check if door is for the room currently in
		for (DoorTile dT : game.getDoors()) {
			if (dT.getRoomInitial()
					.equals(game.getTile(oldPosition).toString())) {
				// then the door then the door belongs to the same room we are
				// in
				Location loc = new Location(dT.getX() + dT.getxDir(), dT.getY()
						+ dT.getyDir());
				moves.addAll(game.getMovesTo(loc, diceRoll - 1));
			}
		}

		if (moves.contains(newPosition)) {
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cluedo.moves.MoveI#apply(cluedo.main.Game)
	 */
	@Override
	public void apply(Game game) {
		// TODO Auto-generated method stub

	}

}
