package cluedo.structs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cluedo.cards.Card;
import cluedo.cards.Keepers;

/**
 * Represents a human player in the game. Should contain their current location,
 * as well as their own cards.
 * 
 * 
 * @author Andrew & Michael
 * 
 */
public class Player {
	private Location myPosition;
	private final String myName;
	private final String mySymbol;
	private final Location starting;
	
	
	private List<Keepers> keeperCards = new ArrayList<Keepers>();

	private List<Card> myCards;

	public String getMyName() {
		return myName;
	}

	public String getMySymbol() {
		return mySymbol;
	}

	public Player(Location start, String myName, String mySymbol,
			List<Card> cards) {
		this.myName = myName;
		this.mySymbol = mySymbol;
		myPosition = start;
		starting = start;
		myCards = cards;
	}

	public void updateLocation(Location newPos) {
		myPosition = newPos;
	}

	public Location getLocation() {
		return myPosition;
	}

	/**
	 * Use me to check if I have certain cards.
	 * 
	 * @return
	 */
	public Iterator<Card> myCardsIterator() {
		return myCards.iterator();
	}
	
	/**
	 * String representation of the cards the player holds.
	 * @return
	 */
	public String myCards(){
		String s = "";
		for(Card c: myCards){
			s+=c.toString() + ", ";
		}
		return s;
	}

	/**
	 * String rep of the keeper cards.
	 * @return
	 */
	public String myKeeperCards(){
		if(keeperCards.size() == 0){
			return "no";
		}
		String s = "";
		for(Card c: keeperCards){
			s+=c.toString() + ", ";
		}
		return s;
	}
	public static Location startLocation(String charName) {
		if (charName.equals("Kasandra Scarlett")) {
			return new Location(18, 28);
		} else if (charName.equals("Jack Mustard")) {
			return new Location(7, 28);
		} else if (charName.equals("Diane White")) {
			return new Location(0, 19);
		} else if (charName.equals("Jacob Green")) {
			return new Location(0, 9);
		} else if (charName.equals("Eleanor Peacock")) {
			return new Location(6, 0);
		} else if (charName.equals("Victor Plum")) {
			return new Location(20, 0);
		}
		throw new IllegalArgumentException("Invalid Char name");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myName == null) ? 0 : myName.hashCode());
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
		Player other = (Player) obj;
		if (myName == null) {
			if (other.myName != null)
				return false;
		} else if (!myName.equals(other.myName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getMyName();
	}

	public Location getStarting() {
		return starting;
	}

	/**
	 * @return the keeperCards
	 */
	public List<Keepers> getKeeperCards() {
		return keeperCards;
	}
	
	/**
	 * @return mycards
	 */
	public List<Card> getMyCards(){
		return myCards;
	}
}
