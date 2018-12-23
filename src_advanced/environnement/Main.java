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
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import planet.Planet;
import view.Map;


/**
 * The Class Main runs the application.
 */
public class Main extends Application {
	
	/**
	 * Gets the ressource path by name.
	 *
	 * @param name the name of the ressource
	 * @return the ressource path by name
	 */
	
	
	
	public static String getRessourcePathByName(String name) {
		return Main.class.getResource('/' + name).toString();
	}

	/**
	 * The main method to launch the game
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
		
	
		//Original Canvas ( Atm only for drawing planets)
		Canvas canvas = new Canvas(Map.WIDTH, Map.HEIGHT);
		root.getChildren().add(canvas);
		//CANVAS for text on planets
		Canvas canvas2 = new Canvas(Map.WIDTH, Map.HEIGHT);
		root.getChildren().add(canvas2);
		// CANVAS for ships
		Canvas canvas3 = new Canvas(Map.WIDTH, Map.HEIGHT);
		root.getChildren().add(canvas3);

		/* graphic canvas for drawing planet & original */
		
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

		// Map ressources
		Map map_mult[] = new Map[11];
		for(int i =1 ; i<11;i++)map_mult[i]=new Map(12,4);
		Map map = map_mult[1];
		
		
		
		// Mouse handler
		Mouse_Handler mouse_event = new Mouse_Handler();
		mouse_event.apply_event_mouse(map, gc, scene);
		
		// Load the "wallpaper" of the application
		Image space = new Image(getRessourcePathByName("images/wallpaper.jpg"), Map.WIDTH, Map.HEIGHT, false, false);

		// Apply the scene
		stage.setScene(scene);
		stage.show();
		
		//Save load object
		Save_Load save_load = new Save_Load();
		save_load.save_load(map, scene);
		Key_Handler key = new Key_Handler();
		key.nine_instance(scene, map, map_mult);
			
	
		
		new AnimationTimer() {	
			public void handle(long now) {
				gc.drawImage(space, 0, 0);
				gc2.clearRect(0, 0, Map.WIDTH, Map.HEIGHT); // CLEAR TXT RECT
				gc3.clearRect(0, 0, Map.WIDTH, Map.HEIGHT); // CLEAR TXT RECT
				map.draw_planets(gc); // Draw planets each tick
				map.draw_text_planets(gc2); // Draw text production of planets each tick
				map.draw_squadrons(gc3); // If squadron exists draw each tick his new position
				map.win_condition();
				map.update_ships_numbers(now); // Production function of planets's ships
				for (int i = 1 ; i < map.getNb_players() ; i++)
					((IA) map.getPlayer_tab()[i]).decisionmaking(now,map.getPlanet_tab());
			}
		}.start();
	}
}