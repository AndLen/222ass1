package cluedo.main;

import cluedo.structs.Player;
import cluedo.cards.Card;
//import cluedo.cards.*;
import cluedo.cards.Character;

public class AfterTurnCard extends Keepers {

	private String description;

	public AfterTurnCard(String s) {
		if (!(s.equalsIgnoreCase(Keepers.Cards[0])
				|| s.equalsIgnoreCase(Keepers.Cards[4]) || s
					.equalsIgnoreCase(Keepers.Cards[7]))) {
			throw new IllegalArgumentException(s + " is not an aproriate card");
		}

		description = s;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public String toString() {
		return description;
	}

	@Override
	public boolean apply(Game game, Player player) {

		if (description.equals(Keepers.Cards[0])) {
			// player on right show you card.
			Card card = game.playerShowCard(game.getPlayerToPrevoiusPlayer()
					.get(player));
			System.out.println(card.toString());
			return true;
		}
		if (description.equals(Keepers.Cards[4])) {
			// move anyone back to their starting position
			Character c = game.getCharcterInput();

			// gets the above Character and moves back to their starting
			// position
			game.getPlayers()
					.get(game.getPlayers().indexOf(c.toString()))
					.updateLocation(
							game.getPlayers()
									.get(game.getPlayers()
											.indexOf(c.toString()))
									.getStarting());

			return true;
		}

		if (description.equals(Keepers.Cards[7])) {
			// take another turn

			// TODO if player dies we need to kill them
			return game.takeTurn(player);

		}

		return false;
	}

}
