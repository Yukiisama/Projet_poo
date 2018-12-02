package planet;

import javafx.geometry.Point2D;

public class Square_Planet extends Rect_planet {

	public Square_Planet(float rate_production, int nb_ship, String ships_type, Point2D centre, int ID_player,
			double width, double height) {
		super(rate_production, nb_ship, ships_type, centre, ID_player, width, width);
		this.setPlanet_type("square");
		
	}

	

}
