package cluedo.cards;

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
		//Check valid input.
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

}