package cluedo.main;

import cluedo.structs.Player;

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
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public boolean apply(Game game, Player player) {
		// TODO Auto-generated method stub

		// this card is applied in the game class
		return true;
	}

}
