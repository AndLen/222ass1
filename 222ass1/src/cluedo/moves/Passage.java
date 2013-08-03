package cluedo.moves;

import cluedo.main.Game;
import cluedo.structs.Location;

public class Passage implements MoveI {

	private Location oldPosition;
	private int diceRoll;
	private Location newPosition;
	
	
	public Passage(Location oldPosition, Location newPosition, int diceRoll, Game game) {
		super();
		this.oldPosition = oldPosition;
		this.diceRoll = diceRoll;
		this.newPosition = newPosition;
	}

	@Override
	public boolean isValid(Game game) throws CluedoException {
		// TODO Auto-generated method stub
		
		if(game.isRoomLocation(oldPosition) || game.isDoorLocation(oldPosition)){
			if(game.isRoomLocation(newPosition) || game.isDoorLocation(newPosition)){
				
				if(game.inParticularRoomLocation(oldPosition, "SP") && game.inParticularRoomLocation(newPosition, "GU")){
					return true;
				}else if(game.inParticularRoomLocation(oldPosition, "GU") && game.inParticularRoomLocation(newPosition, "SP")){
					return true;
				}else if(game.inParticularRoomLocation(oldPosition, "KI") && game.inParticularRoomLocation(newPosition, "OB")){
					return true;
				}else if(game.inParticularRoomLocation(oldPosition, "OB") && game.inParticularRoomLocation(newPosition, "KI")){
					return true;
				}
				
				
			}
		}
		
		return false;
	}

	@Override
	public void apply(Game game) {
		// TODO Auto-generated method stub

	}

}
