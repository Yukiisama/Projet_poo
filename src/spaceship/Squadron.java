package spaceship;

public final class Squadron {
	private  int size = 6;
	private SpaceShip tab[];
	private boolean decollated=false;
	private int cpt=0;
	// Contiendra toutes les fonctions permettant de contrôler les spaceships
	public Squadron() {
		this.tab = new SpaceShip[size];
		this.decollated = decollated;
	}
	public  int getSize() {
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
		if(cpt==size-1)return false;
		this.tab[this.cpt]=s;
		cpt++;
		return true;
	}
	
	
}
