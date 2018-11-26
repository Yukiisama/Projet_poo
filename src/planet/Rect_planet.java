package planet;

public class Rect_planet extends Planet {
	private int width ; private int height;
	public Rect_planet(float rate_production, int nb_ship, boolean neutral, int width , int height) {
		super(rate_production,nb_ship, neutral);
		this.width = width;
		this.height= height;
		
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

}
