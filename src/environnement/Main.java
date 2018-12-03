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
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		gc.setFill(Color.BISQUE);
		gc.setStroke(Color.RED);
		gc.setLineWidth(1);
		stage.setScene(scene);
		stage.show();
		Image space = new Image(getRessourcePathByName("images/wallpaper.jpg"), WIDTH, HEIGHT, false, false);
		Map map = new Map(HEIGHT, WIDTH, 10, 1);
		map.add_planets(map.getNb_planets());
		map.draw_Planets(map.getPlanet_tab(), root);
		Planet tab[] = map.getPlanet_tab();
		Planet e = tab[0];
		e.setNb_ship(5);
		
		new AnimationTimer() {
			public void handle(long arg0) {
				gc.drawImage(space, 0, 0);
				
				
				
					
				//v.render(gc);
				//p.render(gc);

			}
		}.start();
		
		
		
		
	}

}