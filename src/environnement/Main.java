package environnement;
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

public class Main extends Application {
	
	public static String getRessourcePathByName(String name) {
		return Main.class.getResource('/' + name).toString();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		stage.setTitle("Pooooooo");
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

		// graphic canvas for drawing planet & original
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
		Map map = new Map(20, 4);
		
		// Mouse handler
		Mouse_Handler mouse_event = new Mouse_Handler();
		mouse_event.apply_event_mouse(map, gc, scene);
		
		
		// STAGE INIT
		Image space = new Image(getRessourcePathByName("images/wallpaper.jpg"), Map.WIDTH, Map.HEIGHT, false, false);
		stage.setScene(scene);
		stage.show();
		
		new AnimationTimer() {	
			public void handle(long now) {
				gc.drawImage(space, 0, 0);
				gc2.clearRect(0, 0, Map.WIDTH, Map.HEIGHT); // CLEAR TXT RECT
				map.draw_planets(gc); // Draw planets each tick
				map.draw_text_planets(gc2);
				map.draw_squadrons(gc3);
				map.update_ships_numbers(now);
				
				//v.render(gc);
				//p.render(gc);

			}
		}.start();
	}
}