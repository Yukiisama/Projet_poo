package view;


import java.awt.Rectangle;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import planet.Planet;
import planet.Rect_planet;
import planet.Square_Planet;
import spaceship.SpaceShip;
import spaceship.Squadron;
import spaceship.Square_ss;

public class Map {
	private  double size_w = 40;
	private  double size_h = 40;
	
	private int height ; private int width;
	private Planet planet_tab[];
	private int nb_planets = 0;
	private int nb_joueurs;
	private long last_time;
	private Squadron squadron_tab[];
	private int squadron_cpt =0;
	
	
	
	
	
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
		this.squadron_tab = new Squadron[10000];
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
	
	public Squadron[] getSquadron_tab() {
		return squadron_tab;
	}
	public void setSquadron_tab(Squadron[] squadron_tab) {
		this.squadron_tab = squadron_tab;
	}
	public void add_planets(int nb_p,String ships_type,String planet_type,int ID_player) {
		
		// if ID_player is -1 planet is neutral no player
		int i=this.nb_planets;
		int cpt = 0;
		System.out.println(i );
		
		Random gen = new Random();
		while(cpt<nb_p) {
			Point2D point = new Point2D(0,0);
			size_w = 30 +gen.nextInt(60);
			size_h = size_w +gen.nextInt(20);
			
			
			if(ships_type=="Square") {
				point =point.add(gen.nextInt(this.width - (int)this.size_w),gen.nextInt(this.height - (int)this.size_w));
			}else if (ships_type == "Rect") {
				point =point.add(gen.nextInt(this.width - (int)this.size_w),gen.nextInt(this.height - (int)this.size_h));
			}
			if( Is_Location_Valid(point,this.planet_tab)) {
				if(planet_type == "Square")
					planet_tab[i] = new Square_Planet(1.5f , 0, ships_type,planet_type, point, ID_player, this.size_w, this.size_w);
				if(planet_type== "Rect")
					planet_tab[i] = new Rect_planet(1.5f , 0, ships_type,planet_type, point, ID_player, this.size_w, this.size_h);
				
				//System.out.println(planet_tab[i].getCentre());
				
				this.nb_planets++;
				
				i++;cpt++;
				
			}
		 
			
		}
		
		
	}
	
		   public boolean do_intersect (Point2D p,Point2D pos,Planet plan) {
			    int tx1 = (int) p.getX();
			    int ty1 = (int) p.getY();
			    int rx1 = (int) pos.getX();
			    int ry1 = (int)pos.getY();
			    
			    // We check there width height size *2
			    // to verify intersection AND minimum distance between planets ( min_dist = size * 2)
			    
			    long tx2 = tx1; tx2 += this.size_w * 2;
			    long ty2 = ty1; ty2 += this.size_h * 2;
			    long rx2 = rx1; rx2 += plan.getWidth() * 2;
			    long ry2 = ry1; ry2 += plan.getHeight() * 2;
			    if (tx1 < rx1) tx1 = rx1;
			    if (ty1 < ry1) ty1 = ry1;
			    if (tx2 > rx2) tx2 = rx2;
			    if (ty2 > ry2) ty2 = ry2;
			    tx2 -= tx1;
			    ty2 -= ty1;
			    // tx2,ty2 will never overflow (they will never be
			    // larger than the smallest of the two source w,h)
			    // they might underflow, though...
			    if (tx2 < Integer.MIN_VALUE) tx2 = Integer.MIN_VALUE;
			    if (ty2 < Integer.MIN_VALUE) ty2 = Integer.MIN_VALUE;
			    Rectangle test =new Rectangle(tx1, ty1, (int) tx2, (int) ty2);
			    return !test.isEmpty();
			    }
	
	public boolean Is_Location_Valid(Point2D p,Planet planet_tab[]) {
		if(nb_planets == 0)return true;
		
		for (int i =0 ; i< this.nb_planets;i++) {
			if(planet_tab[i]!=null) {
				Point2D pos = planet_tab[i].getCentre();
				if(do_intersect(p,pos,planet_tab[i]))return false;
			}
		}
		return true;
	}
	
	public void choose_color (int ID_player,GraphicsContext g) {
		switch (ID_player) {
		case 1: g.setFill(Color.YELLOW);break;
		case 2: g.setFill(Color.LIGHTBLUE);break;
		case 3: g.setFill(Color.HOTPINK);break;
		case 4: g.setFill(Color.LIGHTSLATEGREY);break;
		default:g.setFill(Color.LIGHTSALMON);break;
	}
	}
	
	public void draw_Planets(Planet planet_tab[],GraphicsContext gc) {
		
		for (int i = 0 ; i<this.nb_planets;i++) {
			if(planet_tab[i]!=null) {
				Planet p = planet_tab[i];
		choose_color(p.getID_player(), gc);
		
		
			
		if(p.getPlanet_type() == "Square") {
			double x = p.getCentre().getX();
			double y = p.getCentre().getY();
			double width = p.getWidth();
			double height = p.getHeight();
			gc.fillRect(x,y,width,height);
		}
		if(p.getPlanet_type() == "Rect") {
			double x = p.getCentre().getX();
			double y = p.getCentre().getY();
			double width = p.getWidth();
			double height = p.getHeight();
			gc.fillRect(x,y,width,height);
		}
	    
	 
	}
		}
	}
	
	public void draw_text_planets(GraphicsContext gc2) {
		for (int i =0 ; i<this.getNb_planets();i++) {
			if(planet_tab[i]!= null ) {
				String pointsText =  String.valueOf( planet_tab[i].getNb_ship());
				if(planet_tab[i].getPlanet_type()=="Square" || planet_tab[i].getPlanet_type()=="Rect") {
				gc2.fillText( pointsText, planet_tab[i].getCentre().getX() + planet_tab[i].getWidth()/2,
						planet_tab[i].getCentre().getY()+ planet_tab[i].getHeight()/2,planet_tab[i].getWidth()/2);
				//gc2.strokeText( pointsText,planet_tab[i].getCentre().getX() + planet_tab[i].getWidth()/2, 
					//planet_tab[i].getCentre().getY() + planet_tab[i].getHeight()/2);
				}
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
		
	public void form_squadron(Planet p) {
		Squadron new_squadron = new Squadron();
		Point2D center = new Point2D(p.getWidth(),p.getHeight());
		if(p.getNb_ship()>6) {
			if(p.getShips_type()=="Square") {
				for(int i=0;i<6;i++) {
					center =center.add(5,5);
					SpaceShip s = new Square_ss(center,p.getID_player(), 4, 10, 10);
					new_squadron.add_spaceship(s);
					p.setNb_ship(p.getNb_ship()-1);
				}
				this.squadron_tab[this.squadron_cpt]=new_squadron;
				this.squadron_cpt++;
			}
		
		}
	
		
	}
	
	
	public void draw_squadron(GraphicsContext gc3 ,Squadron s) {
		
			SpaceShip tab[] = s.getTab();
			for(int i = 0 ; i<s.getSize();i++) {
				if(tab[i]!=null && tab[i].getType()=="Square") {
					double x = tab[i].getCenter().getX();
					double y = tab[i].getCenter().getY();
					double width = tab[i].getWidth();
					double height = tab[i].getHeight();
					choose_color(tab[i].getID_player(), gc3);
					gc3.fillRect(x, y, width, height);
				}
			}
		}
	
}