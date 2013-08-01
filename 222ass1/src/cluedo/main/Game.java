package cluedo.main;

import java.util.List;

import cluedo.cards.Card;
import cluedo.structs.Solution;

/**
 * Responsible for managing and running the game.
 * @author Andrew
 *
 */
public class Game {
	Board gameBoard;
	Solution solution;
	List<Card> cardsInPool;
	public Game(Board b, Solution s, List<Card> cardsLeft){
		gameBoard = b;
		solution = s;
		//All our leftover cards get put in the middle.
		cardsInPool = cardsLeft;
	}
	public String boardToString() {
		return gameBoard.toString();
	}
	public void begin() {
		// TODO Auto-generated method stub
		
	}
}
