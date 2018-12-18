package planet;

import java.io.Serializable;

import javafx.geometry.Point2D;


/**
 * The Class Circle_Planet which extends Planet.
 */
public class Circle_Planet extends Planet  {
	
	/** The Constant DEFAULT_RADIUS. */
	public static final int DEFAULT_RADIUS = 120;
	
	/** The radius. */
	private int radius = DEFAULT_RADIUS;

	/**
	 * Instantiates a new circle planet.
	 *
	 * @param rate_production the rate production
	 * @param nb_ship         the number of ship to initialize
	 * @param ships_type      the ships type
	 * @param centre          the centre (i.e POINT2D)
	 * @param ID_player       the ID of the player
	 * @param size_factor     the size factor
	 */
	public Circle_Planet(double rate_production, int nb_ship, String ships_type, Point2D centre, int ID_player, double size_factor) {
		super(rate_production, nb_ship, ships_type, centre, ID_player);
		this.radius *= size_factor;
	}

	/**
	 * Gets the radius.
	 *
	 * @return the radius
	 */
	public int getRadius() { return radius; }
	
	/**
	 * Sets the radius.
	 *
	 * @param radius the new radius
	 */
	public void setRadius(int radius) { this.radius = radius; }
	
	/* (non-Javadoc)
	 * @see planet.Planet#is_inside(javafx.geometry.Point2D)
	 */
	@Override
	public boolean is_inside(Point2D p) {
		return Math.abs(Math.sqrt(Math.pow((double)(p.getX()-this.getCenter().getX()), 2)+Math.pow((double)(p.getY()-this.getCenter().getY()), 2))) < this.radius;
	}
	
	/* (non-Javadoc)
	 * @see planet.Planet#getWidth()
	 */
	@Override
	public int getWidth( ) { return radius*2; }
	
	/* (non-Javadoc)
	 * @see planet.Planet#getHeight()
	 */
	@Override
	public int getHeight() { return radius*2; }
}