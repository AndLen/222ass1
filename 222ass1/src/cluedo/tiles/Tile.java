package cluedo.tiles;

public abstract class Tile {
	private String stringRep;
	
	
	public Tile(String s) {
		stringRep = s;
	}

	public Tile(String s, int x, int y) {
		stringRep = s;
	}

	public void draw() {
		System.out.print(stringRep);

	}

	@Override
	public String toString() {
		return stringRep;
	}

}
