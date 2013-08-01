package cluedo.main;

/**
 * Responsible for managing and running the game.
 * @author Andrew
 *
 */
public class Game {
	Board gameBoard;
	Player solution;
	public Game(Board b, Player s){
		gameBoard = b;
		solution = s;
	}
	public String boardToString() {
		return gameBoard.toString();
	}
	public void begin() {
		// TODO Auto-generated method stub
		
	}
}
