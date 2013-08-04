package cluedo.cards;

import java.util.Arrays;

/**
 * 
 * @author Michael
 * 
 */
public class Room implements Card {
	public static enum ROOMS {
		Spa, Theatre, Living_Room, Conservatory, Piano, Hall, Kitchen, Dining_Room, Guest_House
	};

	public static String[] ROOMS = { "Spa", "Theatre", "Living Room",
			"Conservatory", "Patio", "Hall", "Kitchen", "Dining Room",
			"Guest House" };
	private String room;

	public Room(String s) {
		if(!Arrays.asList(ROOMS).contains(s)){
			throw new IllegalArgumentException(s + " is not a room");
		}
		this.room = s;

	}

	@Override
	public String getCard() {
		return room;
	}

	@Override
	/**
	 * @ retrun the first 2 letters capitalised
	 */
	public String getSymbol() {
		return room.charAt(0) + ""
				+ java.lang.Character.toUpperCase(room.charAt(1));
	}

}
