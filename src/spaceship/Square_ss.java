package spaceship;

import javafx.geometry.Point2D;

public class Square_SS extends SpaceShip {

	public static final int DEFAULT_PRODUCTION_TIME = 3000000;
	
	public Square_SS(Point2D center, int id_player) {
		super(center, 10, 10, 2, 1, id_player);
	}
}
