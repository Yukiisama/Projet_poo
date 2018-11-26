package planet;

import com.sun.javafx.geom.Point2D;

public class Rect_planet extends Planet {
	private String planet_type;
	private int width ; private int height;
	public Rect_planet(float rate_production,int nb_ship, String ships_type, Point2D centre, int ID_player, int width , int height) {
		super(rate_production, nb_ship, ships_type, centre, ID_player);
		this.planet_type = "rectangle";
		this.width = width;
		this.height = height;
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

	public String getPlanet_type() {
		return planet_type;
	}

	public void setPlanet_type(String planet_type) {
		this.planet_type = planet_type;
	}

}
