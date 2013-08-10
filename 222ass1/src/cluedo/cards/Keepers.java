package cluedo.cards;

import cluedo.main.Game;
import cluedo.structs.Player;

public abstract class Keepers implements Card {

	//private final String description;
	public static final String[] Cards = {"Play at the end of your turn. The player on your right must show you a card."
		,"Play when starting a turn in a room. You may stay in that room and start a Rumor."
		,"Play instead of answering a Rumor. The Rumor stays unanswered."
		,"Play when one player has shown another player a card. You get to see the card."
		,"Play at the end of your turn. Move anyone back to their start space."
		,"Play instead of rolling the dice. Move anywhere."
		,"Play after you roll the dice but before you move. Add 6 to your dice roll."
		,"Play at the end of your turn. Take another turn."
	};
	
//	public Keepers(String s){
//		description = s;
//	}
	
	
	
	
	@Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	public abstract String getDescription();
	//	return description;
	
	@Override
	public abstract boolean equals(Object obj);

	public abstract int hashCode();

	@Override
	public abstract String apply(Game game, Player player);
	

}
