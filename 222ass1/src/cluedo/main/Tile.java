package cluedo.main;

public class Tile {
	private String stringRep;

	public Tile(String s){
		stringRep = s;
	}
	public void draw() {
		System.out.print(stringRep);

	}
	public String toString(){
		return stringRep;
	}

}
