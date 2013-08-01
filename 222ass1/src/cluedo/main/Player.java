package cluedo.main;

import cluedo.cards.*;

/**
 * Represents a human player in the game. Should contain their current location,
 * as well as their own cards.
 * 
 * TODO: Put an interface in for this I guess.
 * 
 * @author Andrew
 * 
 */
public class Player {
	private Location myPosition;
	private final CharacterI myName;

	/* Cards that player has. Assumes can only hold one of each for now. */
	private final CharacterI myCharCard;
	private final RoomI myRoomCard;
	private final WeaponI myWeapCard;

	public String getMyName() {
		return myName.getCard();
	}

	public String getMySymbol(){
		return myName.getSymbol();
	}
	public Player(Location start, CharacterI myName, CharacterI characterI, RoomI roomI,
			WeaponI weaponI) {
		this.myName = myName;
		myPosition = start;
		myCharCard = characterI;
		myRoomCard = roomI;
		myWeapCard = weaponI;
	}

	public CharacterI getMyChar() {
		return myCharCard;
	}

	public RoomI getMyRoomCard() {
		return myRoomCard;
	}

	public WeaponI getMyWeap() {
		return myWeapCard;
	}

	public void updatePosition(Location newPos) {
		myPosition = newPos;
	}

	public Location getLocation() {
		return myPosition;
	}

	public static Location startLocation(CharacterI charCard) {
		String charName = charCard.getCard();
		// TODO: Can we use enums here? Would be prettier}
		if (charName.equals("Kasandra Scarlett")) {
			return new Location(28, 18);
		} else if (charName.equals("Jack Mustard")) {
			return new Location(28,7);
		} else if (charName.equals("Diane White")) {
			return new Location(19,0);
		} else if (charName.equals("Jacob Green")) {
			return new Location(9,0);
		} else if (charName.equals("Eleanor Peacock")) {
			return new Location(0,6);
		} else if (charName.equals("Victor Plum")) {
			return new Location(0,20);
		}
		throw new IllegalArgumentException("Invalid Char name");
	}
}
