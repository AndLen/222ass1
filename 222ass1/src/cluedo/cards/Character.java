package cluedo.cards;

/**
 * A Character card. This is a card limited to representing the characters that
 * are possible to be obtained. These are also the same as the possible player
 * names, but the distinction should be made from the cluedo.structs.Player
 * class.
 * 
 * @author Andrew & Michael
 * 
 */
public class Character implements Card {

	public static String[] CHARACTERS = { "Kasandra Scarlett", "Jack Mustard",
			"Diane White", "Jacob Green", "Eleanor Peacock", "Victor Plum" };

	public static enum CHARACTERS {
		Kasandra_Scarlett, Jack_Mustard, Diane_White, Jacob_Green, Eleanor_Peacock, Victor_Plum
	};

	private String character;

	public Character(String charName) {
		// Check valid input - gotta have one of our recognised names!
		for (String name : CHARACTERS) {
			if (charName.equalsIgnoreCase(name)) {
				//Use our titlecase name as the variable, not their dodgy input.
				this.character = name;
				return;
			}
		}
		throw new IllegalArgumentException(charName + " is not a character");
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