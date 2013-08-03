package cluedo.structs;

import java.util.Random;

/**
 * Used to get a random dice roll. Included for later extension with a GUI (e.g.
 * Showing the dice).
 * 
 * @author Andrew
 * 
 */
public class Dice {
	Random r;

	public Dice() {
		r = new Random();
	}

	public int getRoll() {
		return r.nextInt(6) + 1; // Exclude 0 (we want 1=6 inclusive)
	}
}
