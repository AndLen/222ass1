package cluedo.main;

import java.util.List;
import java.util.Map;

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
	private final Board gameBoard;
	private final Solution solution;
	private final List<Card> cardsInPool;
	// Our human players.
	private final List<Player> players;
	/**
	 * The entry is the next player clockwise after the key player.
	 */
	private Map<Player,Player> nextPlayer;

	public Game(Board b, Solution s, List<Card> cardsLeft, List<Player> players) {
		gameBoard = b;
		solution = s;
		// All our leftover cards get put in the middle.
		cardsInPool = cardsLeft;
		this.players = players;
	}

	public void beginGame() {
		nextPlayer = getOrder();
		// TODO Auto-generated method stub

	}

	/**
	 * Constructs the order in which players take turns.
	 * @return
	 * TODO: Implement map return.
	 */
	private Map<Player, Player> getOrder() {
		int highestRoll = 0;
		Player highestRollPlayer = null;
		//Find who rolled the highest number
		for(Player p: players){
			int roll = (int)(1+Math.random()*6);
			System.out.println(p.getMyName() + " rolled a " + roll + "!");
			if(roll > highestRoll){
				highestRoll = roll;
				highestRollPlayer = p;
			}
		}
		System.out.println(highestRollPlayer.getMyName() + " goes first.");
		return null;
	}
	
	public Player nextPlayer(Player current){
		return nextPlayer.get(current);
	}

	public String boardToString() {
		return gameBoard.toString(this);
	}

	public final List<Player> getPlayers() {
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
