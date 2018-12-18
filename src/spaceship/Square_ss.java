package spaceship;

import javafx.geometry.Point2D;

// TODO: Auto-generated Javadoc
/**
 * The Class Square_SS.
 */
public class Square_SS extends SpaceShip {

	/** The Constant DEFAULT_PRODUCTION_TIME. */
	public static final int DEFAULT_PRODUCTION_TIME = 3000000;
	
	/**
	 * Instantiates a new square SS.
	 *
	 * @param center    the center
	 * @param id_player the id player
	 */
	public Square_SS(Point2D center, int id_player) {
		super(center, 10, 10, 2, 1, id_player);
	}
}
