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
 * @author Andrew & Michael
 * 
 */
public class Exit extends Move {

	/**
	 * 
	 * @param oldPosition
	 * @param newPosition
	 * @param diceRoll how many squares can the player move
	 * @param game
	 */
	public Exit(Location oldPosition, Location newPosition, int diceRoll,
			Game game) {
		super(oldPosition, newPosition, diceRoll, game);

	}

	@Override
	public boolean isValid(Game game) {

		Set<Location> moves = new TreeSet<Location>();

		// check that one of the positions infront of a door is in the set.
		// for each door check if door is for the room currently in

		for (DoorTile dT : game.getDoors()) {
			if (game.inParticularRoomLocation(oldPosition, dT.roomRep())) {
				// then the door then the door belongs to the same room we are
				// in
				Location loc = new Location(dT.getX() + dT.getxDir(), dT.getY()
						+ dT.getyDir());
				moves.addAll(game.getMovesTo(loc, diceRoll - 1));
			}
		}
	//	printMoves(moves);
		if (moves.contains(newPosition)) {
			return true;
		}
	//	printMoves(moves);
		return false;
	}

}
