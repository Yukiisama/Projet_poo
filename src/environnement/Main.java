package environnement;

import java.util.Iterator;

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

public class Main extends Application {
	private final static int WIDTH = 1900;
	private final static int HEIGHT = 1080;
	
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
		
		
		
		new AnimationTimer() {
			public void handle(long arg0) {
				gc.drawImage(space, 0, 0);
				//v.render(gc);
				//p.render(gc);

			}
		}.start();
		
	}

}