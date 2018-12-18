package spaceship;

import javafx.geometry.Point2D;

// TODO: Auto-generated Javadoc
/**
 * The Class Rect_SS.
 */
public class Rect_SS extends SpaceShip {

	/** The Constant DEFAULT_PRODUCTION_TIME. */
	public static final int DEFAULT_PRODUCTION_TIME = 2000000;
	
	/**
	 * Instantiates a new rect SS.
	 *
	 * @param center    the center
	 * @param id_player the id player
	 */
	public Rect_SS(Point2D center, int id_player) {
		super(center, 5, 15, 1, 2, id_player);
	}
}
