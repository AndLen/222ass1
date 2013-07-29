package cluedo.cards;

public class Room implements RoomI {

	private String card;
	
	
	public Room(int i){
		this.card = RoomI.ROOMS[i];
		
	}
	
	
	
	@Override
	public String getCard() {
		// TODO Auto-generated method stub
		return null;
	}

}
