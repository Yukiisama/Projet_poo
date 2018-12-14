package spaceship;

import java.util.List;

import javafx.geometry.Point2D;
import planet.Planet;
import planet.Rect_planet;
import view.Map;

public final class Squadron {
	private int size = 6;
	private SpaceShip tab[];
	private boolean decollated = false;
	private int cpt = 0;
	private int player;
	private boolean attack_done = true;
	private int target = 0;

	// Contiendra toutes les fonctions permettant de contrôler les spaceships
	public Squadron(int target,int player) {
		this.tab = new SpaceShip[size];
		this.decollated = decollated;
		this.target = target;
		this.player= player;
	}
	
	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getSize() {
		return size;
	}

	public SpaceShip[] getTab() {
		return tab;
	}

	public void setTab(SpaceShip[] tab) {
		this.tab = tab;
	}

	public boolean isDecollated() {
		return decollated;
	}

	public void setDecollated(boolean decollated) {
		this.decollated = decollated;
	}

	public boolean add_spaceship(SpaceShip s) {
		if (cpt == size)
			return false;
		this.tab[this.cpt] = s;
		cpt++;
		return true;
	}

	public boolean isAttack_done() {
		return attack_done;
	}

	public void setAttack_done(boolean attack_done) {
		this.attack_done = attack_done;
	}
	
	public void conquer_planet (Map m, int caller,boolean selected[],int indice) {
		if(this.attack_done) {
			Planet tab[] = m.getPlanet_tab();
			Planet tar = tab[this.target];
			
			int to_substract = tar.getNb_ship()-this.size; 
			if(to_substract >=0)
				tar.setNb_ship(to_substract);
			else {
		        Planet p = new Rect_planet(1.5f, 0, "Rect", "Rect", tar.getCentre(),1, tar.getWidth(), tar.getHeight());
		       
				tab[this.target] = p;
				m.setPlanet_tab(tab);
				Squadron tabs[] = m.getSquadron_tab();
				if(tabs[indice]!=null)
					tabs[indice]=null;
				
				
			}
		}
	}
	

	public boolean check_contain(Map m, double x , double y) {
		Planet tab[] = m.getPlanet_tab(); boolean state = true;
		for(Planet p : tab) {
			if(p!=null)
			state ^= p.pt_contains_polygon( (int)x ,(int)y);
			if(state==false)
				return false;
		}
		return state;
	}
	
	public void squadron_move(Map m ,int WIDTH,int HEIGHT) {
		int x_from = 0, y_from = 0;
		double gap = 1.5;
		Planet tplan[]= m.getPlanet_tab();
		Planet goal = tplan[target];
		int x_goal = (int) goal.getCentre().getX();
		int y_goal = (int) goal.getCentre().getY();
		
		for(int i =0 ; i<size;i++) {
			if(tab[i]!=null) {
					
					 x_from = (int) tab[i].getCenter().getX();
					 y_from = (int) tab[i].getCenter().getY();
					 x_goal = (int) goal.getCentre().getX();
					 y_goal = (int) goal.getCentre().getY();
					
					if (x_from == x_goal && y_from == y_goal) {
						//clear
					}
					
					
					if( x_from <x_goal && check_contain(m,tab[i].getCenter().getX()+gap , tab[i].getCenter().getY()))
						tab[i].setCenter(tab[i].getCenter().add(gap,0));
					else if (x_from>x_goal && check_contain(m,tab[i].getCenter().getX()-gap , tab[i].getCenter().getY()))
						tab[i].setCenter(tab[i].getCenter().add(-gap,0));
					
					else if( y_from <y_goal && check_contain(m,tab[i].getCenter().getX(), tab[i].getCenter().getY() +gap))
						tab[i].setCenter(tab[i].getCenter().add(0,+gap));
					else if (y_from>y_goal && check_contain(m,tab[i].getCenter().getX(), tab[i].getCenter().getY() -gap))
						tab[i].setCenter(tab[i].getCenter().add(0,-gap));
					else if (y_from>y_goal && check_contain(m,tab[i].getCenter().getX() -gap, tab[i].getCenter().getY() - gap))
						tab[i].setCenter(tab[i].getCenter().add(-gap,-gap));
					else if (y_from<y_goal && check_contain(m,tab[i].getCenter().getX()+ gap, tab[i].getCenter().getY() + gap))
						tab[i].setCenter(tab[i].getCenter().add(+gap,+gap));
					
						
				
				 	
			}
		}
		
	}

	
}

