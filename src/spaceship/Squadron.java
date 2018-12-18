package spaceship;

import javafx.geometry.Point2D;
import planet.Circle_Planet;
import planet.Planet;
import planet.Square_Planet;

// TODO: Auto-generated Javadoc
/**
 * The Class Squadron.
 */
public final class Squadron {
	
	/** The spaceship tab. */
	private SpaceShip spaceship_tab[];
	
	/** The target. */
	private Planet target;
	
	/** The origin. */
	private Planet origin;
	
	/** The size. */
	private int size;

	/**
	 * Instantiates a new squadron.
	 *
	 * @param Origin the origin
	 * @param Target the target
	 */
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

	/**
	 * Gets the spaceship tab.
	 *
	 * @return the spaceship tab
	 */
	public SpaceShip[] getSpaceship_tab() { return spaceship_tab; }
	
	/**
	 * Sets the spaceship tab.
	 *
	 * @param spaceship_tab the new spaceship tab
	 */
	public void setSpaceship_tab(SpaceShip[] spaceship_tab) { this.spaceship_tab = spaceship_tab; }
	
	/**
	 * Gets the target.
	 *
	 * @return the target
	 */
	public Planet getTarget() { return target; }
	
	/**
	 * Sets the target.
	 *
	 * @param target the new target
	 */
	public void setTarget(Planet target) { this.target = target; }

	/**
	 * Gets the origin.
	 *
	 * @return the origin
	 */
	public Planet getOrigin() { return origin; }
	
	/**
	 * Sets the origin.
	 *
	 * @param origin the new origin
	 */
	public void setOrigin(Planet origin) { this.origin = origin; }

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() { return size; }
	
	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size) { this.size = size; }

	/**
	 * Squadron move.
	 */
	public void squadron_move(){
		
	}
}
