package spaceship;

import geometry.Point2D;
import planet.Planet;


/**
 * The Class Squadron which contain multiples spaceship from a planet in order to attack a new one .
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
	 * @param Origin the origin planet
	 * @param Target the target planet
	 */
	public Squadron(Planet Origin, Planet Target){
		this.origin = Origin;
		this.target = Target;
		
		// defining the size of the squadron depending on its origin's radius
		this.spaceship_tab = new SpaceShip[6];
		if (this.origin.getNb_ship() >= 6) this.size = 6;
		else this.size = this.origin.getNb_ship();
		for (int i = 0 ; i < this.size ; i++) {
			double angle = (double) (2*Math.PI/this.size) * (i);
			int radius = 0;
			radius = (int)(Math.max(this.origin.getWidth(), this.origin.getHeight())/2*1.4f);
			double x = Math.cos(angle) * radius;
			double y = Math.sin(angle) * radius;
			Point2D where = new Point2D((int)(this.origin.getCenter().getX()+x), (int)(this.origin.getCenter().getY()+y));
			spaceship_tab[i] = new SpaceShip(where, this.origin.getShips_shape(), this.origin.getID_player());
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
	public void squadron_move(int speed){
		double angle;
		//Parcours à l'envers bcp plus facile pour supprimer
		for(int i= size -1 ; i>=0;i--) {
			SpaceShip s =  spaceship_tab[i];
			if(s!=null) {
			Point2D p = s.getCenter();
			angle = p.getAngle(target.getCenter());
			p.move_angle(speed, angle);
			damage_planet(s);
			}
		
		}
	}
	public boolean damage_planet(SpaceShip s) {
		
		if(target.is_inside(s.getCenter())) {
			if(target.getNb_ship()>0) {target.setNb_ship(target.getNb_ship()-1);size--;}
			else target.setID_player(origin.getID_player());
		}
			
		return false;
	}
}
