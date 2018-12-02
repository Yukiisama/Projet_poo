package planet;

import javafx.geometry.Point2D;

public class Rect_planet extends Planet {
	private String planet_type;
	
	public Rect_planet(float rate_production,int nb_ship, String ships_type, Point2D centre,
			int ID_player, double width , double height) {
		super(rate_production, nb_ship, ships_type, centre, ID_player,width,height);
		this.planet_type = "rectangle";
		
	}

	

	public String getPlanet_type() {
		return planet_type;
	}

	public void setPlanet_type(String planet_type) {
		this.planet_type = planet_type;
	}

}
