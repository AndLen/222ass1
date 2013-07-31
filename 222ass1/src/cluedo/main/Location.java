package cluedo.main;

/**
 * a pair of x and y coordinates
 * 
 * bad encapsulation
 * should make it easier to find where a player is at. 
 * @author Michael
 *
 */
public class Location {
	public int x;
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}


	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}


	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}


	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}


	public int y;
	
	
	public Location(Location l){
	this.x = l.x;
	this.y = l.y;
	}


	public Location() {
		// TODO Auto-generated constructor stub
	}
}
