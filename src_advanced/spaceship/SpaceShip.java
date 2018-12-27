package spaceship;

import java.io.Serializable;

import geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;



/**
 * The Class SpaceShip can't be instantiate.
 */
public class SpaceShip implements Serializable {

	/**  The Serial version UID. */
	private static final long serialVersionUID = 1L;
	
	/** The center. */
	private Point2D center;
	
	/** The height. */
	private int width, height;
	
	/** The height. */
	private String shape;
	
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
	 * @param shape        the shape
	 * @param id_player    the id player
	 */
	public SpaceShip(Point2D center, String shape, int id_player) {
		this.center = center;
		this.shape = shape;
		if (shape == "Square") {
			this.width = 10;
			this.height = 10;
			this.speed = 5;
			this.attack_power = 3;
			
		}
		else if (shape == "Rectangle") {
			this.width = 15;
			this.height = 5;
			this.speed = 6;
			this.attack_power = 4;
			
		}
		else if (shape == "Circle") {
			this.width = 10;
			this.height = 10;
			this.speed = 5;
			this.attack_power = 1;
			
		}
		else if (shape == "Oval") {
			this.width = 5;
			this.height = 15;
			this.speed = 4;
			this.attack_power = 2;
			
		}
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
	
	/**
	 * Draw the spaceship on a GraphicsContext.
	 *
	 * @param gc the GraphicsContext.
	 */
	public void draw(GraphicsContext gc, Point2D camera) {
		if (this.shape.compareTo("Square") == 0 || this.shape.compareTo("Rectangle") == 0) {
			gc.fillRect(this.center.getX()-this.width/2-camera.getX(), this.center.getY()-this.height/2-camera.getY(), this.width, this.height);
			//System.out.println("Je draw tavu");
		}
		else if(this.shape.compareTo("Circle") == 0 || this.shape.compareTo("Oval") == 0){
			gc.fillOval(this.center.getX()-this.width/2-camera.getX(), this.center.getY()-this.height/2-camera.getY(), this.width, this.height);
		}
	}
}