package environnement;

import geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.Map;


/**
 * The Class Menu.
 */
public class Menu {
	
	/** The limit number of planets. */
	private int max_nb_planets = 200;
	
	/** The value P. */
	private int valueP = 10;
	
	/** The value IA. */
	private int valueIA = 1;
	
	/** The value level IA. */
	private int valueLevelIA = 1;
	
	/** The p pla plus. */
	private Point2D p_pla_plus = new Point2D((Map.FOV_WIDTH/2)+30, Map.FOV_HEIGHT/2);
	
	/** The p pla less. */
	private Point2D p_pla_less = new Point2D((Map.FOV_WIDTH/2)+80, Map.FOV_HEIGHT/2);
	
	/** The p ia plus. */
	private Point2D p_ia_plus = new Point2D((Map.FOV_WIDTH/2)+30, (Map.FOV_HEIGHT/2)-50);
	
	/** The p ia less. */
	private Point2D p_ia_less = new Point2D((Map.FOV_WIDTH/2)+80, (Map.FOV_HEIGHT/2)-50);
	
	/** The p level I A plus. */
	private Point2D p_levelIA_plus = new Point2D((Map.FOV_WIDTH/2)+30 , (Map.FOV_HEIGHT/2)+50);
	
	/** The p level I A less. */
	private Point2D p_levelIA_less = new Point2D((Map.FOV_WIDTH/2)+80 , (Map.FOV_HEIGHT/2)+50);
	
	/** The play. */
	private Point2D play = new Point2D((Map.FOV_WIDTH/2)-80, Map.FOV_HEIGHT/2-130);
	
	/** The state play. */
	private boolean state_play = false;
	
	/**
	 * Contains.
	 *
	 * @param p the p
	 * @param mouse the mouse
	 * @param width the width
	 * @param height the height
	 * @return true, if successful
	 */
	private boolean contains(Point2D p,Point2D mouse,int width , int height) {
		return Math.abs(p.getX() - mouse.getX()) < width && Math.abs(p.getY() - mouse.getY()) < height;
	}
	
	/**
	 * Apply change.
	 */
	private void apply_change() {
		Main.Planet = valueP;
		Main.IA = valueIA;
		Main.levelIA =  valueLevelIA;
	}
	
	
	/**
	 * Increment.
	 *
	 * @param scene the scene
	 * @return true, if successful
	 */
	public boolean increment(Scene scene) {
		scene.setOnMousePressed(event2 -> {
			
			Point2D p = new Point2D((int)event2.getX(), (int)event2.getY());

			// Buttons for planets per IA/player/neutrals incre-decre-mentation | Also checking is the total number of planets won't hit the limit
			int total = valueP*(valueIA+3); // +3 cause of player + neutrals + IA we wanna add
			if(total<=max_nb_planets && p.distance(p_pla_plus)<25)
				valueP+=1;
			else if(valueP>1 && p.distance(p_pla_less)<20)
				valueP-=1;

			// Buttons for number of IAs incre-decre-mentation
			if(total<=max_nb_planets && p.distance(p_ia_plus)<25)
				valueIA+=1;
			else if(valueIA>1 && p.distance(p_ia_less)<20)
				valueIA-=1;

			// Buttons for level of IAs incre-decre-mentation
			if(valueLevelIA<5 && p.distance(p_levelIA_plus)<25)
				valueLevelIA+=1;
			else if (valueLevelIA>1 && p.distance(p_levelIA_less)<20)
				valueLevelIA-=1;
			
			if(contains(play,p,60,25)) {
				state_play=true;
				apply_change();
			}
			
		});
		return state_play;
	}
	
	/**
	 * Apply text play.
	 *
	 * @param gc the gc
	 */
	private void apply_text_play(GraphicsContext gc) {
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
		gc.fillText("PLAY", (Map.FOV_WIDTH/2)-150, Map.FOV_HEIGHT/2-100);
		gc.strokeText("PLAY",(Map.FOV_WIDTH/2)-150, Map.FOV_HEIGHT/2-100);
	}
	
	/**
	 * Apply text planets.
	 *
	 * @param gc the gc
	 */
	private void apply_text_planets(GraphicsContext gc) {
		apply_text( gc , valueP , Map.FOV_WIDTH/2 , Map.FOV_HEIGHT/2,24,"Planets per Player :");
	}
	
	/**
	 * Apply text IA.
	 *
	 * @param gc the gc
	 */
	private void apply_text_IA(GraphicsContext gc) {
		apply_text( gc , valueIA , Map.FOV_WIDTH/2 , (Map.FOV_HEIGHT/2)-50,24,"Number of IA :");
	}
	
	/**
	 * Apply text level IA.
	 *
	 * @param gc the gc
	 */
	private void apply_text_LevelIA(GraphicsContext gc) {
		apply_text( gc , valueLevelIA , Map.FOV_WIDTH/2 , (Map.FOV_HEIGHT/2)+50,24,"Level of IA :");
	}
	
	/**
	 * Apply text.
	 *
	 * @param gc the gc
	 * @param value the value
	 * @param width the width
	 * @param height the height
	 * @param sizefont the sizefont
	 * @param text the text
	 */
	private void apply_text(GraphicsContext gc , int value , int width , int height,int sizefont,String text) {
		String nb =  String.valueOf(value);
		gc.setFont(Font.font("Helvetica", FontWeight.NORMAL, sizefont));
		gc.fillText(text, width-230, height);
		gc.fillText(nb, width, height);
		gc.strokeText(nb,width,height);
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 40));
		gc.fillText("+", width+30, height);
		gc.fillText("-", width+80, height);
	}
	
	/**
	 * Apply menu.
	 *
	 * @param gc the gc
	 * @param menu the menu
	 */
	void apply_menu(GraphicsContext gc,Image menu) {
		gc.drawImage(menu, 0, 0);
		apply_text_play(gc);
		apply_text_planets(gc);
		apply_text_IA(gc);
		apply_text_LevelIA(gc);
	}
	
	
}
