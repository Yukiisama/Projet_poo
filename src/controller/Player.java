package controller;
import java.io.Serializable;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import planet.Planet;


/**
 * The Class Player represents all informations usefull about the actuals players of the game  .
 */
public class Player implements Serializable {

	/** The Serial version UID */
	private static final long serialVersionUID = 1L;

	/** The id. If ID is one then the players is the human playing,others are IAs  */
	private int ID;
	
	/** The color , each player is characterize by one specific color ( Player One is LIGHTBLUE). */
	transient private Paint color;
	
	protected Planet tab_planets_not_own[];
	protected Planet tab_planets_own[];
	
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
	
	public void settabs(Planet[] tab) {
		int cpt_not_own = 0;
		int cpt_own = 0;
		if(tab_planets_not_own==null)
			tab_planets_not_own = new Planet[tab.length];
		if(tab_planets_own==null)
			tab_planets_own = new Planet[tab.length];
		for(int i =0 ; i< tab.length;i++) {
			if(tab[i].getID_player()==ID) {
				tab_planets_own[cpt_own]=tab[i]; cpt_own++;
			}
			else
				tab_planets_not_own[cpt_not_own]=tab[i]; cpt_not_own++;
			if(tab_planets_not_own[i]!=null && tab_planets_not_own[i].getID_player()==2)
				System.out.println("coucou");
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
