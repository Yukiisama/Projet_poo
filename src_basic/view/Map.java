package view;

import java.io.Serializable;
import java.util.Random;

import controller.IA;
import controller.Player;
import geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import planet.Planet;
import spaceship.SpaceShip;




/**
 * The Class Map represents each informations we need to draw the actual content of the game .
 */
public class Map implements Serializable {

	/**  The Serial version UID. */
	private static final long serialVersionUID = 1L;

	/** The Constant WIDTH. */
	public final static int WIDTH = 1600;

	/** The Constant HEIGHT. */
	public final static int HEIGHT = 920;

	/** The min dist. */
	private final int min_dist = 250;

	/** The planet tab. */
	private Planet planet_tab[];

	/** The player tab. */
	private Player player_tab[];

	/** The nb planets. */
	private int nb_planets;

	/** The last time. */
	private long last_time;

	/**
	 * Instantiates a new map.
	 *
	 * @param nb_planets the nb planets
	 */
	public Map(int nb_planets) {
		this.planet_tab = new Planet[nb_planets];
		this.player_tab = new Player[2];
		this.nb_planets = nb_planets;
		this.add_planets();
		this.add_players();
	}

	/**
	 * Gets the planet tab.
	 *
	 * @return the planet tab
	 */
	public Planet[] getPlanet_tab() { return planet_tab; }

	/**
	 * Sets the planet tab.
	 *
	 * @param planet_tab the new planet tab
	 */
	public void setPlanet_tab(Planet planet_tab[]) { this.planet_tab = planet_tab; }

	/**
	 * Gets the nb planets.
	 *
	 * @return the nb planets
	 */
	public int getNb_planets() { return nb_planets; }

	/**
	 * Sets the nb planets.
	 *
	 * @param nb_planets the new nb planets
	 */
	public void setNb_planets(int nb_planets) { this.nb_planets = nb_planets; }

	/**
	 * Gets the player tab.
	 *
	 * @return the player tab
	 */
	public Player[] getPlayer_tab() { return player_tab; }

	/**
	 * Sets the player tab.
	 *
	 * @param player_tab the new player tab
	 */
	public void setPlayer_tab(Player[] player_tab) { this.player_tab = player_tab; }

	/**
	 * Adds the players.
	 */
	public void add_players() {
        player_tab[0] = new Player(0);
        player_tab[1] = new IA(1);
	}

	/**
	 * Checks if is location valid.
	 *
	 * @param p           the point p to test location
	 * @param size_factor the size factor
	 * @return true, if is location valid
	 */
	public boolean is_location_valid(Point2D p, double size_factor) {

		//Out of bounds detection
		int half_size = 0;
		half_size = (int)(Planet.ci_width*size_factor)/2;
		if (p.getX() - half_size < 0 || p.getX() + half_size > Map.WIDTH || p.getY() - half_size < 0 || p.getY() + half_size > Map.HEIGHT) return false;

		//No other planets detection
		if (nb_planets == 0) return true;

		//Other planets detection
		for (int i =0 ; i < this.nb_planets;i++) {
			if(this.planet_tab[i]!=null) {
				Point2D pos = this.planet_tab[i].getCenter();
				if(pos.distance(p) < this.min_dist) return false;
			}
		}
		return true;
	}

	/**
	 * Adds the planets.
	 */
	public void add_planets() {
		int id = -1;

		Random gen = new Random();

		double size_factor = 0.0f;
		while (size_factor < 0.4f) size_factor = gen.nextDouble();

		/* Each planet random location, random size, random form, random spaceships */
		for (int i = 0 ; i < this.nb_planets ; i++) {
			Point2D point = new Point2D(gen.nextInt(Map.WIDTH - this.min_dist), gen.nextInt(Map.HEIGHT - this.min_dist));
			if(is_location_valid(point, size_factor)) {
					int nb_ship = 0;
					nb_ship = gen.nextInt(30);
					planet_tab[i] = new Planet(1/size_factor, nb_ship, point, id, size_factor);
					id++;
					if (id >= 2) {
						size_factor = 0.0f;
						while (size_factor < 0.4f) size_factor = gen.nextDouble();
						id = -1;
					}
			}
			else i--;
		}
	}

	/**
	 * Draw planets.
	 *
	 * @param gc  the GraphicsContext we use for drawing the planets and their informations
	 */
	public void draw_planets(GraphicsContext gc) {

		for (int i = 0 ; i < this.nb_planets ; i++) {
			Planet planet = this.planet_tab[i];

			// Getting the right color
			if (planet.getID_player() == -1) gc.setFill(Color.GRAY);
			else {
				for (int j = 0 ; j < 2 ; j++) {
					if (this.player_tab[j].getID() == planet.getID_player()) {
						gc.setFill(this.player_tab[j].getColor());
					}
				}
			}

			planet.draw(gc);
		}
	}

	/**
	 * Draw text planets.
	 *
	 * @param gc2  the GraphicsContext we use for drawing text of the planets (production ..)
	 */
	public void draw_text_planets(GraphicsContext gc2) {
		for (int i = 0 ; i < this.getNb_planets() ; i++) {
			String nb_ships =  String.valueOf(planet_tab[i].getNb_ship());
			gc2.setFont(Font.font("Helvetica", FontWeight.NORMAL, 24));
			gc2.fillText(nb_ships, this.planet_tab[i].getCenter().getX(), this.planet_tab[i].getCenter().getY()+6);
			gc2.strokeText(nb_ships, this.planet_tab[i].getCenter().getX(), this.planet_tab[i].getCenter().getY()+6);
			gc2.setFont(Font.font("Helvetica", FontWeight.NORMAL, 12));
			if (planet_tab[i].getID_player() == 0) {
				String selected = String.valueOf(planet_tab[i].getSelected())+"%";
				gc2.fillText(selected, this.planet_tab[i].getCenter().getX(), this.planet_tab[i].getCenter().getY()+18);
				gc2.strokeText(selected, this.planet_tab[i].getCenter().getX(), this.planet_tab[i].getCenter().getY()+18);
			}
		}
	}

	/**
	 * Draw squadrons.
	 *
	 * @param gc3  the GraphicsContext we use for drawing the spaceships in squadrons
	 */
	public void draw_squadrons(GraphicsContext gc3) {
		for (int i = 0 ; i < this.nb_planets ; i++) {
			for (int j = 0 ; j < this.planet_tab[i].getNb_squadron() ; j++) {
				SpaceShip tab[] = this.planet_tab[i].getSquadron_tab()[j].getSpaceship_tab();
				for (int k = 0; k < this.planet_tab[i].getSquadron_tab()[j].getSize(); k++) {
					int id_player = this.planet_tab[i].getSquadron_tab()[j].getOrigin().getID_player();
					for (int l = 0 ; l < 2 ; l++) if (this.player_tab[l].getID() == id_player) gc3.setFill(this.player_tab[l].getColor());
					tab[k].draw(gc3);

				}
				this.planet_tab[i].getSquadron_tab()[j].squadron_move(8,planet_tab);
			}
		}
	}


	/**
	 * Update ships numbers.
	 *
	 * @param now the now
	 */
	public void update_ships_numbers(long now) {
		long passed_time = (now - this.last_time)/1000;
		this.last_time = now;

		int i = 0;
		while (i < this.getNb_planets() && this.planet_tab[i] != null) {
			this.planet_tab[i].setLeft_time(this.planet_tab[i].getLeft_time()-passed_time);
			if (this.planet_tab[i].getLeft_time() <= 0 && this.planet_tab[i].getID_player() != -1) {
				long new_left_time = this.planet_tab[i].getProduction_time() * (long)this.planet_tab[i].getRate_production();
				long new_left_time_dot = (long)(this.planet_tab[i].getProduction_time() * (this.planet_tab[i].getRate_production() - (long)this.planet_tab[i].getRate_production()));
				this.planet_tab[i].setLeft_time(new_left_time + new_left_time_dot);
				this.planet_tab[i].setNb_ship(this.planet_tab[i].getNb_ship()+1);
			}
			i++;
		}
	}
	
	/**
	 * Win condition.
	 */
	public void win_condition() {
		boolean state = true;
		for(int i = 0 ; i < 2 ; i++) {
			for(int j = 0 ; j<nb_planets;j++) {
				if(planet_tab[j].getID_player()!=i)
					state=false;
				if(j==nb_planets-1 && state==true) {
					System.out.println("VOUS AVEZ GAGNEZ");
					System.exit(0);
				}
			}
		}
	}
}
