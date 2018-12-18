package controller;
import java.io.Serializable;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


/**
 * The Class Player represents all informations usefull about the actuals players of the game  .
 */
public class Player implements Serializable {

	/** The id. If ID is one then the players is the humain playing,others are bots  */
	private int ID;
	
	/** The color , each player is characterize by one specific color ( Player One is LIGHTBLUE). */
	transient private Paint color;
	
	/**
	 * Instantiates a new player and apply the selected color to the current model .
	 *
	 * @param id the id of the player
	 */
	public Player(int id) {
		this.ID = id;
		switch (ID) {
			case 0:
				this.color = Color.YELLOW;
				break;
			case 1:
				this.color = Color.LIGHTBLUE;
				break;
			case 2:
				this.color = Color.HOTPINK;
				break;
			case 3:
				this.color = Color.LIGHTSLATEGREY;
				break;
		}
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID() { return ID; }
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setID(int id) { ID = id; }

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Paint getColor() { return color; }
	
	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(Paint color) { this.color = color; }
}
