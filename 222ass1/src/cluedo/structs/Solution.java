package cluedo.structs;

import cluedo.cards.Character;
import cluedo.cards.Room;
import cluedo.cards.Weapon;

/**
 * A set of cards believed to be the solution to the game!
 * 
 * @author Andrew
 * 
 */

public class Solution {

	private final Character charSol;
	private final Room roomSol;
	private final Weapon weaponSol;

	public Solution(Character c, Room r, Weapon w) {
		charSol = c;
		roomSol = r;
		weaponSol = w;
	}

	public Character getCharSol() {
		return charSol;
	}

	public Room getRoomSol() {
		return roomSol;
	}

	public Weapon getWeaponSol() {
		return weaponSol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((charSol == null) ? 0 : charSol.hashCode());
		result = prime * result + ((roomSol == null) ? 0 : roomSol.hashCode());
		result = prime * result
				+ ((weaponSol == null) ? 0 : weaponSol.hashCode());
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
		Solution other = (Solution) obj;
		if (charSol == null) {
			if (other.charSol != null)
				return false;
		} else if (!charSol.getCard().equals(other.charSol.getCard()))
			return false;
		if (roomSol == null) {
			if (other.roomSol != null)
				return false;
		} else if (!roomSol.getCard().equals(other.roomSol.getCard()))
			return false;
		if (weaponSol == null) {
			if (other.weaponSol != null)
				return false;
		} else if (!weaponSol.getCard().equals(other.weaponSol.getCard()))
			return false;
		return true;
	}
}
