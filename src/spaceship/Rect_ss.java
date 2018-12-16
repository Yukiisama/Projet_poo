package spaceship;

import javafx.geometry.Point2D;

public class Rect_SS extends SpaceShip {

	public static final int DEFAULT_PRODUCTION_TIME = 2000000;
	
	public Rect_SS(Point2D center, int id_player) {
		super(center, 5, 15, 1, 2, id_player);
	}
}
