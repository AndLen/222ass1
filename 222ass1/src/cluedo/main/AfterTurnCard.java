package cluedo.main;

import cluedo.structs.Player;
//import cluedo.cards.*;
import cluedo.cards.Character;

public class AfterTurnCard extends Keepers {

	private String description;

	public AfterTurnCard(String s) {
		description = s;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public boolean apply(Game game, Player player) {

		if (description.equals(Keepers.Cards[0])) {
			// player on right show you card.
			game.playerShowCard(game.getPlayerToPrevoiusPlayer().get(player));
			return true;
		}
		if (description.equals(Keepers.Cards[4])) {
			// move anyone back to their starting position
			Character c = game.getCharcterInput();

			// gets the above player and moves back to their starting position
			game.getPlayers()
					.get(game.getPlayers().indexOf(c.toString()))
					.updateLocation(
							game.getPlayers()
									.get(game.getPlayers()
											.indexOf(c.toString()))
									.getStarting());

			return true;
		}

		return false;
	}

}
