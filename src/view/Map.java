package view;

import java.util.Random;

import controller.Player;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import planet.Circle_Planet;
import planet.Planet;
import planet.Square_Planet;
import spaceship.SpaceShip;

public class Map {
	public final static int WIDTH = 1600;
	public final static int HEIGHT = 920;
	
	private final int min_dist = 200;
	private Planet planet_tab[];
	private Player player_tab[];
	private int nb_planets;
	private int nb_players;
	private long last_time;
	
	public Map(int nb_planets, int nb_players) {
		this.planet_tab = new Planet[nb_planets];
		this.player_tab = new Player[nb_players];
		this.nb_planets = nb_planets;
		this.nb_players = nb_players;
		this.add_planets();
		this.add_players();
	}

	public Planet[] getPlanet_tab() { return planet_tab; }
	public void setPlanet_tab(Planet planet_tab[]) { this.planet_tab = planet_tab; }

	public int getNb_planets() { return nb_planets; }
	public void setNb_planets(int nb_planets) { this.nb_planets = nb_planets; }

	public Player[] getPlayer_tab() { return player_tab; }
	public void setPlayer_tab(Player[] player_tab) { this.player_tab = player_tab; }

	public int getNb_players() { return nb_players; }
	public void setNb_players(int nb_players) { this.nb_players = nb_players; }


	public void add_players() {
		for (int i = 0 ; i < this.nb_players ; i++) {
			player_tab[i] = new Player(i);
		}
	}
	
	public boolean is_location_valid(Point2D p, double size_factor, String planet_type) {
		
		//Out of bounds detection
		if (planet_type == "Square") {
			int half_size = (int)(Square_Planet.DEFAULT_SIZE*size_factor)/2;
			if (p.getX() - half_size < 0 || p.getX() + half_size > Map.WIDTH || p.getY() - half_size < 0 || p.getY() + half_size > Map.HEIGHT) return false;
		}
		else if (planet_type == "Circle") {
			int half_size = (int)(Circle_Planet.DEFAULT_RADIUS*size_factor)/2;
			if (p.getX() - half_size < 0 || p.getX() + half_size > Map.WIDTH || p.getY() - half_size < 0 || p.getY() + half_size > Map.HEIGHT) return false;
		}
		
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
	
	public void add_planets() {
		int id = -1;

		Random gen = new Random();
		
		double size_factor = 0.0f;
		while (size_factor < 0.4f) size_factor = gen.nextDouble();
		
		String planet_type = "Square";
		switch (gen.nextInt(2)) {
		case 1:
			planet_type = "Circle";
			break;
		}

		for (int i = 0 ; i < this.nb_planets ; i++) {
			Point2D point = new Point2D(gen.nextInt(Map.WIDTH - this.min_dist), gen.nextInt(Map.HEIGHT - this.min_dist));
			if(is_location_valid(point, size_factor, planet_type)) {
					int nb_ship = 0;
					if (id == -1) nb_ship = gen.nextInt(30);
					if (planet_type == "Square") planet_tab[i] = new Square_Planet(1/size_factor, nb_ship, "Square", point, id, size_factor);
					else if (planet_type == "Circle") planet_tab[i] = new Circle_Planet(1/size_factor, nb_ship, "Square", point, id, size_factor);
					id++;
					if (id >= this.nb_players) {
						size_factor = 0.0f;
						while (size_factor < 0.4f) size_factor = gen.nextDouble();
						id = -1;
						
						switch (gen.nextInt(2)) {
						case 0:
							planet_type = "Square";
							break;
						case 1:
							planet_type = "Circle";
							break;
						}
					}
			}
			else i--;
		}
	}
	
	public void draw_planets(GraphicsContext gc) {
		
		for (int i = 0 ; i < this.nb_planets ; i++) {
			Planet planet = this.planet_tab[i];
			
			// Getting the right color
			if (planet.getID_player() == -1) gc.setFill(Color.GRAY);
			else {
				for (int j = 0 ; j < this.nb_players ; j++) {
					if (this.player_tab[j].getID() == planet.getID_player()) {
						gc.setFill(this.player_tab[j].getColor());
					}
				}
			}
			
			// Getting the center
			double x = planet.getCenter().getX();
			double y = planet.getCenter().getY();
			
			// Fillings shapes according to planet type
			if (planet instanceof Square_Planet) {
				int size = ((Square_Planet)planet).getSize();
				gc.fillRect(x-size/2, y-size/2, size, size);
			}
			
			if (planet instanceof Circle_Planet) {
				int radius = ((Circle_Planet)planet).getRadius();
				gc.fillOval(x-radius/2, y-radius/2, radius, radius);
			}
		}
	}
	
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
	
	public void draw_squadrons(GraphicsContext gc3) {
		for (int i = 0 ; i < this.nb_planets ; i++) {
			for (int j = 0 ; j < this.planet_tab[i].getNb_squadron() ; j++) {
				SpaceShip tab[] = this.planet_tab[i].getSquadron_tab()[j].getSpaceship_tab();
				for (int k = 0; k < this.planet_tab[i].getSquadron_tab()[j].getSize(); k++) {
					if (tab[j] != null) {
						
						double x = tab[k].getCenter().getX();
						double y = tab[k].getCenter().getY();
						double width = tab[k].getWidth();
						double height = tab[k].getHeight();
						
						int id_player = this.planet_tab[i].getSquadron_tab()[j].getOrigin().getID_player();
						for (int l = 0 ; l < nb_players ; l++) if (this.player_tab[l].getID() == id_player) gc3.setFill(this.player_tab[l].getColor());
						gc3.fillRect(x, y, width, height);
					}
				}
			}
		}
	}
	
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
}
