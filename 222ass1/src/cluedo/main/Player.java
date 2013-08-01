package cluedo.main;

import cluedo.cards.*;
import cluedo.cards.Character;

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
	
	/* Cards that player has. Assumes can only hold one of each for now. */
	private Character myChar;
	private Room myRoomCard;
	private Weapon myWeap;
	
	public Player(Location start, Character c, Room r, Weapon w) {
		myPosition = start;
		myChar = c;
		myRoomCard = r;
		myWeap = w;
	}

	public Character getMyChar() {
		return myChar;
	}

	public void setMyChar(Character myChar) {
		this.myChar = myChar;
	}

	public Room getMyRoomCard() {
		return myRoomCard;
	}

	public void setMyRoomCard(Room myRoomCard) {
		this.myRoomCard = myRoomCard;
	}

	public Weapon getMyWeap() {
		return myWeap;
	}

	public void setMyWeap(Weapon myWeap) {
		this.myWeap = myWeap;
	}

	public void updatePosition(Location newPos) {
		myPosition = newPos;
	}

	public Location getLocation() {
		return myPosition;
	}
}
