package cluedo.cards;

import cluedo.main.Game;
import cluedo.structs.Player;

/**
 * 
 * @author Michael
 * 
 */
public class Character implements Card {

	public static String[] CHARACTERS = { "Kasandra Scarlett", "Jack Mustard",
			"Diane White", "Jacob Green", "Eleanor Peacock", "Victor Plum" };

	public static enum CHARACTERS {
		Kasandra_Scarlett, Jack_Mustard, Diane_White, Jacob_Green, Eleanor_Peacock, Victor_Plum
	};

	private String character;

	public Character(String s) {
		// Check valid input.
		for (String name : CHARACTERS) {
			if (s.equalsIgnoreCase(name)) {
				this.character = name;
				return;
			}
		}
		throw new IllegalArgumentException(s + " is not a character");
	}

	@Override
	public String toString() {
		return character;
	}

	@Override
	/**
	 * @return the first letter and the letter after the underscore converted to lower case
	 */
	public String getSymbol() {
		return character.charAt(0)
				+ ""
				+ java.lang.Character.toLowerCase(character.charAt(character
						.lastIndexOf(" ") + 1));
	}

	@Override
	public boolean apply(Game game, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((character == null) ? 0 : character.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		if (character == null) {
			if (other.character != null)
				return false;
		} else if (!character.equals(other.character))
			return false;
		return true;
	}
}