package cluedo.cards;

public class Clocks implements Card {

	public static String s = "Tock";
	
	@Override
	public String getSymbol() {
		
		
		return s.substring(0, 1);
	}

	
	@Override
	public String toString() {
		if(s.equals("Tick")){
			s = "Tock";
		}
		else {
			s = "Tick";
		}
		
		return s;
	}
}
