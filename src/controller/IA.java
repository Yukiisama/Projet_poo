package controller;

import java.util.Random;

import planet.Planet;

public class IA extends Player {
	Random gen = new Random();
	private int level = 1; // get from option
	
	private final int LEVELMAX = 3;
	public IA(int id) {
		super(id);
	}
	
	
	//Avec une bonne méthode pour évaluer un score on pourrait déjà avoir une bonne IA
	public int evaluate_score(int target_prod,int prod){
				return target_prod-prod;
		}
	
	public int[] insert_descending(int score[],int key) {
		int i = 0;
		for(int j = 1 ; j<score.length;j++) {
			key= score[j];
			for(i= j-1 ; i>=0 && (score[i]<key);i--) {
				score[i+1]=score[i];
			}
			score[i+1]=key;
		}
		
		return score;
	}
	
	
	
	public void choose_one(int prod,Planet from){
			int size = tab_planets_not_own.length;
			int score[]= new int[size];
			
			for(int i = 0 ; i<size;i++){
				if(tab_planets_not_own[i]!=null)
				score =insert_descending(score,evaluate_score(tab_planets_not_own[i].getNb_ship(),prod));
			}
			Planet p = tab_planets_not_own[0];
			int to_search =score[gen.nextInt(size/LEVELMAX)]; // premiere case meilleur "score"
			for(int i = 0 ; i<size;i++) {
				if(tab_planets_not_own[i]!=null && evaluate_score(tab_planets_not_own[i].getNb_ship(),prod)==to_search)
					p = tab_planets_not_own[i];
			}
			
			from.attack(p);
		}

		public void choose_multiple(int length){
			int iterations = gen.nextInt(length);
			while(iterations>0){
				Planet choose_planet = tab_planets_own[gen.nextInt(length)];
				if(choose_planet!=null)
				choose_one(choose_planet.getNb_ship(),choose_planet);
				iterations--;
			}
		}

		/*public int coupdavance(){
			comparer 5 ou 10 prochains coups en comparant le score , 
			prendre le chemin avec le plus grand score ( comparer 3 chemins).
		}*/
		public void decisionmaking(long now){
			Planet OneofMine = tab_planets_own[gen.nextInt(tab_planets_own.length)];
			/*long passed_time = (now - this.last_time)/1000;
			this.last_time = now;
			if(passed_time ==3000000){ //3sec*/
				switch(level) {
				case 1:
					if(gen.nextDouble()>0.03)
						//do not attack this tick
						return;
					break;
				case 2:
					if(gen.nextDouble()>0.10)
						//do not attack this tick
						return;
					break;
				default:break;
				}
				if(level==1 && OneofMine != null)
					choose_one(OneofMine.getNb_ship(),OneofMine);
				if(level==2)
					choose_multiple((tab_planets_not_own.length/2));
				if(level==3)
					choose_multiple(tab_planets_not_own.length);
				
			
		//}

		//L'ajout de plus serait de pouvoir calculer quelques coups à l'avance et prendre le max des score établis
		//Cependant comment définir ce qu'est le meilleur coup ?

		}

}
