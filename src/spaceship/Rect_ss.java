package spaceship;

import planet.Planet;

public class Rect_ss extends SpaceShip {
	private int width; private int height;
	public Rect_ss(int speed, int product_time, int attack_power, int width , int height) {
		super(speed, product_time, attack_power);
		this.width = width; this.height = height;
		
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int deplacement(Planet origin , Planet destination) { return 0;}
	public int production() { return 0;}
	public boolean collision() { return true;}
	
}
