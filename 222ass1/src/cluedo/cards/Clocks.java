package cluedo.cards;

import cluedo.main.Game;
import cluedo.structs.Player;

/**
 * A special type of Keeper card that does nothing for the first 7 clock cards,
 * but kills the person who draws the 8th one! Alternates between printing tick
 * and tock as the game proceeds.
 * 
 * @author Andrew
 * 
 */
public class Clocks extends Keepers {

	// Static as should be shared across all Clock cards (so it varies tick <-->
	// tock)
	public static String s = "TOCK";

	@Override
	public String getSymbol() {
		return s.substring(0, 1);
	}

	@Override
	public String toString() {
		if (s.equals("TICK")) {
			s = "TOCK";
		} else {
			s = "TICK";
		}

		return s;
	}

	@Override
	public String apply(Game game, Player player) {
		// Done in the game method.
		return null;
	}

	@Override
	public String getDescription() {
		return s;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null & obj instanceof Clocks;
	}

	@Override
	public int hashCode() {
		// Doesn't need to change.
		return 0;
	}
}
