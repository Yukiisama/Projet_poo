package environnement;
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
import javafx.stage.Stage;
import planet.Planet;
import view.Map;

public class Main extends Application {
	private final static int WIDTH = 800;
	private final static int HEIGHT = 600;
	
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
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);
		
		//CANVAS for text on planets
		Canvas canvas2 = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas2);
		// graphic canvas for drawing planet & original
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		gc.setFill(Color.BISQUE);
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(1);
		// graphic canvas for drawing text 
		GraphicsContext gc2 = canvas2.getGraphicsContext2D();
		gc2.setFont(Font.font("Helvetica", FontWeight.NORMAL, 24));
		gc2.setFill(Color.RED);
		gc2.setStroke(Color.RED);
		gc2.setLineWidth(1);
		
		// Map ressources
		Map map = new Map(HEIGHT, WIDTH, 30, 1);
		map.add_planets(map.getNb_planets());
		Planet tab[] = map.getPlanet_tab();
		
		
		// STAGE INIT
		Image space = new Image(getRessourcePathByName("images/wallpaper.jpg"), WIDTH, HEIGHT, false, false);
		stage.setScene(scene);
		stage.show();
		
		new AnimationTimer() {
			int i =0;
			public void handle(long now) {
				gc.drawImage(space, 0, 0);
				gc2.clearRect(0, 0, WIDTH, HEIGHT); // CLEAR TXT RECT
				map.draw_Planets(map.getPlanet_tab(), gc); // Draw planets each tick
				map.draw_text_planets(gc2);
				map.onUpdate(now);
				
				//v.render(gc);
				//p.render(gc);

			}
		}.start();
		
		
		
		
	}

}