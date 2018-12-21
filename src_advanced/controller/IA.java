package controller;

import java.util.Random;

import planet.Planet;

public class IA extends Player {

	/** The Serial version UID */
	private static final long serialVersionUID = 1L;

	Random gen = new Random();
	private int level = 2; // get from option
	
	public IA(int id) {
		super(id);
	}
	
	
	public void choose_one(int prod,Planet from,Planet tab[]){
			int size = tab.length;
			if (prod < 5) return;
			for(int i = 0 ; i<size;i++){
				if(tab[i]!=null && tab[i].getID_player()!=ID) {
					from.attack(tab[i]);return;}
			}
			
		}

		public void choose_multiple(int length,Planet tab[]){
			int iterations = gen.nextInt(length);
			Planet choose_planet = tab[gen.nextInt(length)];
			while(iterations>0){
				while(choose_planet.getID_player()!=this.ID)choose_planet = tab[gen.nextInt(length)];
				if(choose_planet!=null)
				choose_one(choose_planet.getNb_ship(),choose_planet,tab);
				iterations--;
			}
		}

		/*public int coupdavance(){
			comparer 5 ou 10 prochains coups en comparant le score , 
			prendre le chemin avec le plus grand score ( comparer 3 chemins).
		}*/
		public void decisionmaking(long now,Planet tab[]){
			boolean state = false;
			for(int i = 0 ; i<tab.length;i++) {
				if (tab[i].getID_player()==ID)
					state =true;
				if(i==tab.length-1 && state == false)
					return;
			}
			
			Planet OneofMine = tab[gen.nextInt(tab.length)];
			while(OneofMine.getID_player()!=ID) OneofMine = tab[gen.nextInt(tab.length)]; 
				switch(level) {
				case 1:
					if(gen.nextDouble()>0.010)
						//do not attack this tick
						return;
					break;
				case 2:
					if(gen.nextDouble()>0.50)
						//do not attack this tick
						return;
					break;
				default:break;
				}
				if(level==1 && OneofMine != null)
					choose_one(OneofMine.getNb_ship(),OneofMine,tab);
				if(level==2)
					choose_multiple(tab.length,tab);
				if(level==3)
					choose_multiple(tab.length,tab);
			
				
		

		//L'ajout de plus serait de pouvoir calculer quelques coups Ã  l'avance et prendre le max des score Ã©tablis
		//Cependant comment dÃ©finir ce qu'est le meilleur coup ?

		}

}
