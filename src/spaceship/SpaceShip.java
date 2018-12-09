package spaceship;

import javafx.geometry.Point2D;

public abstract class SpaceShip {
	private Point2D center;
	private double width, height;
	private int speed;
	private int attack_power;
	private String type;
	private int ID_player;

	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 * @param product_time
	 * @param attack_power
	 */

	public SpaceShip(Point2D center, String type, int ID_player, double width, double height, int speed,
			int attack_power) {

		this.center = center;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.attack_power = attack_power;
		this.type = type;
		this.ID_player = ID_player;
	}

	public int getID_player() {
		return ID_player;
	}

	public void setID_player(int iD_player) {
		ID_player = iD_player;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getAttack_power() {
		return attack_power;
	}

	public void setAttack_power(int attack_power) {
		this.attack_power = attack_power;
	}

	public Point2D getCenter() {
		return center;
	}

	public void setCenter(Point2D center) {
		this.center = center;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
