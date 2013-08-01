package cluedo.main;

import java.util.List;

import cluedo.cards.Card;
import cluedo.structs.Player;
import cluedo.structs.Solution;

/**
 * Responsible for managing and running the game.
 * 
 * @author Andrew
 * 
 */
public class Game {
	Board gameBoard;
	Solution solution;
	List<Card> cardsInPool;
	// Our human players.
	private List<Player> players;

	public Game(Board b, Solution s, List<Card> cardsLeft, List<Player> players) {
		gameBoard = b;
		solution = s;
		// All our leftover cards get put in the middle.
		cardsInPool = cardsLeft;
		this.players = players;
	}

	public String boardToString() {
		return gameBoard.toString(this);
	}

	public void beginGame() {
		// TODO Auto-generated method stub

	}

	public List<Player> getPlayers(){
		return players;
	}
	
}
