package environnement;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import planet.Planet;
import spaceship.SpaceShip;
import spaceship.Squadron;
import view.Map;
import controller.Mouse_handler;

public class Main extends Application {
	private final static int WIDTH = 1600;
	private final static int HEIGHT = 900;

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

		// Original Canvas ( Atm only for drawing planets)
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);

		// CANVAS for text on planets
		Canvas canvas2 = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas2);

		// CANVAS for ships
		Canvas canvas3 = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas3);

		// CANVAS for Selection planet
		Canvas canvas4 = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas4);
		// graphic canvas for drawing planet & original
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));

		gc.setFill(Color.BISQUE);
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(1);
		// graphic canvas for drawing text
		GraphicsContext gc2 = canvas2.getGraphicsContext2D();
		gc2.setFont(Font.font("Helvetica", FontWeight.NORMAL, 24));
		gc2.setTextAlign(TextAlignment.CENTER);
		gc2.setTextBaseline(VPos.CENTER);
		gc2.setFill(Color.RED);
		gc2.setStroke(Color.RED);
		gc2.setLineWidth(1);

		// graphic canvas for drawing text
		GraphicsContext gc3 = canvas3.getGraphicsContext2D();
		gc3.setFont(Font.font("Helvetica", FontWeight.NORMAL, 24));

		gc3.setFill(Color.GREEN);
		gc3.setStroke(Color.GREEN);
		gc3.setLineWidth(1);

		// graphic canvas for selected planet
		GraphicsContext gc4 = canvas4.getGraphicsContext2D();
		gc4.setFont(Font.font("Helvetica", FontWeight.NORMAL, 24));

		gc4.setFill(Color.WHITE);
		gc4.setStroke(Color.WHITE);
		gc4.setLineWidth(3);
		// Map ressources
		Map map = new Map(HEIGHT, WIDTH, 0, 1);
		map.add_planets(13, "Square", "Square", 1);
		map.add_planets(7, "Rect", "Rect", 2);
		map.add_planets(5, "Rect", "Rect", 1);
		map.add_planets(5, "Square", "Square", -1);
		Planet tab[] = map.getPlanet_tab();
		boolean selected[] = new boolean[map.getNb_planets()];
		Mouse_handler mouse_event = new Mouse_handler(WIDTH, HEIGHT);
		mouse_event.falsify(selected, map.getNb_planets());
		mouse_event.apply_event_mouse(map, tab, selected, gc4, scene);

		// STAGE INIT
		Image space = new Image(getRessourcePathByName("images/wallpaper.jpg"), WIDTH, HEIGHT, false, false);
		stage.setScene(scene);
		stage.show();

		new AnimationTimer() {
			int i = 0;

			public void handle(long now) {
				gc.drawImage(space, 0, 0);
				gc2.clearRect(0, 0, WIDTH, HEIGHT); // CLEAR TXT RECT
				map.draw_Planets(map.getPlanet_tab(), gc, selected, gc4); // Draw planets each tick
				map.draw_text_planets(gc2);

				Squadron squads[] = map.getSquadron_tab();
				for (int j = 0; j < 10000; j++) {
					if (squads[j] != null) {
						/*for (int i = 0; i < squads[j].getSize(); i++) {
							SpaceShip tabsq[] = squads[j].getTab();
							SpaceShip s = tabsq[i];
							// if(s!=null)
							// System.out.println(s.getCenter().getX());
						}*/
						map.draw_squadron(gc3, squads[j]);
						squads[j].conquer_planet(map,1,selected);
						map.onUpdate(now);
						squads[j]=null;
					}
				
					map.onUpdate(now);
					// v.render(gc);
					// p.render(gc);

				}
			}
		}.start();

	}

}