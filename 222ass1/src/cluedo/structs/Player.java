package cluedo.structs;

import java.util.List;

import cluedo.cards.*;

/**
 * Represents a human player in the game. Should contain their current location,
 * as well as their own cards.
 * 
 * TODO: Put an interface in for this I guess.
 * 
 * @author Andrew
 * 
 */
public class Player {
	private Location myPosition;
	private final String myName;
	private final String mySymbol;

	/* Cards that player has. Assumes can only hold one of each for now. */
	private List<Card> myCards;

	public String getMyName() {
		return myName;
	}

	public String getMySymbol(){
		return mySymbol;
	}
	public Player(Location start, String myName,String mySymbol, List<Card> cards) {
		this.myName = myName;
		this.mySymbol = mySymbol;
		myPosition = start;
		myCards = cards;
	}

	public void updatePosition(Location newPos) {
		myPosition = newPos;
	}

	public Location getLocation() {
		return myPosition;
	}

	public static Location startLocation(String charName) {
		// TODO: Can we use enums here? Would be prettier}
		if (charName.equals("Kasandra Scarlett")) {
			return new Location(28, 18);
		} else if (charName.equals("Jack Mustard")) {
			return new Location(28,7);
		} else if (charName.equals("Diane White")) {
			return new Location(19,0);
		} else if (charName.equals("Jacob Green")) {
			return new Location(9,0);
		} else if (charName.equals("Eleanor Peacock")) {
			return new Location(0,6);
		} else if (charName.equals("Victor Plum")) {
			return new Location(0,20);
		}
		throw new IllegalArgumentException("Invalid Char name");
	}
}
