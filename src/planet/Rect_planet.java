package planet;

import javafx.geometry.Point2D;

public class Rect_Planet extends Planet {
	public static final int DEFAULT_HEIGHT = 90;
	public static final int DEFAULT_WIDTH = 150;
	private int height = DEFAULT_HEIGHT;
	private int width = DEFAULT_WIDTH;
	
	public Rect_Planet(double rate_production, int nb_ship, String ships_type, Point2D centre, int ID_player, double size_factor) {
		super(rate_production, nb_ship, ships_type, centre, ID_player);
		this.height *= size_factor;
		this.width *= size_factor;
		
	}

	@Override
	public int getHeight() { return height; }
	public void setHeight(int height) { this.height = height; }

	@Override
	public int getWidth() { return width; }
	public void setWidth(int width) { this.width = width; }

	@Override
	public boolean is_inside(Point2D p) {
		return Math.abs(p.getX() - this.getCenter().getX()) < this.width/2 && Math.abs(p.getY() - this.getCenter().getY()) < this.height/2;
	}

}
