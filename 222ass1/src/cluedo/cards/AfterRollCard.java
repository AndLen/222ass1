package cluedo.cards;

import cluedo.main.Game;
import cluedo.structs.Player;


/**
 * represents a keepers card that can be played after a roll but before the move
 * (adding 6 to the dice)
 * @author Michael
 *
 */
public class AfterRollCard extends Keepers {

	private String description;

	public AfterRollCard(String s) {
		if (s.equalsIgnoreCase(Keepers.Cards[6])) {
			this.description = s;
		}
		else {
			throw new IllegalArgumentException(s + " is not the right card, should be: " + Keepers.Cards[6]);
		}
	}

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
		AfterRollCard other = (AfterRollCard) obj;
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
		return description;
	}

	@Override
	public String apply(Game game, Player player) {

		// this card is applied in the game class
		
		player.getKeeperCards().remove(this);
		return null;
	}
	
	@Override
	public String toString(){
		return description;
	}

}
