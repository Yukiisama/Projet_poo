package controller;
import java.io.Serializable;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

// TODO: Auto-generated Javadoc
/**
 * The Class Player.
 */
public class Player implements Serializable {

	/** The id. */
	private int ID;
	
	/** The color. */
	transient private Paint color;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param id the id
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
