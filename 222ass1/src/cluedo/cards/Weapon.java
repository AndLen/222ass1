package cluedo.cards;

public class Weapon implements WeaponI {

	private String weapon;

	/**
	 * 
	 * @param i
	 *            the number the card corresponds to from static WEAPONS[]
	 */
	public Weapon(String s) {
		this.weapon = s;
	}

	@Override
	public String getCard() {
		// TODO Auto-generated method stub
		return null;
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
