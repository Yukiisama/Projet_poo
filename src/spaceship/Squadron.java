package spaceship;

import javafx.geometry.Point2D;
import planet.Circle_Planet;
import planet.Planet;
import planet.Square_Planet;

public final class Squadron {
	private SpaceShip spaceship_tab[];
	private Planet target;
	private Planet origin;
	private int size;

	public Squadron(Planet Origin, Planet Target){
		this.origin = Origin;
		this.target = Target;
		this.spaceship_tab = new SpaceShip[6];
		if (this.origin.getNb_ship() >= 6) this.size = 6;
		else this.size = this.origin.getNb_ship();
		for (int i = 0 ; i < this.size ; i++) {
			double angle = (double) (2*Math.PI/this.size) * (i);
			int radius = 0;
			if (this.origin instanceof Circle_Planet) radius = ((Circle_Planet)this.origin).getRadius();
			else if (this.origin instanceof Square_Planet) radius = (int)Math.sqrt(Math.pow((double)((Square_Planet) this.origin).getSize(), 2));
			double x = Math.cos(angle) * radius;
			double y = Math.sin(angle) * radius;
			Point2D where = new Point2D(this.origin.getCenter().getX()+x, this.origin.getCenter().getY()+y);
			if (this.origin.getShips_type()=="Square") spaceship_tab[i] = new Square_SS(where, this.origin.getID_player());
			else if (this.origin.getShips_type()=="Rect") spaceship_tab[i] = new Rect_SS(where, this.origin.getID_player());
			this.origin.setNb_ship(this.origin.getNb_ship() - 1);
		}
	}

	public SpaceShip[] getSpaceship_tab() { return spaceship_tab; }
	public void setSpaceship_tab(SpaceShip[] spaceship_tab) { this.spaceship_tab = spaceship_tab; }
	
	public Planet getTarget() { return target; }
	public void setTarget(Planet target) { this.target = target; }

	public Planet getOrigin() { return origin; }
	public void setOrigin(Planet origin) { this.origin = origin; }

	public int getSize() { return size; }
	public void setSize(int size) { this.size = size; }

	public void squadron_move(){
		
	}
}
