package spaceship;

import geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;


/**
 * The Class Rect_SS represents a spaceship with rectangle form, herits of SpaceShip.
 * It has a size of 5x15pixels and has a speed of 2, and a attack power of 1
 */
public class Rect_SS extends SpaceShip {
	/** The Constant DEFAULT_PRODUCTION_TIME. */
	public static final int DEFAULT_PRODUCTION_TIME = 2000000;
	
	/**
	 * Instantiates a new rect SS (i.e ship).
	 *
	 * @param center    the center
	 * @param id_player the id player
	 */
	public Rect_SS(Point2D center, int id_player) {
		super(center, 5, 15, 1, 2, id_player);
	}
	
	/* (non_Javadoc)
	 * @see spaceship.Spaceship#draw(javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void draw(GraphicsContext gc) {
		gc.fillRect(this.getCenter().getX()-this.getWidth()/2, this.getCenter().getY()-this.getHeight()/2, this.getWidth(), this.getHeight());
	}
}
