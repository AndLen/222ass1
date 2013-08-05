package cluedo.cards;

import java.util.Arrays;

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

	private String card;

	public Character(String s) {
		if(!Arrays.asList(CHARACTERS).contains(s)){
			throw new IllegalArgumentException(s + " is not a character");
		}
		this.card = s;

	}

	@Override
	public String toString() {
		return card;
	}

	@Override
	/**
	 * @return the first letter and the letter after the underscore converted to lower case
	 */
	public String getSymbol() {
		return card.charAt(0)
				+ ""
				+ java.lang.Character.toLowerCase(card.charAt(card
						.lastIndexOf(" ") + 1));
	}

}