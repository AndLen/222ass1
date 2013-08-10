package cluedo.cards;

import cluedo.main.Game;
import cluedo.structs.Player;

/**
 * 
 * @author Michael
 * 
 */
public interface Card {

	/**
	 * 
	 * @return symbolic representation of the ascii board
	 */
	public String getSymbol();

	/**
	 * 
	 * @param player TODO
	 * @return false if card cannot be applied(at the current time or applying
	 *         does nothing). true if successfully applied.
	 */
	public String apply(Game game, Player player);

	
	
}
