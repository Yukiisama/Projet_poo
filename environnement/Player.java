package environnement;

import planet.Planet;

public class Player {

	private int ID; 
	private Planet tab_planets[];
	private int nb_ship_total;
	
	
	
	/**
	 * @param iD
	 * @param tab_planets
	 * @param nb_ship_total
	 */
	public Player(int iD, Planet[] tab_planets, int nb_ship_total) {
		ID = iD;
		this.tab_planets = tab_planets;
		this.nb_ship_total = nb_ship_total;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Planet[] getTab_planets() {
		return tab_planets;
	}
	public void setTab_planets(Planet[] tab_planets) {
		this.tab_planets = tab_planets;
	}
	public int getNb_ship_total() {
		return nb_ship_total;
	}
	public void setNb_ship_total(int nb_ship_total) {
		this.nb_ship_total = nb_ship_total;
	}
	
	
}
