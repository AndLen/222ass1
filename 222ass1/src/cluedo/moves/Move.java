package cluedo.moves;

import java.util.List;

import cluedo.main.Game;
import cluedo.structs.Location;
import cluedo.structs.Player;

/**
 * a move is when the player is in the coridors (i.e. not in a room)
 * 
 * use to get where player may move to, enter room, walk along corridor...
 * 
 * @author Michael
 * 
 */
public class Move implements MoveI {

	private int x;
	private int y;
	private Location oldPosition;
	private int diceRoll;
	private Location newPosition;

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
	 * @param oldPosition
	 *            the tile to be moved from
	 * @param newPosition the tile to be moved to
	 * @param diceRoll
	 *            the number rolled on the dice
	 */
	public Move(Location oldPosition, Location newPosition, int diceRoll,
			Game game) {
		if (!game.isDoorLocation(oldPosition)) {
			throw new IllegalArgumentException(
					"oldPosition must be in a corridor");
		}
		this.oldPosition = oldPosition;
		this.newPosition = newPosition;
		this.diceRoll = diceRoll;
	}

	@Override
	public boolean isValid(Game game) throws CluedoException {
		// TODO
		// can we jump over a player? i know they cant when they are blocking a
		// door, but can they if the are in hallway

		List<Location> moves = game.getMovesTo(oldPosition, diceRoll);
		for (Player p : game.getPlayers()) {
			if (p.getLocation().equals(newPosition)) {

				// player cannot land on another player
				return false;
			}
		}

		if (moves.contains(newPosition)) {
			if (game.isRoomLocation(newPosition)) {
				throw new EnteringRoomException(
						"move is potentially valid but needs to be an EnterMove");
			}

			return true;
		}

		return false;
	}

	@Override
	public void apply(Game game) {
		// TODO Auto-generated method stub

	}

}
