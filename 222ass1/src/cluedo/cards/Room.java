package cluedo.cards;

/**
 * The physical room cards in the cluedo game. These are distinct from the
 * RoomTiles, although one must be on the correct RoomTile to suggest the
 * corresponding room card!
 * 
 * @author Andrew & Michael
 * 
 */
public class Room implements Card {

	public static enum ROOMS {
		Spa, Theatre, Living_Room, Observatory, Patio, Hall, Kitchen, Dining_Room, Guest_House
	};

	public static String[] ROOMS = { "Spa", "Theatre", "Living Room",
			"Observatory", "Patio", "Hall", "Kitchen", "Dining Room",
			"Guest House" };
	private String room;

	public Room(String s) {
		// Check valid input.
		for (String name : ROOMS) {
			if (s.equalsIgnoreCase(name)) {
				// Use our titlecase name as the variable, not their dodgy
				// input.
				this.room = name;
				return;
			}
		}
		throw new IllegalArgumentException(s + " is not a room");

	}

	@Override
	public String toString() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((room == null) ? 0 : room.hashCode());
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
		Room other = (Room) obj;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}

}
