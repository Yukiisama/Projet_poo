package spaceship;

import javafx.geometry.Point2D;

// TODO: Auto-generated Javadoc
/**
 * The Class SpaceShip.
 */
public abstract class SpaceShip {
	
	/** The center. */
	private Point2D center;
	
	/** The height. */
	private int width, height;
	
	/** The speed. */
	private int speed;
	
	/** The attack power. */
	private int attack_power;
	
	/** The id player. */
	private int id_player;
	
	/**
	 * Instantiates a new space ship.
	 *
	 * @param center       the center
	 * @param width        the width
	 * @param height       the height
	 * @param speed        the speed
	 * @param attack_power the attack power
	 * @param id_player    the id player
	 */
	public SpaceShip(Point2D center, int width, int height, int speed, int attack_power, int id_player) {
		this.center = center;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.attack_power = attack_power;
		this.id_player = id_player;
	}

	/**
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public int getSpeed() { return speed; }
	
	/**
	 * Sets the speed.
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(int speed) { this.speed = speed; }

	/**
	 * Gets the attack power.
	 *
	 * @return the attack power
	 */
	public int getAttack_power() { return attack_power; }
	
	/**
	 * Sets the attack power.
	 *
	 * @param attack_power the new attack power
	 */
	public void setAttack_power(int attack_power) { this.attack_power = attack_power; }

	/**
	 * Gets the center.
	 *
	 * @return the center
	 */
	public Point2D getCenter() { return center; }
	
	/**
	 * Sets the center.
	 *
	 * @param center the new center
	 */
	public void setCenter(Point2D center) { this.center = center; }

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() { return width; }
	
	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(int width) { this.width = width; }

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() { return height; }
	
	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(int height) { this.height = height; }

	/**
	 * Gets the id player.
	 *
	 * @return the id player
	 */
	public int getId_player() { return id_player; }
	
	/**
	 * Sets the id player.
	 *
	 * @param id_player the new id player
	 */
	public void setId_player(int id_player) { this.id_player = id_player; }
}