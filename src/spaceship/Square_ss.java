package spaceship;

import javafx.geometry.Point2D;

public class Square_ss extends Rect_ss {

	public Square_ss(Point2D center,int ID_player, double width, int speed, int attack_power) {
		super(center, ID_player, width, width, speed, attack_power);
		this.setType("Square");
		// TODO Auto-generated constructor stub
	}


}
