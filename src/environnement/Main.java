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
		Canvas canvas2 = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas2);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		GraphicsContext gc2 = canvas2.getGraphicsContext2D();
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		gc.setFill(Color.BISQUE);
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(1);
		gc2.setFont(Font.font("Helvetica", FontWeight.NORMAL, 24));
		gc2.setFill(Color.RED);
		gc2.setStroke(Color.RED);
		stage.setScene(scene);
		stage.show();
		Image space = new Image(getRessourcePathByName("images/wallpaper.jpg"), WIDTH, HEIGHT, false, false);
		Map map = new Map(HEIGHT, WIDTH, 10, 1);
		map.add_planets(map.getNb_planets());
		Planet tab[] = map.getPlanet_tab();
		map.draw_Planets(map.getPlanet_tab(), gc);
		
		new AnimationTimer() {
			int i =0;
			public void handle(long arg0) {
				gc.drawImage(space, 0, 0);
				gc2.clearRect(0, 0, WIDTH, HEIGHT);
				map.draw_Planets(map.getPlanet_tab(), gc);
				for (int i =0 ; i<map.getNb_planets();i++) {
					if(tab[i]!= null) {
						String pointsText =  String.valueOf( tab[i].getNb_ship());
						
						gc2.fillText( pointsText, tab[i].getCentre().getX() + tab[i].getWidth()/6, tab[i].getCentre().getY() + tab[i].getHeight()/2 +tab[i].getHeight()/4 );
						gc2.strokeText( pointsText, tab[i].getCentre().getX() + tab[i].getWidth()/6, tab[i].getCentre().getY() + tab[i].getHeight()/2 + tab[i].getHeight()/4);
					}
				}
				i++;
				tab[0].setNb_ship(i);
				
				//v.render(gc);
				//p.render(gc);

			}
		}.start();
		
		
		
		
	}

}