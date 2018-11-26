package planet;


import com.sun.javafx.geom.Point2D;

abstract public class Planet  {
	private float rate_production;
	private int nb_ship;
	private String ships_type;
	private Point2D centre;
	private int ID_player;
	
	/**
	 * @param rate_production  
	 * @param size
	 * @param nb_ship
	 * @param tab_ship
	 * @param neutral
	 */
	public Planet(float rate_production,int nb_ship, String ships_type, Point2D centre, int ID_player) {
		this.rate_production = rate_production;
		this.nb_ship = nb_ship;
		this.ships_type = ships_type;
		this.centre = centre;
		this.ID_player = ID_player;
	}
	
	public Planet(float rate_production,int nb_ship, String ships_type, Point2D centre) {
		this.rate_production = rate_production;
		this.nb_ship = nb_ship;
		this.ships_type = ships_type;
		this.centre = centre;
		this.ID_player = -1;
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
	
	

	
	
}
