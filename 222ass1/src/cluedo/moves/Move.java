package cluedo.moves;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cluedo.main.Game;
import cluedo.structs.Location;
import cluedo.structs.Player;

/**
 * a move is when the player is in the corridors (i.e. not in a room)
 * 
 * use to get where player may move to, enter room, walk along corridor...
 * 
 * @author Michael
 * 
 */
public class Move implements MoveI {

	private Location oldPosition;
	private int diceRoll;
	private Location newPosition;

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
	public boolean isValid(Game game) /* throws CluedoException */{
		// TODO
		// can we jump over a player? i know they can't when they are blocking a
		// door, but can they if the are in hallway

		Set<Location> moves = game.getMovesTo(oldPosition, diceRoll);
		// printMoves(moves);

		for (Player p : game.getPlayers()) {

			if (p.getLocation().equals(newPosition)) {

				// player cannot land on another player
				return false;
			}
		}

		if ((game.isRoomLocation(oldPosition) || game
				.isDoorLocation(oldPosition))
				&& (game.isRoomLocation(newPosition) || game
						.isDoorLocation(newPosition))) {

			move = new Passage(oldPosition, newPosition, diceRoll, game);
			return move.isValid(game);
		}

		if (game.isRoomLocation(newPosition)
				|| game.isDoorLocation(newPosition)) {
			// throw new EnteringRoomException(
			// "move is potentially valid but needs to be an EnterMove");
			move = new Enter(oldPosition, newPosition, diceRoll, game);
			return move.isValid(game);
		}

		if (game.isCorridorLocation(newPosition)
				&& (game.isDoorLocation(oldPosition) || game
						.isRoomLocation(oldPosition))) {

			move = new Exit(oldPosition, newPosition, diceRoll, game);
			
			return move.isValid(game);
		}

		if (moves.contains(newPosition)) {
			// a door should also counts as a roomlocation

			// should check that path doesn't involve walking through walls
			// if only one six sided dice can only occur when crossing the tip
			// of the Living room
			// or crossing the width of the theater. not entirely sure how to do
			// this, will likley include keeping track of the path and checking
			// that they are rooms or doors (when the newPos is a corridor) this
			// may mean i need to reimplement the recursive getMovesTo trick
			// List<Location> path = new ArrayList<Location>();

			return true;
		}

		return false;
	}

	/**
	 * 
	 * @return the distance between the 2 points
	 */
	private int distanceBetween() {

		return Math.abs(newPosition.getX() - oldPosition.getX())
				+ Math.abs(newPosition.getY() - oldPosition.getY());
	}

	private void printMoves(Set<Location> moves) {
		boolean a[][] = new boolean[26][26];
		for (Location l : moves) {
			a[l.getX()][l.getY()] = true;
		}
		for (boolean[] ba : a) {
			for (boolean b : ba) {
				if (b) {
					System.out.print("x");
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}

	@Override
	public void apply(Game game) {
		game.changePlayerLocation(oldPosition, newPosition);

	}

}
