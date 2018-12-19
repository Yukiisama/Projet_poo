package spaceship;

import geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;


/**
 * The Class Square_SS represents a spaceship with square form.
 * It has a size of 10x10pixels and has a speed of 2, and a attack power of 1
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
	
	/* (non_Javadoc)
	 * @see spaceship.Spaceship#draw(javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void draw(GraphicsContext gc) {
		gc.fillRect(this.getCenter().getX()-this.getWidth()/2, this.getCenter().getY()-this.getHeight()/2, this.getWidth(), this.getHeight());
	}
}
