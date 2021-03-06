package cluedo.moves;

import cluedo.main.Game;
import cluedo.structs.Location;

/**
 * A secret passage from one room to another - can only be used if we're already
 * in the room.
 * 
 * @author Andrew & Michael
 * 
 */
public class Passage extends Move {

	public Passage(Location oldPosition, Location newPosition, int diceRoll,
			Game game) {
		super(oldPosition, newPosition, diceRoll, game);
	}

	@Override
	public boolean isValid(Game game) {

		if (game.isRoomLocation(oldPosition)) {
			if (game.isRoomLocation(newPosition)) {

				if (game.inParticularRoomLocation(oldPosition, "SP")
						&& game.inParticularRoomLocation(newPosition, "GU")) {
					return true;
				} else if (game.inParticularRoomLocation(oldPosition, "GU")
						&& game.inParticularRoomLocation(newPosition, "SP")) {
					return true;
				} else if (game.inParticularRoomLocation(oldPosition, "KI")
						&& game.inParticularRoomLocation(newPosition, "OB")) {
					return true;
				} else if (game.inParticularRoomLocation(oldPosition, "OB")
						&& game.inParticularRoomLocation(newPosition, "KI")) {
					return true;
				}

			}
		}

		return false;
	}

}
