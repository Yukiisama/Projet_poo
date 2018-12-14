package planet;



import java.awt.Rectangle;

import javafx.geometry.Point2D;

abstract public class Planet {
	private float rate_production;
	private int nb_ship;
	private String ships_type, planet_type;
	private Point2D centre;
	private int ID_player;
	private double width;
	private double height;
	private long left_time;
	private long production_time;
	

	/**
	 * @param rate_production
	 * @param size
	 * @param nb_ship
	 * @param tab_ship
	 * @param neutral
	 */
	
	public Planet(float rate_production, int nb_ship, String ships_type, String planet_type, Point2D centre,
			int ID_player, double width, double height) {
		if (ships_type == "Square") {
			this.production_time = 1000000;
		} else if (ships_type == "Rect") {
			this.production_time = 2000000;
		}
		this.rate_production = rate_production;
		this.nb_ship = nb_ship;
		this.ships_type = ships_type;
		this.planet_type = planet_type;
		this.centre = centre;
		this.ID_player = ID_player;
		this.width = width;
		this.height = height;
		
	}

	public Planet(float rate_production, int nb_ship, String ships_type, String planet_type, Point2D centre) {
		this.rate_production = rate_production;
		this.nb_ship = nb_ship;
		this.ships_type = ships_type;
		this.planet_type = planet_type;
		this.centre = centre;
		this.ID_player = -1;
	}

	public long getLeft_time() {
		return left_time;
	}

	public String getPlanet_type() {
		return planet_type;
	}

	public void setPlanet_type(String planet_type) {
		this.planet_type = planet_type;
	}

	public void setLeft_time(long left_time) {
		this.left_time = left_time;
	}

	public long getProduction_time() {
		return production_time;
	}

	public void setProduction_time(long production_time) {
		this.production_time = production_time;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Point2D getCentre() {
		return centre;
	}

	public void setCentre(Point2D centre) {
		this.centre = centre;
	}

	public String getShips_type() {
		return ships_type;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setShips_type(String ships_type) {
		this.ships_type = ships_type;
	}

	public int getID_player() {
		return ID_player;
	}

	public void setID_player(int iD_player) {
		ID_player = iD_player;
	}

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
	
	public boolean pt_contains_polygon(int x , int y) {
		boolean state = false;
		if(this.planet_type=="Rect" || this.planet_type =="Square") {
			java.awt.Rectangle _r = new Rectangle((int)centre.getX()-10,(int)centre.getY()-10,(int)width+20,(int)height+20);
			state = _r.contains(x, y);
		}
		return state;
	}
	

}
