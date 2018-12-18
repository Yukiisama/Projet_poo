package planet;

import javafx.geometry.Point2D;


/**
 * The Class Rect_Planet which extends Planet.
 */
public class Rect_Planet extends Planet {
	
	/** The Constant DEFAULT_HEIGHT. */
	public static final int DEFAULT_HEIGHT = 90;
	
	/** The Constant DEFAULT_WIDTH. */
	public static final int DEFAULT_WIDTH = 150;
	
	/** The height. */
	private int height = DEFAULT_HEIGHT;
	
	/** The width. */
	private int width = DEFAULT_WIDTH;
	
	/**
	 * Instantiates a new rect planet.
	 *
	 * @param rate_production the rate production
	 * @param nb_ship         the nb ship
	 * @param ships_type      the ships type
	 * @param centre          the centre (POINT2D)
	 * @param ID_player       the i D player
	 * @param size_factor     the size factor
	 */
	public Rect_Planet(double rate_production, int nb_ship, String ships_type, Point2D centre, int ID_player, double size_factor) {
		super(rate_production, nb_ship, ships_type, centre, ID_player);
		this.height *= size_factor;
		this.width *= size_factor;
		
	}

	/* (non-Javadoc)
	 * @see planet.Planet#getHeight()
	 */
	@Override
	public int getHeight() { return height; }
	
	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(int height) { this.height = height; }

	/* (non-Javadoc)
	 * @see planet.Planet#getWidth()
	 */
	@Override
	public int getWidth() { return width; }
	
	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(int width) { this.width = width; }

	/* (non-Javadoc)
	 * @see planet.Planet#is_inside(javafx.geometry.Point2D)
	 */
	@Override
	public boolean is_inside(Point2D p) {
		return Math.abs(p.getX() - this.getCenter().getX()) < this.width/2 && Math.abs(p.getY() - this.getCenter().getY()) < this.height/2;
	}

}
