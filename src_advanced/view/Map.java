package view;

import java.io.Serializable;
import java.util.Random;

import controller.IA;
import controller.Player;
import geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import planet.Planet;
import spaceship.SpaceShip;




/**
 * The Class Map represents each informations we need to draw the actual content of the game .
 */
public class Map implements Serializable {
	private boolean state_game = true;
	/**  The Serial version UID. */
	private static final long serialVersionUID = 1L;

	/** The Constant WIDTH. */
	public  static int WIDTH = 3600;
	
	/** The Constant HEIGHT. */
	public  static int HEIGHT = 1840;
	
	/** The Constant FOV_WIDTH. */
	public  static int FOV_WIDTH = 1900;
	
	/** The Constant FOV_HEIGHT. */
	public  static int FOV_HEIGHT = 1080;
	
	/** The Constant squad_dash_limit representing the maximum number of dashed lines rendered. */
	public  static int squad_dash_limit = 20;
	
	/** The point representing the camera's position */
	private Point2D camera;
	
	/** The min dist. */
	private int min_dist = 200;
	
	/** The planet tab. */
	private Planet planet_tab[];
	
	/** The player tab. */
	private Player player_tab[];
	
	/** The nb planets. */
	private int nb_planets;
	
	/** The nb players. */
	private int nb_players;
	
	/** The P pirate. */
	private Point2D P_pirate = new Point2D(500,0);
	
	/** The pirate. */
	private Planet pirate=new Planet(1,5,"Circle","Circle",P_pirate,666,1);
	
	/** The last time. */
	private long last_time;
	
	/**
	 * Instantiates a new map.
	 *
	 * @param nb_planets the nb planets
	 * @param nb_players the nb players
	 * @param level_IA the level IA
	 */
	public Map(int nb_planets, int nb_players,int level_IA) {
		this.planet_tab = new Planet[nb_planets];
		this.player_tab = new Player[nb_players];
		this.nb_planets = nb_planets;
		this.nb_players = nb_players;
		this.add_planets();
		this.add_players(level_IA);
		this.camera = new Point2D(0,0);
		
		
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
	 * Gets the nb players.
	 *
	 * @return the nb players
	 */
	public int getNb_players() { return nb_players; }
	
	/**
	 * Sets the nb players.
	 *
	 * @param nb_players the new nb players
	 */
	public void setNb_players(int nb_players) { this.nb_players = nb_players; }
	
	
	/**
	 * Gets the camera.
	 *
	 * @return the camera
	 */
	public Point2D getCamera() { return camera; }

	/**
	 * Sets the camera.
	 *
	 * @param camera the new camera
	 */
	public void setCamera(Point2D camera) { this.camera = camera; }

	/**
	 * Adds the players.
	 *
	 * @param level the level
	 */
	public void add_players(int level) {
		player_tab[0] = new Player(0);
		for (int i = 1 ; i < this.nb_players ; i++) {
			player_tab[i] = new IA(i,level);
		}
	}
	
	/**
	 * Checks if is location valid.
	 *
	 * @param p           the point p to test location
	 * @param size_factor the size factor
	 * @param planet_type the planet type
	 * @return true, if is location valid
	 */
	public boolean is_location_valid(Point2D p, double size_factor, String planet_type) {
		
		//Out of bounds detection
		int half_size = 0;
		if (planet_type == "Square") {
			half_size = (int)(Planet.sq_width*size_factor)/2;
		}
		else if (planet_type == "Rectangle") {
			half_size = Math.max((int)(Planet.re_width*size_factor), (int)(Planet.re_height*size_factor)/2);
		}
		else if (planet_type == "Circle") {
			half_size = (int)(Planet.ci_width*size_factor)/2;
		}
		else if (planet_type == "Oval") {
			half_size = Math.max((int)(Planet.ov_width*size_factor), (int)(Planet.ov_height*size_factor)/2);
		}
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
	 * Randomly picks a return the name of a shape.
	 *
	 * @return the name of picked shape
	 */
	private String random_shape() {
		String res = "Square";
		Random gen = new Random();
	
		switch (gen.nextInt(4)) {
		case 1:
			res = "Circle";
			break;
		case 2:
			res = "Rectangle";
			break;
		case 3:
			res = "Oval";
			break;
		}
		
		return res;
	}
	
	/**
	 * Adds the planets to the map.
	 */
	public void add_planets() {
		int id = -1;

		Random gen = new Random();
		
		double size_factor = 0.0f;
		double limit = 0.9f;
		if(nb_planets/15>=1) {limit=0.6f;min_dist-=70;}
		else if(nb_planets/30>=1) {limit=0.4f;}
		while (size_factor < limit) size_factor = gen.nextDouble();
		int nb_ship = gen.nextInt(20);
		
		String planet_shape = random_shape();
		String spaceship_shape = random_shape();

		/* Each planet random location, random size, random form, random spaceships */
		for (int i = 0 ; i < this.nb_planets ; i++) {
			Point2D point = new Point2D(gen.nextInt(Map.WIDTH - this.min_dist), gen.nextInt(Map.HEIGHT - this.min_dist));
			if(is_location_valid(point, size_factor, planet_shape)) {
					planet_tab[i] = new Planet(1/size_factor, nb_ship, planet_shape, spaceship_shape, point, id, size_factor);
					id++;
					if (id >= this.nb_players) {
						size_factor = 0.0f;
						while (size_factor < limit) size_factor = gen.nextDouble();
						id = -1;
						nb_ship = gen.nextInt(20);
						planet_shape = random_shape();
						spaceship_shape = random_shape();
					}
			}
			else i--;
		}
	}
	
	/**
	 * Draw pirates.
	 *
	 * @param gc the gc
	 */
	//APPARITION VAISSEAUX PIRATES ( peut endommager les planetes mais pas les capturer)
	public void draw_pirates(GraphicsContext gc) {
		Random gen = new Random(); 
		if(gen.nextDouble()<0.005) {  // possible to set it to 0.002 if u want rare			
			Point2D p = pirate.getCenter();
			if(gen.nextDouble()<0.5)
				p.replace(gen.nextInt(WIDTH), 0);
			else
				p.replace(gen.nextInt(WIDTH),HEIGHT );
			pirate.setCenter(p);
			Planet target = this.planet_tab[gen.nextInt(this.getNb_planets())];
			if(target.getNb_ship()>0)
				pirate.attack(target);
			pirate.setNb_ship(gen.nextInt(6));
		}
		
	}
	
	/**
	 * Draw planets.
	 *
	 * @param gc the GraphicsContext we use to draw the planets and their informations
	 */
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
			planet.draw(gc, camera);
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
			gc2.fillText(nb_ships, this.planet_tab[i].getCenter().getX()-camera.getX(), this.planet_tab[i].getCenter().getY()-camera.getY()+6);
			gc2.strokeText(nb_ships, this.planet_tab[i].getCenter().getX()-camera.getX(), this.planet_tab[i].getCenter().getY()-camera.getY()+6);
			gc2.setFont(Font.font("Helvetica", FontWeight.NORMAL, 12));
			if (planet_tab[i].getID_player() == 0) {
				String selected = String.valueOf((int)planet_tab[i].getSelected())+"%";
				gc2.fillText(selected, this.planet_tab[i].getCenter().getX()-camera.getX(), this.planet_tab[i].getCenter().getY()-camera.getY()+18);
				gc2.strokeText(selected, this.planet_tab[i].getCenter().getX()-camera.getX(), this.planet_tab[i].getCenter().getY()-camera.getY()+18);
			}
		}
	}
	
	/**
	 * Draw squadrons.
	 *
	 * @param gc3 the GraphicsContext we use to draw the spaceships
	 */
	public void draw_squadrons(GraphicsContext gc3) {
		//for pirate spaceship
		int squad_count = 0;
		for (int i = 0 ; i < this.nb_planets ; i++)
			for (int j = 0 ; j < this.planet_tab[i].getNb_squadron() ; j++)
				if (this.planet_tab[i].getSquadron_tab()[j].getSize() > 0) squad_count += 1;
		
		for(int j = 0 ; j  < this.pirate.getNb_squadron() ; j++) {
			SpaceShip tab[] = this.pirate.getSquadron_tab()[j].getSpaceship_tab();
			for (int k = 0; k < this.pirate.getSquadron_tab()[j].getSize(); k++) {
				gc3.setFill(Color.GREY);
				gc3.setStroke(Color.GREY);
				tab[k].draw(gc3, camera);
				if (tab[k].isLeader() && squad_count < squad_dash_limit)
					draw_dashed_line(gc3, tab[k].getCenter(), pirate.getSquadron_tab()[j].getTarget().getCenter());
			}
			this.pirate.getSquadron_tab()[j].squadron_move(5,planet_tab);
			
		}
	
		for (int i = 0 ; i < this.nb_planets ; i++) {
			for (int j = 0 ; j < this.planet_tab[i].getNb_squadron() ; j++) {
				SpaceShip tab[] = this.planet_tab[i].getSquadron_tab()[j].getSpaceship_tab();
				for (int k = 0; k < this.planet_tab[i].getSquadron_tab()[j].getSize(); k++) {
					int id_player = this.planet_tab[i].getSquadron_tab()[j].getOrigin().getID_player();
					for (int l = 0 ; l < nb_players ; l++) if (this.player_tab[l].getID() == id_player) {
						gc3.setFill(this.player_tab[l].getColor());
						gc3.setStroke(this.player_tab[l].getColor());
					}
					tab[k].draw(gc3, camera);
					if (tab[k].isLeader() && squad_count < squad_dash_limit)
						draw_dashed_line(gc3, tab[k].getCenter(), planet_tab[i].getSquadron_tab()[j].getTarget().getCenter());
				}
				this.planet_tab[i].getSquadron_tab()[j].squadron_move(this.planet_tab[i].getSquadron_tab()[j].getSpeed(),planet_tab);
			}
		}
	}
	
	/**
	 * Draws a dashed line from a to b.
	 * 
	 * @param gc3 the GraphicsContext we use to draw the spaceships
	 * @param a the Point2D a
	 * @param b the Point2D b
	 */
	public void draw_dashed_line(GraphicsContext gc3, Point2D a, Point2D b) {
		int dash_lenght = 20;
		gc3.setLineDashes(dash_lenght);
		gc3.setLineWidth(2);
		gc3.strokeLine(a.getX()-camera.getX(), a.getY()-camera.getY(), b.getX()-camera.getX(), b.getY()-camera.getY());
	}
	
	/**
	 * Draws the "Selected" menu in top-left corner of the window.
	 * 
	 * @param gc4 the GraphicsContext we use to draw the UI elements
	 */
	public void draw_selected(GraphicsContext gc4) {
		gc4.setFill(Color.WHITE);
		gc4.setStroke(Color.WHITE);
		gc4.setLineWidth(2);
		gc4.strokeText("Selected : ", 35, 60);
		gc4.fillText("Selected : ", 35, 60);
		int sq_count = 0, re_count = 0, ci_count = 0, ov_count = 0;
		int count;
		
		for (int i = 0 ; i < nb_planets ; i++) {
			count = (int)(planet_tab[i].getNb_ship()*planet_tab[i].getSelected()/100);
			if (planet_tab[i].getID_player() == 0) {
				if (planet_tab[i].getShips_shape().compareTo("Square") == 0) sq_count += count;
				else if (planet_tab[i].getShips_shape().compareTo("Rectangle") == 0) re_count += count;
				else if (planet_tab[i].getShips_shape().compareTo("Circle") == 0) ci_count += count;
				else if (planet_tab[i].getShips_shape().compareTo("Oval") == 0) ov_count += count;
			}
		}
		
		gc4.fillRect(35, 85, 20, 20);
		gc4.strokeText(" : " + sq_count, 85, 100);
		gc4.fillText(" : " + sq_count, 85, 100);

		gc4.fillRect(35, 125, 30, 10);
		gc4.strokeText(" : " + re_count, 85, 140);
		gc4.fillText(" : " + re_count, 85, 140);

		gc4.fillOval(35, 155, 20, 20);
		gc4.strokeText(" : " + ci_count, 85, 180);
		gc4.fillText(" : " + ci_count, 85, 180);

		gc4.fillOval(35, 195, 10, 30);
		gc4.strokeText(" : " + ov_count, 85, 220);
		gc4.fillText(" : " + ov_count, 85, 220);
	}
	
	/**
	 * Draws an arrow for each planet the player owns outside the FOV
	 * 
	 * @param gc4 the GraphicsContext we use to draw the UI elements
	 */
	public void draw_domination_table(GraphicsContext gc4) {
		int planet_count[] = new int[nb_players+1];
		for (int i = -1 ; i < nb_players ; i++) {
			for (int j = 0 ; j < nb_planets ; j++) {
				if (planet_tab[j].getID_player() == i) {
					planet_count[i+1] += 1;
				}
			}
		}
		int where_we_at = -250;
		for (int i = 0 ; i < nb_players+1 ; i++) {
			Paint color;
			if (i > 0) color = player_tab[i-1].getColor();
			else color = Color.GRAY;
			int width = (int)((((double)planet_count[i]/(double)nb_planets)*100)*5);
			gc4.setFill(color);
			gc4.fillRect(FOV_WIDTH/2+where_we_at, 35, width, 35);
			where_we_at += width;
		}
		
		gc4.setStroke(Color.BLACK);
		gc4.setLineWidth(5);
		gc4.strokeLine(FOV_WIDTH/2-250, 35, FOV_WIDTH/2+250, 35);
		gc4.strokeLine(FOV_WIDTH/2-250, 35, FOV_WIDTH/2-250, 70);
		gc4.strokeLine(FOV_WIDTH/2+250, 35, FOV_WIDTH/2+250, 70);
		gc4.strokeLine(FOV_WIDTH/2-250, 70, FOV_WIDTH/2+250, 70);
	}
	
	/**
	 * Updates ships numbers of each planet.
	 *
	 * @param now the time now in nanoseconds
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
	 * Checks win conditions and apply win effect if so.
	 */
	public void win_condition() {
		boolean state = true;
		for(int i = 0 ; i<nb_players;i++) {
			for(int j = 0 ; j<nb_planets;j++) {
				if(planet_tab[j].getID_player()!=i)
					state=false;
				if(j==nb_planets-1 && state && state_game) {
					System.out.println("Vous avez gagn� cette map Vous pouvez sauvegarder"
							+ " ou charger une nouvelle partie comme bon vous semble =)");
					state_game = false;
				}
			}
		}
	}
}
