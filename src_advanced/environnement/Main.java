package environnement;
import controller.IA;
import controller.Key_Handler;
import controller.Mouse_Handler;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import view.Map;

/**
 * The Class Main runs the application.
 */
public class Main extends Application {
	
	/** The menu. */
	private boolean menu = true;
	
	/** The ia. */
	public static int IA = 1;
	
	/** The Planet. */
	public static int Planet = 20;
	
	/** The level IA. */
	public static int levelIA = 1;
	/**
	 * Gets the ressource path by name.
	 *
	 * @param name the name of the ressource
	 * @return the ressource path by name
	 */
	
	private static String getRessourcePathByName(String name) {
		return Main.class.getResource('/' + name).toString();
	}
	
	/**
	 * The main method to launch the game.
	 *
	 * @param args the main arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage stage) {
		/* Stage initialisation */
		stage.setTitle("Projet Poo");
		stage.setResizable(false);
		Group root = new Group();
		Scene scene = new Scene(root);
		//Original Canvas (drawing planets)
		Canvas canvas = new Canvas(Map.FOV_WIDTH, Map.FOV_HEIGHT);
		root.getChildren().add(canvas);
		//CANVAS for text on planets
		Canvas canvas2 = new Canvas(Map.FOV_WIDTH, Map.FOV_HEIGHT);
		root.getChildren().add(canvas2);
		// CANVAS for ships
		Canvas canvas3 = new Canvas(Map.FOV_WIDTH, Map.FOV_HEIGHT);
		root.getChildren().add(canvas3);
		// CANVAS for UI
		Canvas canvas4 = new Canvas(Map.FOV_WIDTH, Map.FOV_HEIGHT);
		root.getChildren().add(canvas4);
		// graphic canvas for drawing planets
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		gc.setFill(Color.BISQUE);
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(1);
		// graphic canvas for drawing text 
		GraphicsContext gc2 = canvas2.getGraphicsContext2D();
		gc2.setFont(Font.font("Helvetica", FontWeight.NORMAL, 24));
		gc2.setFill(Color.BLACK);
		gc2.setStroke(Color.BLACK);
		gc2.setTextAlign(TextAlignment.CENTER);
		gc2.setLineWidth(1);
		// graphic canvas for drawing squadrons
		GraphicsContext gc3 = canvas3.getGraphicsContext2D();
		gc3.setFont(Font.font("Helvetica", FontWeight.NORMAL, 24));
		gc3.setFill(Color.GREEN);
		gc3.setStroke(Color.GREEN);
		gc3.setLineWidth(1);
		// graphic canvas for drawing UI elements
		GraphicsContext gc4 = canvas4.getGraphicsContext2D();
		gc4.setFont(Font.font("Helvetica", FontWeight.NORMAL, 30));
		gc4.setFill(Color.WHITE);
		gc4.setStroke(Color.WHITE);
		gc4.setLineWidth(1);

	
	
		Map map = new Map(Planet,(IA+1),levelIA);
		
		// Mouse handler
		Mouse_Handler mouse_event = new Mouse_Handler();
		mouse_event.apply_event_mouse(map, gc, scene);
		
		// Load the "wallpaper" and menu "wallpaper" of the application
		Image space = new Image(getRessourcePathByName("images/wallpaper.jpg"), Map.FOV_WIDTH, Map.FOV_HEIGHT, false, false);
		Image menu_img = new Image(getRessourcePathByName("images/menu.jpg"), Map.FOV_WIDTH, Map.FOV_HEIGHT, false, false);
		
		//Menu initialisation
		Group root_menu =new Group();
		Canvas canvas_menu = new Canvas(Map.FOV_WIDTH, Map.FOV_HEIGHT);
		GraphicsContext gc_menu = canvas_menu.getGraphicsContext2D();
		gc_menu.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		gc_menu.setFill(Color.WHITE);
		gc_menu.setStroke(Color.WHITE);
		gc_menu.setLineWidth(1);
		root_menu.getChildren().add(canvas_menu);
		Scene scene2 = new Scene(root_menu);
		// Apply the scene
		if(menu) stage.setScene(scene2);
		else stage.setScene(scene);
		stage.show();
		
		//Save load object
		Key_Handler key = new Key_Handler();
		key.event_keyboard(scene, map, scene2,stage);
		Menu m = new Menu();	
		
		
		//Tick actions
		new AnimationTimer() {	
			public void handle(long now) {
				if(menu) {
					m.apply_menu(gc_menu, menu_img);
					menu = !m.increment(scene2);
					if(!menu) {
						//Init after you have selected which map you want 
						//This will be executed once
						Map map2= new Map(Planet*(IA+2),(IA+1),levelIA);
						map.setNb_planets(Planet*(IA+2));  map.setNb_players(IA+1);
						map.setPlanet_tab(map2.getPlanet_tab());  map.setPlayer_tab(map2.getPlayer_tab());
						//Apply the scene of the game
						stage.setWidth(Map.FOV_WIDTH);
						stage.setHeight(Map.FOV_HEIGHT);
						stage.setScene(scene);
						stage.show();}
					}
				else{
				gc.drawImage(space, 0, 0);
				gc2.clearRect(0, 0, Map.FOV_WIDTH, Map.FOV_HEIGHT); // CLEAR TXT RECT
				gc3.clearRect(0, 0, Map.FOV_WIDTH, Map.FOV_HEIGHT); // CLEAR TXT RECT
				gc4.clearRect(0, 0, Map.FOV_WIDTH, Map.FOV_HEIGHT); // CLEAR TXT RECT
				map.draw_planets(gc); // Draw planets each tick
				map.draw_text_planets(gc2); // Draw text production of planets each tick
				map.draw_pirates(gc3);
				map.draw_squadrons(gc3); // If squadron exists draw each tick his new position
				map.draw_domination_table(gc4);
				map.draw_selected(gc4);
				map.win_condition();
				map.update_ships_numbers(now); // Production function of planets's ships
				for (int i = 1 ; i < map.getNb_players() ; i++)
					((IA) map.getPlayer_tab()[i]).decisionmaking(now,map.getPlanet_tab());
				}
			}
		}.start();
	}
}