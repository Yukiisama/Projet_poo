package geometry;

import java.io.Serializable;

/**
 * The Class Point2D.
 */
public class Point2D implements Serializable {
	
	/** The seriaVersionUID */
	private static final long serialVersionUID = -223459058008132259L;
	/** The x. */
	private int x;
	/** The y. */
	private int y;
	
	/**
	 * Instantiates a new point 2 D.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	


/**
 * Gets the x.
 *
 * @return the x
 */
public int getX() {return x;}

/**
 * Sets the x.
 *
 * @param x the new x
 */
public void setX(int x) {this.x = x;}

/**
 * Gets the y.
 *
 * @return the y
 */
public int getY() {return y;}

/**
 * Sets the y.
 *
 * @param y the new y
 */
public void setY(int y) {this.y = y;}

/**
 * Prints the.
 */
public void print() {System.out.println("Point : (" + x + ", " + y + ")");}

/**
 * Move.
 *
 * @param dx the dx
 * @param dy the dy
 */
public void move(int dx, int dy) {x += dx;y += dy;}

/**
 * Replace.
 *
 * @param dx the dx
 * @param dy the dy
 */
public void replace(int dx , int dy) {this.x = dx;this.y=dy;}

/**
 * Distance.
 *
 * @param p the p
 * @return the double
 */
public double distance(Point2D p) {
		int d1 = p.x - x;
		int d2 = p.y - y;
		return Math.sqrt(d1*d1 + d2*d2);
	}
}

