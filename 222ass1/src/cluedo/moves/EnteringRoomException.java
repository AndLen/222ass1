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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnteringRoomException(String string) {
		super(string);
	}

}
