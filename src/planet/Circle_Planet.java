package planet;

import javafx.geometry.Point2D;

public class Circle_Planet extends Planet {
	public static final int DEFAULT_RADIUS = 120;
	private int radius = DEFAULT_RADIUS;

	public Circle_Planet(double rate_production, int nb_ship, String ships_type, Point2D centre, int ID_player, double size_factor) {
		super(rate_production, nb_ship, ships_type, centre, ID_player);
		this.radius *= size_factor;
	}

	public int getRadius() { return radius; }
	public void setRadius(int radius) { this.radius = radius; }
	
	@Override
	public boolean is_inside(Point2D p) {
		return Math.abs(Math.sqrt(Math.pow((double)(p.getX()-this.getCenter().getX()), 2)+Math.pow((double)(p.getY()-this.getCenter().getY()), 2))) < this.radius;
	}
	
	@Override
	public int getWidth( ) { return radius*2; }
	
	@Override
	public int getHeight() { return radius*2; }
}