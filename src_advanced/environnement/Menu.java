package environnement;

import geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import view.Map;



/**
 * The Class Menu.
 */
public class Menu {
	
	
	/** The value P. */
	private int valueP = 10;
	
	/** The value IA. */
	private int valueIA = 1;
	
	/** The value level IA. */
	private int valueLevelIA = 1;
	
	/** The value fov width. */
	private int valueFovWidth = 1900;
	
	/** The value fov height. */
	private int valueFovHeight = 1080;
	
	/** The value width. */
	private int valueWidth = 3840;
	
	/** The value height. */
	private int valueHeight = 2160;
	
	/** The ratio nb planet. */
	private int ratio_nb_planet = 40000;
	/** The limit number of planets. */
	private int max_nb_planets = (valueHeight*valueWidth)/ratio_nb_planet;
	/** The p pla plus. */
	private Point2D p_pla_plus = new Point2D((Map.FOV_WIDTH/2)+70, Map.FOV_HEIGHT/2-200);
	
	/** The p pla less. */
	private Point2D p_pla_less = new Point2D((Map.FOV_WIDTH/2)+120, Map.FOV_HEIGHT/2-200);
	
	/** The p ia plus. */
	private Point2D p_ia_plus = new Point2D((Map.FOV_WIDTH/2)+70, (Map.FOV_HEIGHT/2)-150);
	
	/** The p ia less. */
	private Point2D p_ia_less = new Point2D((Map.FOV_WIDTH/2)+120, (Map.FOV_HEIGHT/2)-150);
	
	/** The p level I A plus. */
	private Point2D p_levelIA_plus = new Point2D((Map.FOV_WIDTH/2)+70 , (Map.FOV_HEIGHT/2)-100);
	
	/** The p level I A less. */
	private Point2D p_levelIA_less = new Point2D((Map.FOV_WIDTH/2)+120 , (Map.FOV_HEIGHT/2)-100);
	
	/** The p level I A plus. */
	private Point2D p_fovwidth_plus = new Point2D((Map.FOV_WIDTH/2)+70 , (Map.FOV_HEIGHT/2)-50);
	
	/** The p level I A less. */
	private Point2D p_fovwidth_less = new Point2D((Map.FOV_WIDTH/2)+120 , (Map.FOV_HEIGHT/2)-50);
	
	/** The p fovheight plus. */
	private Point2D p_fovheigth_plus = new Point2D((Map.FOV_WIDTH/2)+70 , (Map.FOV_HEIGHT/2));
	
	/** The p fovheight less. */
	private Point2D p_fovheight_less = new Point2D((Map.FOV_WIDTH/2)+120 , (Map.FOV_HEIGHT/2));
	
	
	/** The p height plus. */
	private Point2D p_width_plus = new Point2D((Map.FOV_WIDTH/2)+70 , (Map.FOV_HEIGHT/2)+50);
	
	/** The p height less. */
	private Point2D p_width_less = new Point2D((Map.FOV_WIDTH/2)+120 , (Map.FOV_HEIGHT/2)+50);
	
	/** The p height plus. */
	private Point2D p_heigth_plus = new Point2D((Map.FOV_WIDTH/2)+70 , (Map.FOV_HEIGHT/2)+100);
	
	/** The p height less. */
	private Point2D p_height_less = new Point2D((Map.FOV_WIDTH/2)+120 , (Map.FOV_HEIGHT/2)+100);
	
	/** The play. */
	private Point2D play = new Point2D((Map.FOV_WIDTH/2)-50, Map.FOV_HEIGHT/2-300);
	
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
		Map.FOV_WIDTH = valueFovWidth;
		Map.FOV_HEIGHT = valueFovHeight;
		Map.WIDTH = valueWidth;
		Map.HEIGHT = valueHeight;
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

			if(p.distance(p_pla_plus)<25)
				valueP+=1;
			else if(p.distance(p_pla_less)<20)
				valueP-=1;

			// Buttons for number of IAs incre-decre-mentation
			if( p.distance(p_ia_plus)<25)
				valueIA+=1;
			else if(valueIA>1 && p.distance(p_ia_less)<20)
				valueIA-=1;

			// Buttons for level of IAs incre-decre-mentation
			if(valueLevelIA<5 && p.distance(p_levelIA_plus)<25)
				valueLevelIA+=1;
			else if (valueLevelIA>1 && p.distance(p_levelIA_less)<20)
				valueLevelIA-=1;
			
			// Buttons for fov's width resolution incre-decre-mentation
			if(valueFovWidth<1900 && p.distance(p_fovwidth_plus)<25)
				valueFovWidth+=20;
			else if (valueFovWidth>800 && p.distance(p_fovwidth_less)<20)
				valueFovWidth-=20;
			// Buttons for fov's height resolution incre-decre-mentation
			if(valueFovHeight<1080 && p.distance(p_fovheigth_plus)<25)
				valueFovHeight+=20;
			else if (valueFovHeight>600 && p.distance(p_fovheight_less)<20)
				valueFovHeight-=20;
			
			// Buttons for map's width incre-decre-mentation
			if(p.distance(p_width_plus)<25)
				valueWidth+=120;
			else if (valueFovWidth+120<=valueWidth && p.distance(p_width_less)<20)
				valueWidth-=120;
			// Buttons for map's height incre-decre-mentation
			if(p.distance(p_heigth_plus)<25)
				valueHeight+=120;
			else if (valueFovHeight+120<=valueHeight && p.distance(p_height_less)<20)
				valueHeight-=120;
			
			if(contains(play,p,60,25)) {
				if(max_nb_planets>=valueP*(valueIA+2)) {
				state_play=true;
				apply_change();
				}
				else System.out.println("capacity planets must be <200");
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
		gc.fillText("PLAY", (Map.FOV_WIDTH/2)-100, Map.FOV_HEIGHT/2-300);
		gc.strokeText("PLAY",(Map.FOV_WIDTH/2)-100, Map.FOV_HEIGHT/2-300);

		int total = valueP*(valueIA+2);
		if(max_nb_planets+1<=total) {
			gc.setStroke(Color.RED);
			gc.setLineWidth(2);
			gc.strokeLine((Map.FOV_WIDTH/2)-110, (Map.FOV_HEIGHT/2)-290, (Map.FOV_WIDTH/2)+35, (Map.FOV_HEIGHT/2)-345);
			gc.strokeLine((Map.FOV_WIDTH/2)-110, (Map.FOV_HEIGHT/2)-345, (Map.FOV_WIDTH/2)+35, (Map.FOV_HEIGHT/2)-290);
		}
	}
	
	/**
	 * Apply text planets.
	 *
	 * @param gc the gc
	 */
	private void apply_text_planets(GraphicsContext gc) {
		apply_text( gc , valueP , Map.FOV_WIDTH/2 , (Map.FOV_HEIGHT/2) - 200,24,"Planets per Player :",0);
	}
	
	/**
	 * Apply text IA.
	 *
	 * @param gc the gc
	 */
	private void apply_text_IA(GraphicsContext gc) {
		apply_text( gc , valueIA , Map.FOV_WIDTH/2 , (Map.FOV_HEIGHT/2)-150,24,"Number of IA :",0);
	}
	
	/**
	 * Apply text level IA.
	 *
	 * @param gc the gc
	 */
	private void apply_text_LevelIA(GraphicsContext gc) {
		apply_text( gc , valueLevelIA , Map.FOV_WIDTH/2 , (Map.FOV_HEIGHT/2)-100,24,"Level of IA :",0);
	}
	
	/**
	 * Apply text fov.
	 *
	 * @param gc the gc
	 */
	private void apply_text_Fov(GraphicsContext gc) {
		apply_text( gc , valueFovWidth , Map.FOV_WIDTH/2 , (Map.FOV_HEIGHT/2)-50,24,"FOV's Width :", 0);
		apply_text( gc , valueFovHeight , Map.FOV_WIDTH/2 , (Map.FOV_HEIGHT/2),24,"FOV's Height :", 0);
	}
	
	/**
	 * Apply text dimension.
	 *
	 * @param gc the gc
	 */
	private void apply_text_dimension(GraphicsContext gc) {
		apply_text( gc , valueWidth , Map.FOV_WIDTH/2 , (Map.FOV_HEIGHT/2)+50,24,"Map's Width :", 0);
		apply_text( gc , valueHeight , Map.FOV_WIDTH/2 , (Map.FOV_HEIGHT/2)+100,24,"Map's Height :", 0);
	}
	
	/**
	 * Apply text nbcurrentplanet.
	 *
	 * @param gc the gc
	 */
	private void apply_text_nbcurrentplanet(GraphicsContext gc) {
		int total = valueP*(valueIA+2);
		Color c = Color.GREEN;
		if(max_nb_planets+1<=total)c=Color.RED; 
		gc.setFill(c);
		gc.setStroke(c);
		int width = (Map.FOV_WIDTH/2);
		int height = (Map.FOV_HEIGHT/2)+150;
		int add_to_width_nb = +0;
		String nb =  String.valueOf(total);
		gc.setFont(Font.font("Helvetica", FontWeight.NORMAL, 24));
		gc.fillText(nb, width+add_to_width_nb, height);
		gc.strokeText(nb,width+add_to_width_nb,height);
	}
	
	/**
	 * Apply text nbplanet possible.
	 *
	 * @param gc the gc
	 */
	private void apply_text_nbplanet_possible(GraphicsContext gc) {
		int total = valueP*(valueIA+2);
		Color c = Color.GREEN;
		if(max_nb_planets+1<=total)c=Color.RED; 
			gc.setFill(c);
			gc.setStroke(c);
		max_nb_planets = (valueHeight*valueWidth)/ratio_nb_planet;
		if(max_nb_planets>120)max_nb_planets=120;
		int width = (Map.FOV_WIDTH/2);
		int height = (Map.FOV_HEIGHT/2)+150;
		int add_to_width_nb = +30;
		String nb =  String.valueOf(max_nb_planets);
		gc.setFont(Font.font("Helvetica", FontWeight.NORMAL, 24));
		gc.fillText("Max planet possible :", width-230, height);
		gc.fillText("  /  "+nb, width+add_to_width_nb, height);
		gc.strokeText("  /  "+nb,width+add_to_width_nb,height);
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
	 * @param add_to_width_nb the add to width nb
	 */
	private void apply_text(GraphicsContext gc , int value , int width , int height,int sizefont,String text,int add_to_width_nb) {
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.WHITE);
		String nb =  String.valueOf(value);
		gc.setFont(Font.font("Helvetica", FontWeight.NORMAL, sizefont));
		gc.fillText(text, width-230, height);
		gc.fillText(nb, width+add_to_width_nb, height);
		gc.strokeText(nb,width+add_to_width_nb,height);
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 40));
		gc.fillText("+", width+70, height+2);
		gc.fillText("-", width+120, height+2);
	}
	
	/**
	 * Apply menu.
	 *
	 * @param gc the gc
	 * @param menu the menu
	 */
	void apply_menu(GraphicsContext gc,Image menu) {
		gc.drawImage(menu, 0, 0);
		apply_text_nbplanet_possible(gc);
		apply_text_nbcurrentplanet(gc);
		apply_text_planets(gc);
		apply_text_play(gc);
		apply_text_IA(gc);
		apply_text_LevelIA(gc);
		apply_text_Fov(gc);
		apply_text_dimension(gc);
	}
	
	
}
