package controller;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import planet.Planet;


/**
 * The Class IA.
 */
public class IA extends Player {

	/** The seriaVersionUID. */
	private static final long serialVersionUID = 1L;
	private int LEVEL_MAX = 2;
	/** The gen. */
	Random gen = new Random();
	
	/** The level. */
	private int level = 5; // get from option
	
	/**
	 * Instantiates a new ia.
	 *
	 * @param id the id
	 */
	public IA(int id) {
		super(id);
	}
	
	/**
	 * Choose one.
	 *
	 * @param prod the production count of ships
	 * @param from the origin planet
	 * @param tab  the tab of planets in the current map
	 */
	public void choose_one(int prod,Planet from,Planet tab[]){
			if (prod < 5) return;
			int index= ThreadLocalRandom.current().nextInt(tab.length);
			if(tab[index]!=null && tab[index].getID_player()!=ID) {
				from.attack(tab[index]);return;}
		}
		/**
		 * Choose multiple.
		 *
		 * @param length the length
		 * @param tab    the tab
		 */
		public void choose_multiple(int length,Planet tab[]){
			int iterations = ThreadLocalRandom.current().nextInt(length);
			Planet choose_planet = tab[ThreadLocalRandom.current().nextInt(length)];
			while(iterations>0){
				while(choose_planet.getID_player()!=this.ID)choose_planet = tab[ThreadLocalRandom.current().nextInt(length)];
				if(choose_planet!=null && level<4)
					choose_one(choose_planet.getNb_ship(),choose_planet,tab);
				else
					choose_one_and_focus_player1(choose_planet.getNb_ship(),choose_planet,tab);
				iterations--;
			}
		}
	
		/**
		 * Still player 1 alive.
		 *
		 * @param tab the tab
		 * @return true, if successful
		 */
		public boolean still_player1_alive(Planet tab[]) {
			for(Planet p : tab) {
				if(p.getID_player()==0)return true;
			}
			return false;
		}
	
		/**
		 * Choose one and focus player 1.
		 *
		 * @param prod the space_ship_production of the planet
		 * @param from the from
		 * @param tab the tab
		 */
		public void choose_one_and_focus_player1(int prod,Planet from,Planet tab[]){
			if (prod < 5) return;
			int index= ThreadLocalRandom.current().nextInt(tab.length);
			if(still_player1_alive(tab)) {
				while(tab[index].getID_player()!=0) index= ThreadLocalRandom.current().nextInt(tab.length);
				from.attack(tab[index]);return;
			}
			else if(tab[index]!=null && tab[index].getID_player()!=ID) {
				from.attack(tab[index]);return;}
		}

		/**
		 * Decisionmaking.
		 *
		 * @param now the current time
		 * @param tab the tab
		 */
		public void decisionmaking(long now,Planet tab[]){
			boolean state = false;
			for(int i = 0 ; i<tab.length;i++) {
				if (tab[i].getID_player()==ID)state =true;
				if(i==tab.length-1 && state == false)return;
			}
			Planet OneofMine = tab[ThreadLocalRandom.current().nextInt(tab.length)];
			while(OneofMine.getID_player()!=ID) OneofMine = tab[ThreadLocalRandom.current().nextInt(tab.length)]; 
				switch(level) {
				case 1:
					if(gen.nextDouble()>0.010)
						//do not attack this tick
						return;
					break;
				case 2:
					if(gen.nextDouble()>0.025)
						//do not attack this tick
						return;
					break;
				case 3:
					if(gen.nextDouble()>0.050)
						//do not attack this tick
						return;
					break;
				case 4:
					if(gen.nextDouble()>0.050)
						//do not attack this tick
						return;
				default:break;
				}
				if(level==1 || level==2 && OneofMine != null)
					choose_one(OneofMine.getNb_ship(),OneofMine,tab);
				else if(level>2 && level<LEVEL_MAX+1)
					choose_multiple(tab.length,tab);
		}
}