package planet;

import interfaces.Actions_game;
import spaceship.SpaceShip;

abstract public class Planet implements Actions_game {
	private float rate_production;
	private int nb_ship;
	private SpaceShip tab_ship[];
	boolean neutral;
	
	/**
	 * @param rate_production  
	 * @param size
	 * @param nb_ship
	 * @param tab_ship
	 * @param neutral
	 */
	public Planet(float rate_production,int nb_ship, boolean neutral) {
		this.rate_production = rate_production;
		this.nb_ship = nb_ship;
		this.tab_ship = new SpaceShip[1000];
		this.neutral = neutral;
	}
	//getter & setters
	public float getRate_production() {
		return rate_production;
	}
	public void setRate_production(float rate_production) {
		this.rate_production = rate_production;
	}

	public int getNb_ship() {
		return nb_ship;
	}
	public void setNb_ship(int nb_ship) {
		this.nb_ship = nb_ship;
	}
	public SpaceShip[] getTab_ship() {
		return tab_ship;
	}
	public void setTab_ship(SpaceShip[] tab_ship) {
		this.tab_ship = tab_ship;
	}
	public boolean isNeutral() {
		return neutral;
	}
	public void setNeutral(boolean neutral) {
		this.neutral = neutral;
	}
	
	public int deplacement(Planet origin , Planet destination) { return 0;}
	public int production() { return 0;}
	public boolean collision() { return true;}
	
	
}
