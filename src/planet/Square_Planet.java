package planet;

import javafx.geometry.Point2D;

public class Square_Planet extends Planet {
	public static final int DEFAULT_SIZE = 120;
	private int size = DEFAULT_SIZE;

	public Square_Planet(double rate_production, int nb_ship, String ships_type, Point2D centre, int ID_player, double size_factor) {
		super(rate_production, nb_ship, ships_type, centre, ID_player);
		this.size *= size_factor;
	}

	public int getSize() { return size; }
	public void setSize(int size) { this.size = size; }

	@Override
	public boolean is_inside(Point2D p) {
		return Math.abs(p.getX() - this.getCenter().getX()) < this.size/2 && Math.abs(p.getY() - this.getCenter().getY()) < this.size/2;
	}
	
	@Override
	public int getWidth( ) { return size; }
	
	@Override
	public int getHeight() { return size; }
}
