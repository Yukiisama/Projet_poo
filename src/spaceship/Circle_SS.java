package spaceship;

import geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * The Class Circle_SS represents a spaceship with circle form, herits of SpaceShip.
 * It has a size of 5x15pixels and has a speed of 3, and a attack power of 1
 */
public class Circle_SS extends SpaceShip {
	/** The Constant DEFAULT_PRODUCTION_TIME. */
	public static final int DEFAULT_PRODUCTION_TIME = 3000000;	

	/**
	 * Instantiates a new circle SS.
	 *
	 * @param center    the center
	 * @param id_player the id player
	 */
	public Circle_SS(Point2D center, int id_player) {
		super(center, 10, 10, 2, 1, id_player);
	}

	/* (non_Javadoc)
	 * @see spaceship.Spaceship#draw(javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void draw(GraphicsContext gc) {
		gc.fillOval(this.getCenter().getX()-this.getWidth()/2, this.getCenter().getY()-this.getHeight()/2, this.getWidth(), this.getHeight());
	}
}