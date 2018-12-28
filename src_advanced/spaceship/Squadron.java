package spaceship;

import java.io.Serializable;

import geometry.Point2D;
import planet.Planet;



/**
 * The Class Squadron which contain multiples spaceship from a planet in order to attack a new one .
 */
public final class Squadron implements Serializable {

	/** The Serial version UID. */
	private static final long serialVersionUID = 1L;

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
		double nb = 6;
		if(Origin.getSelected()>0 && Origin.getID_player()!=-1) {
			 nb = this.origin.getNb_ship()*Origin.getSelected()/100;
		}
		
			// defining the size of the squadron depending on its origin's 
			this.spaceship_tab = new SpaceShip[(int)nb];
			if (this.origin.getNb_ship() >= nb) this.size =(int) nb;
			else this.size = this.origin.getNb_ship();
			for (int i = 0 ; i < this.size ; i++) {
				double angle = (double) (2*Math.PI/this.size) * (i);
				int radius = 0;
				radius = (int)(Math.max(this.origin.getWidth(), this.origin.getHeight())/2*1.4f);
				double x = Math.cos(angle) * radius;
				double y = Math.sin(angle) * radius;
				Point2D where = new Point2D((int)(this.origin.getCenter().getX()+x), (int)(this.origin.getCenter().getY()+y));
				spaceship_tab[i] = new SpaceShip(where, this.origin.getShips_shape(), this.origin.getID_player());
				if (i == 0) spaceship_tab[0].setLeader(true);
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
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public int getSpeed() { 
		switch(origin.getShips_shape()) {
		case "Square":return 6;			
		case "Rectangle":return 5;
		case "Circle":return 8;
		case "Oval" : return 6;
		default: break;
		}
		return 2;
		}
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
	 * @param speed the speed
	 * @param tab the planet tab
	 */
	public void squadron_move(int speed, Planet tab[]){
		double angle;
		
		for(int i= size -1 ; i>=0;i--) {
			SpaceShip s =  spaceship_tab[i];
			if(s!=null) {
				Point2D p = s.getCenter();
				if(target!=null) {
					angle = p.getAngle(target.getCenter());
					Point2D p_test = new Point2D(p.getX(), p.getY());
					
						p_test.move_angle(speed, angle);
					
					for(int j = 0 ; j<tab.length;j++) {
						if(!tab[j].equals(origin) && !tab[j].equals(target)) {
						if(tab[j].is_inside(p_test)) {
								
								angle += Math.PI/2+Math.PI/6;
								
								
						}
						
					}
					}
					p.move_angle(speed, angle);
					
					
					damage_planet(s);
				}
			}
		}
	}
	
	
	/**
	 * Damage planet.
	 *
	 * @param s the s
	 * @return true, if successful
	 */
	public boolean damage_planet(SpaceShip s) {
		int damage_force = this.spaceship_tab[0].getAttack_power();
		if(target != null && target.is_inside(s.getCenter())) {
			// CASE PIRATE(ID=666)
			if(origin.getID_player()==666) {
				if(target.getNb_ship()>0) {
						target.setNb_ship(target.getNb_ship()-1);	
					}
				size--;
				}
			//normal players and ia
			else if(target.getID_player()!=origin.getID_player() && target.getNb_ship()>0) {
				if(target.getNb_ship()-damage_force>=0) 
					target.setNb_ship(target.getNb_ship()-damage_force);
				else
					target.setNb_ship(0);	
				size--;}
			else if(target.getID_player()==origin.getID_player()) {
				if(target.getShips_shape()==origin.getShips_shape())
					target.setNb_ship(target.getNb_ship()+1);
				else target=origin;
				size--;
				}
			else {target.setID_player(origin.getID_player()); size--;}
			
		}
		
			
		return false;
	}
}
