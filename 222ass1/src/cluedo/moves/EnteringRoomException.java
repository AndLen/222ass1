/**
 * 
 */
package cluedo.moves;

/**
 * 
 * thrown in Move class, the idea was to catch and then make a new Enter move
 * which will check if a door was used and didnt walk through a wall
 * 
 * @author Michael
 * 
 */
public class EnteringRoomException extends CluedoException {

	public EnteringRoomException(String string) {
		super(string);
	}

}
