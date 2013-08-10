package cluedo.cards;

import cluedo.main.Game;
import cluedo.structs.Player;

/**
 * 
 * @author Andrew & Michael
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

	@Override
	public String apply(Game game, Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((weapon == null) ? 0 : weapon.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Weapon other = (Weapon) obj;
		if (weapon == null) {
			if (other.weapon != null)
				return false;
		} else if (!weapon.equals(other.weapon))
			return false;
		return true;
	}

}
