package planet;

import java.io.Serializable;

import geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import spaceship.Circle_SS;
import spaceship.Rect_SS;
import spaceship.Square_SS;
import spaceship.Squadron;


/**
 * The Class Planet which is abstract and implements Serializable for save_load functions.
 */
abstract public class Planet implements Serializable {
	
	/** The Serial version UID */
	private static final long serialVersionUID = 1L;

	/** The rate production. */
	private double rate_production;
	
	/** The number of ships. */
	private int nb_ship;
	
	/** The ships type. */
	private String ships_type;
    
    /** The center (i.e Point2D ). */
    private Point2D center;
	
	/** The I D of the player which control this planet. */
	private int ID_player;
	
	/** The left time. */
	private long left_time;
	
	/** The production time. */
	private long production_time;
	
	/** The selected target. */
	private int selected;
	
	/** The squadron tab. */
	private Squadron[] squadron_tab;
	
	/** The number of squadron. */
	private int nb_squadron;

	/**
	 * Instantiates a new planet.
	 *
	 * @param rate_production the rate production
	 * @param nb_ship         the nb ship
	 * @param ships_type      the ships type
	 * @param center          the center
	 * @param ID_player       the i D player
	 */
	public Planet(double rate_production, int nb_ship, String ships_type, Point2D center, int ID_player) {
		if (ships_type == "Square") {
            this.production_time = Square_SS.DEFAULT_PRODUCTION_TIME; // 3 seconds
        }
        else if (ships_type == "Rect") {
            this.production_time = Rect_SS.DEFAULT_PRODUCTION_TIME; // 2 seconds
        }
        else if (ships_type == "Circle") {
            this.production_time = Circle_SS.DEFAULT_PRODUCTION_TIME; // 2 seconds
        }
		this.rate_production = rate_production;
		this.nb_ship = nb_ship;
		this.ships_type = ships_type;
		this.center = center;
		this.ID_player = ID_player;
		this.selected = 0;
		this.nb_squadron = 0;
		this.squadron_tab = new Squadron[1000];
	}

	/**
	 * Gets the left time.
	 *
	 * @return the left time
	 */
	public long getLeft_time() { return left_time; }
	
	/**
	 * Sets the left time.
	 *
	 * @param left_time the new left time
	 */
	public void setLeft_time(long left_time) { this.left_time = left_time; }

	/**
	 * Gets the production time.
	 *
	 * @return the production time
	 */
	public long getProduction_time() { return production_time; }
	
	/**
	 * Sets the production time.
	 *
	 * @param production_time the new production time
	 */
	public void setProduction_time(long production_time) { this.production_time = production_time; }

	/**
	 * Gets the center.
	 *
	 * @return the center
	 */
	public Point2D getCenter() { return center; }
	
	/**
	 * Sets the center.
	 *
	 * @param center the new center
	 */
	public void setCenter(Point2D center) { this.center = center; }

	/**
	 * Gets the ships type.
	 *
	 * @return the ships type
	 */
	public String getShips_type() { return ships_type; }
	
	/**
	 * Sets the ships type.
	 *
	 * @param ships_type the new ships type
	 */
	public void setShips_type(String ships_type) { this.ships_type = ships_type; }

	/**
	 * Gets the i D player.
	 *
	 * @return the i D player
	 */
	public int getID_player() { return ID_player; }
	
	/**
	 * Sets the i D player.
	 *
	 * @param iD_player the new i D player
	 */
	public void setID_player(int iD_player) { ID_player = iD_player; }

	/**
	 * Gets the rate production.
	 *
	 * @return the rate production
	 */
	public double getRate_production() { return rate_production; }
	
	/**
	 * Sets the rate production.
	 *
	 * @param rate_production the new rate production
	 */
	public void setRate_production(double rate_production) { this.rate_production = rate_production; }

	/**
	 * Gets the number of ships.
	 *
	 * @return the numbers ship
	 */
	public int getNb_ship() { return nb_ship; }
	
	/**
	 * Sets the number of ships.
	 *
	 * @param nb_ship the new number of ships
	 */
	public void setNb_ship(int nb_ship) { this.nb_ship = nb_ship; }

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	public int getSelected() { return selected; }
	
	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(int selected) { this.selected = selected; }

	/**
	 * Gets the squadron tab.
	 *
	 * @return the squadron tab
	 */
	public Squadron[] getSquadron_tab() { return squadron_tab; }
	
	/**
	 * Sets the squadron tab.
	 *
	 * @param squadron_tab the new squadron tab
	 */
	public void setSquadron_tab(Squadron[] squadron_tab) { this.squadron_tab = squadron_tab; }

	/**
	 * Gets the number of squadrons.
	 *
	 * @return the number of squadrons
	 */
	public int getNb_squadron() { return nb_squadron; }
	
	/**
	 * Sets the number of squadrons.
	 *
	 * @param nb_squadron the new number of squadrons
	 */
	public void setNb_squadron(int nb_squadron) { this.nb_squadron = nb_squadron; }
	

	/**
	 * Checks if a point is inside the planet.
	 *
	 * @param p the point p we want to test
	 * @return true, if it's inside, false if not
	 */
	public abstract boolean is_inside(Point2D p);
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public abstract int getWidth();
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public abstract int getHeight();
	
	/**
	 * Draw the planet on a GraphicsContext
	 * 
	 * @param gc the GraphicsContext
	 */
	public abstract void draw(GraphicsContext gc);
	
	
	/**
	 * Attack the planet target.
	 *
	 * @param target the target
	 */
	public void attack(Planet target) {
		this.squadron_tab[this.nb_squadron] = new Squadron(this, target);
		this.nb_squadron++;
	}
}
