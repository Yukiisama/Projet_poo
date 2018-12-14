package spaceship;

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
	
	public void conquer_planet (Map m, int caller,boolean selected[]) {
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
				
				
				
			}
		}
	}
	
	

}
