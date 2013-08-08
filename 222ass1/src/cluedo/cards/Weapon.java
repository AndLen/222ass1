package cluedo.cards;

/**
 * 
 * @author Michael
 * 
 */
public class Weapon implements Card {
	public static enum WEAPONS {
		Rope, Candlestick, Knife, Pistol, Baseball_Bat, Dumbbell, Trophy, Poison, Axe
	};

	public static String[] WEAPONS = { "Rope", "Candlestick", "Knife",
			"Pistol", "Baseball Bat", "Dumbbell", "Trophy", "Poison", "Axe" };
	private String weapon;

	/**
	 * 
	 * @param i
	 *            the number the card corresponds to from static WEAPONS[]
	 */
	public Weapon(String s) {

		// Check valid input.
		for (String name : WEAPONS) {
			if (s.equalsIgnoreCase(name)) {
				this.weapon = name;
				return;
			}
		}
		throw new IllegalArgumentException(s + " is not a weapon");
	}

	@Override
	public String toString() {
		return weapon;
	}

	@Override
	/**
	 * 
	 * 	
	 * @return the first letter (Lowercase) and the 2nd letter ()Uppercase 
	 */
	public String getSymbol() {

		return java.lang.Character.toLowerCase(weapon.charAt(0)) + ""
				+ java.lang.Character.toUpperCase(weapon.charAt(1));
	}

}
