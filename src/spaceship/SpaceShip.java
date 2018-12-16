package spaceship;

import javafx.geometry.Point2D;

public abstract class SpaceShip {
	private Point2D center;
	private int width, height;
	private int speed;
	private int attack_power;
	private int id_player;
	
	public SpaceShip(Point2D center, int width, int height, int speed, int attack_power, int id_player) {
		this.center = center;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.attack_power = attack_power;
		this.id_player = id_player;
	}

	public int getSpeed() { return speed; }
	public void setSpeed(int speed) { this.speed = speed; }

	public int getAttack_power() { return attack_power; }
	public void setAttack_power(int attack_power) { this.attack_power = attack_power; }

	public Point2D getCenter() { return center; }
	public void setCenter(Point2D center) { this.center = center; }

	public int getWidth() { return width; }
	public void setWidth(int width) { this.width = width; }

	public int getHeight() { return height; }
	public void setHeight(int height) { this.height = height; }

	public int getId_player() { return id_player; }
	public void setId_player(int id_player) { this.id_player = id_player; }
}