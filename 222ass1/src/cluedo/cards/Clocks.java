package cluedo.cards;

import cluedo.main.Game;
import cluedo.main.Keepers;
import cluedo.structs.Player;

public class Clocks extends Keepers {

	public static String s = "Tock";
	
	@Override
	public String getSymbol() {
		
		
		return s.substring(0, 1);
	}

	
	@Override
	public String toString() {
		if(s.equals("Tick")){
			s = "Tock";
		}
		else {
			s = "Tick";
		}
		
		return s;
	}


	@Override
	public boolean apply(Game game, Player player) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String getDescription() {
	
		return s;
	}
}
