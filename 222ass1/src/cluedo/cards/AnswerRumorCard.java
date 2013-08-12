package cluedo.cards;

import cluedo.main.Game;
import cluedo.structs.Player;

/**
 * this class is not used in the game
 * 
 * represents a keepers card that can be played when someone is answering a rumor
 * (lets 'player' see theat card)
 * @author Michael
 *
 */
public class AnswerRumorCard extends Keepers {
	String description;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnswerRumorCard other = (AnswerRumorCard) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String apply(Game game, Player player) {
		// TODO Auto-generated method stub
		player.getKeeperCards().remove(this);
		return null;
	}

}
