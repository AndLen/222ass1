package cluedo.structs;

import cluedo.cards.CharacterI;
import cluedo.cards.RoomI;
import cluedo.cards.WeaponI;

/**
 * The solution to win the game.
 * 
 * @author Andrew
 * 
 */

public class Solution {

	private final CharacterI charSol;
	private final RoomI roomSol;
	private final WeaponI weaponSol;

	public Solution(CharacterI c, RoomI r, WeaponI w) {
		charSol = c;
		roomSol = r;
		weaponSol = w;
	}

	public CharacterI getCharSol() {
		return charSol;
	}

	public RoomI getRoomSol() {
		return roomSol;
	}

	public WeaponI getWeaponSol() {
		return weaponSol;
	}
}
