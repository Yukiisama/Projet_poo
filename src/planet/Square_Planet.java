package planet;

import javafx.geometry.Point2D;

// TODO: Auto-generated Javadoc
/**
 * The Class Square_Planet.
 */
public class Square_Planet extends Planet {
	
	/** The Constant DEFAULT_SIZE. */
	transient public static final int DEFAULT_SIZE = 120;
	
	/** The size. */
	transient private int size = DEFAULT_SIZE;

	/**
	 * Instantiates a new square planet.
	 *
	 * @param rate_production the rate production
	 * @param nb_ship         the nb ship
	 * @param ships_type      the ships type
	 * @param centre          the centre
	 * @param ID_player       the i D player
	 * @param size_factor     the size factor
	 */
	public Square_Planet(double rate_production, int nb_ship, String ships_type, Point2D centre, int ID_player, double size_factor) {
		super(rate_production, nb_ship, ships_type, centre, ID_player);
		this.size *= size_factor;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() { return size; }
	
	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size) { this.size = size; }

	/* (non-Javadoc)
	 * @see planet.Planet#is_inside(javafx.geometry.Point2D)
	 */
	@Override
	public boolean is_inside(Point2D p) {
		return Math.abs(p.getX() - this.getCenter().getX()) < this.size/2 && Math.abs(p.getY() - this.getCenter().getY()) < this.size/2;
	}
	
	/* (non-Javadoc)
	 * @see planet.Planet#getWidth()
	 */
	@Override
	public int getWidth( ) { return size; }
	
	/* (non-Javadoc)
	 * @see planet.Planet#getHeight()
	 */
	@Override
	public int getHeight() { return size; }
}
