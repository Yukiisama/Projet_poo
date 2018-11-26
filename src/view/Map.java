package view;

import planet.Planet;

public class Map {

	private int height ; private int width;
	private Planet planet_tab[];
	private int nb_planets;
	private int nb_joueurs;
	
	
	
	
	/**
	 * @param height
	 * @param width
	 * @param planet_tab
	 * @param nb_planets
	 * @param nb_joueurs
	 */
	public Map(int height, int width, Planet[] planet_tab, int nb_planets, int nb_joueurs) {
		this.height = height;
		this.width = width;
		this.planet_tab = planet_tab;
		this.nb_planets = nb_planets;
		this.nb_joueurs = nb_joueurs;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public Planet[] getPlanet_tab() {
		return planet_tab;
	}
	public void setPlanet_tab(Planet[] planet_tab) {
		this.planet_tab = planet_tab;
	}
	public int getNb_planets() {
		return nb_planets;
	}
	public void setNb_planets(int nb_planets) {
		this.nb_planets = nb_planets;
	}
	public int getNb_joueurs() {
		return nb_joueurs;
	}
	public void setNb_joueurs(int nb_joueurs) {
		this.nb_joueurs = nb_joueurs;
	}
	
	
}
