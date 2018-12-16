package planet;

import javafx.geometry.Point2D;
import spaceship.Rect_SS;
import spaceship.Squadron;
import spaceship.Square_SS;

abstract public class Planet  {
	private double rate_production;
	private int nb_ship;
	private String ships_type;
	private Point2D center;
	private int ID_player;
	private long left_time;
	private long production_time;
	private int selected;
	private Squadron[] squadron_tab;
	private int nb_squadron;

	public Planet(double rate_production, int nb_ship, String ships_type, Point2D center, int ID_player) {
		if (ships_type == "Square") {
            this.production_time = Square_SS.DEFAULT_PRODUCTION_TIME; // 3 seconds
        }
        else if (ships_type == "Rect") {
            this.production_time = Rect_SS.DEFAULT_PRODUCTION_TIME; // 2 seconds
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

	public long getLeft_time() { return left_time; }
	public void setLeft_time(long left_time) { this.left_time = left_time; }

	public long getProduction_time() { return production_time; }
	public void setProduction_time(long production_time) { this.production_time = production_time; }

	public Point2D getCenter() { return center; }
	public void setCenter(Point2D center) { this.center = center; }

	public String getShips_type() { return ships_type; }
	public void setShips_type(String ships_type) { this.ships_type = ships_type; }

	public int getID_player() { return ID_player; }
	public void setID_player(int iD_player) { ID_player = iD_player; }

	public double getRate_production() { return rate_production; }
	public void setRate_production(double rate_production) { this.rate_production = rate_production; }

	public int getNb_ship() { return nb_ship; }
	public void setNb_ship(int nb_ship) { this.nb_ship = nb_ship; }

	public int getSelected() { return selected; }
	public void setSelected(int selected) { this.selected = selected; }

	public Squadron[] getSquadron_tab() { return squadron_tab; }
	public void setSquadron_tab(Squadron[] squadron_tab) { this.squadron_tab = squadron_tab; }

	public int getNb_squadron() { return nb_squadron; }
	public void setNb_squadron(int nb_squadron) { this.nb_squadron = nb_squadron; }

	public abstract boolean is_inside(Point2D p);
	public abstract int getWidth();
	public abstract int getHeight();
	
	public void attack(Planet target) {
		this.squadron_tab[this.nb_squadron] = new Squadron(this, target);
		this.nb_squadron++;
	}
}
