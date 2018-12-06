package view;


import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import planet.Planet;
import planet.Square_Planet;

public class Map {
	private final double size = 40;
	private final double min_dist = size*2;
	private int height ; private int width;
	private Planet planet_tab[];
	private int nb_planets;
	private int nb_joueurs;
	private long last_time;
	
	
	
	
	/**
	 * @param height
	 * @param width
	 * @param planet_tab
	 * @param nb_planets
	 * @param nb_joueurs
	 */
	public Map(int height, int width, int nb_planets, int nb_joueurs) {
		this.height = height;
		this.width = width;
		this.planet_tab = new Planet[10000];
		this.nb_planets = nb_planets;
		this.nb_joueurs = nb_joueurs;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public Planet[] getPlanet_tab() {
		return planet_tab;
	}
	public void setPlanet_tab(Planet planet_tab[]) {
		this.planet_tab = planet_tab;
	}
	public int getNb_planets() {
		return nb_planets;
	}
	public void setNb_planets(int nb_planets) {
		this.nb_planets = nb_planets;
	}
	public int getNb_joueurs() {
		return nb_joueurs;
	}
	public void setNb_joueurs(int nb_joueurs) {
		this.nb_joueurs = nb_joueurs;
	}
	
	public void add_planets(int nb_p) {
		
		int i=0;
		
		Random gen = new Random();
		while(i<nb_p) {
			Point2D point = new Point2D(0,0);
			
			point =point.add(gen.nextInt(this.width - (int)this.size),gen.nextInt(this.height - (int)this.size));
			
			if( Is_Location_Valid(point,this.planet_tab)) {
				planet_tab[i] = new Square_Planet(1.5f , 0, "Square", point, 1, this.size, this.size);
				
				//System.out.println(planet_tab[i].getCentre());
				
				this.nb_planets++;
				
				i++;
				
			}
		 
			
		}
		
		
	}
	
	public boolean Is_Location_Valid(Point2D p,Planet planet_tab[]) {
		if(nb_planets == 0)return true;
		
		for (int i =0 ; i< this.nb_planets;i++) {
			if(planet_tab[i]!=null) {
				Point2D pos = planet_tab[i].getCentre();
				
				if(pos.distance(p)<this.min_dist) {
					return false;
				}
			}
			
		}
		
		return true;
		
	}
	
	public void draw_Planets(Planet planet_tab[],GraphicsContext gc) {
		
		for (int i = 0 ; i<this.nb_planets;i++) {
			if(planet_tab[i]!=null) {
				Planet p = planet_tab[i];
		//System.out.println(p);
		
		double x = p.getCentre().getX();
		double y = p.getCentre().getY();
		double width = p.getWidth();
		double height = p.getHeight();
		
		gc.fillRect(x,y,width,height);

	    
	 
	}
		}
	}
	
	public void draw_text_planets(GraphicsContext gc2) {
		for (int i =0 ; i<this.getNb_planets();i++) {
			if(planet_tab[i]!= null) {
				String pointsText =  String.valueOf( planet_tab[i].getNb_ship());
				
				gc2.fillText( pointsText, planet_tab[i].getCentre().getX() + planet_tab[i].getWidth()/6, planet_tab[i].getCentre().getY() + planet_tab[i].getHeight()/2 +planet_tab[i].getHeight()/4 );
				gc2.strokeText( pointsText,planet_tab[i].getCentre().getX() + planet_tab[i].getWidth()/6, planet_tab[i].getCentre().getY() + planet_tab[i].getHeight()/2 +planet_tab[i].getHeight()/4);
			}
		}
	}
	
	public void onUpdate(long now) {
		
		long passed_time = (now - this.last_time)/1000;
		this.last_time = now;
		
		int i = 0;
		while (i < this.getNb_planets() && this.planet_tab[i] != null) {
			this.planet_tab[i].setLeft_time(this.planet_tab[i].getLeft_time()-passed_time);
			if (this.planet_tab[i].getLeft_time() <= 0) {
				long new_left_time = this.planet_tab[i].getProduction_time() * (long)this.planet_tab[i].getRate_production();
				long new_left_time_dot = (long)(this.planet_tab[i].getProduction_time() * (this.planet_tab[i].getRate_production() - (long)this.planet_tab[i].getRate_production()));
				this.planet_tab[i].setLeft_time(new_left_time + new_left_time_dot);
				this.planet_tab[i].setNb_ship(this.planet_tab[i].getNb_ship()+1);
			}
			i++;
		}
		
	}
}
