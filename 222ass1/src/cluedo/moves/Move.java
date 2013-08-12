package cluedo.moves;

import java.util.Set;

import cluedo.main.Game;
import cluedo.structs.Location;
import cluedo.structs.Player;

/**
 * a move is when the player is in the corridors (i.e. not in a room)
 * 
 * use to get where player may move to, enter room, walk along corridor...
 * 
 * @author Andrew & Michael
 * 
 */
public class Move implements MoveI {

	protected Location oldPosition;
	protected int diceRoll;
	protected Location newPosition;

	private MoveI move; // move if not a simple corridor to corridor move.

	/**
	 * 
	 * @param oldPosition
	 *            the tile to be moved from
	 * @param newPosition
	 *            the tile to be moved to
	 * @param diceRoll
	 *            the number rolled on the dice
	 */
	public Move(Location oldPosition, Location newPosition, int diceRoll,
			Game game) {
		this.oldPosition = oldPosition;
		this.newPosition = newPosition;
		this.diceRoll = diceRoll;

	}

	@Override
	public boolean isValid(Game game) {

		// Possible locations the player can reach with his/her roll.
		for (Player p : game.getPlayers()) {
			if (p.getLocation().equals(newPosition)) {

				// player cannot land on another player
				return false;
			}
		}

		if (game.isRoomLocation(oldPosition)
				&& game.isRoomLocation(newPosition)) {
			// Must be moving through a passage or otherwise illegal

			move = new Passage(oldPosition, newPosition, diceRoll, game);
			return move.isValid(game);
		}

		else if (game.isRoomLocation(newPosition)) {
			// Must be entering a room from a corridor.
			move = new Enter(oldPosition, newPosition, diceRoll, game);
			return move.isValid(game);
		}

		else if (game.isCorridorLocation(newPosition)
				&& game.isRoomLocation(oldPosition)) {
			// Must be leaving a room
			move = new Exit(oldPosition, newPosition, diceRoll, game);
			// printMoves(moves);
			return move.isValid(game);
		}
		Set<Location> moves = game.getMovesTo(oldPosition, diceRoll);
		if (moves.contains(newPosition)) {
			// Otherwise just a corridor move.

			// a door should also counts as a roomlocation
			return true;
		}
		return false;
	}

	@Override
	public void apply(Game game) {
		game.changePlayerLocation(oldPosition, newPosition);

	}

}
