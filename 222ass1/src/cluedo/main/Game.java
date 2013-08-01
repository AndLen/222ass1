package cluedo.main;

import java.util.List;

import cluedo.cards.Card;
import cluedo.structs.Location;
import cluedo.structs.Player;
import cluedo.structs.Solution;
import cluedo.tiles.CorridorTile;
import cluedo.tiles.DoorTile;
import cluedo.tiles.RoomTile;

/**
 * Responsible for managing and running the game.
 * 
 * @author Andrew
 * 
 */
public class Game {
	private Board gameBoard;
	private Solution solution;
	private List<Card> cardsInPool;
	// Our human players.
	private List<Player> players;

	public Game(Board b, Solution s, List<Card> cardsLeft, List<Player> players) {
		gameBoard = b;
		solution = s;
		// All our leftover cards get put in the middle.
		cardsInPool = cardsLeft;
		this.players = players;
	}

	public void beginGame() {
		// TODO Auto-generated method stub

	}

	public String boardToString() {
		return gameBoard.toString(this);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public List<Location> getMovesTo(Location oldPosition, int diceRoll) {
		return gameBoard.getMovesTo(oldPosition, diceRoll);
	}

	public boolean isCorridorLocation(Location newPosition) {
		return (gameBoard.tileAtLocation(newPosition) instanceof CorridorTile);
	}

	public boolean isRoomLocation(Location newPosition) {
		return (gameBoard.tileAtLocation(newPosition) instanceof RoomTile);
	}

	public boolean isDoorLocation(Location newPosition) {
		return (gameBoard.tileAtLocation(newPosition) instanceof DoorTile);
	}

}
