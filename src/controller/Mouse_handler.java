package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import planet.Planet;
import view.Map;
public final class Mouse_handler {
	// Contiendra les fonctions de listen/handle de la souris
	private int WIDTH;private int HEIGHT;
	
	public Mouse_handler(int wIDTH, int hEIGHT) {
		
		WIDTH = wIDTH;
		HEIGHT = hEIGHT;
	}
	
	public void falsify (boolean selected[], int size) {
		for (int i = 0; i < size; i++) {
			selected[i] = false;
		}
	}
	public boolean is_someone_selected (boolean selected[], int size) {
		for (int i = 0; i < size; i++) {
			if(selected[i])
				return selected[i];
		}
		return false;
	}
	
	//A déplacer bien evidemment dans la classe mouse_handler mais en phase de test 
	public void apply_event_mouse (Map map, Planet tab[],boolean selected[],GraphicsContext gc4,Scene scene){
			EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				int size = map.getNb_planets();
				if (e.isPrimaryButtonDown()) {
					for (int i = 0; i < map.getNb_planets(); i++) {
						double x = tab[i].getCentre().getX();
						double y = tab[i].getCentre().getY();
						double width = tab[i].getWidth();
						double height = tab[i].getHeight();
						if (e.getX() >= x && e.getX() <= x + width && e.getY() >= y && e.getY() <= y + height) {
							int player = tab[i].getID_player();
							if (selected[i] == false && player == 1)
								selected[i] = true;
							else if (selected[i] == false && player != 1 && is_someone_selected(selected, size)) {
								System.out.println("go violer la planete : " + i);
								falsify(selected, size);
								gc4.clearRect(0, 0, WIDTH, HEIGHT);
							} else {
								selected[i] = false;
								gc4.clearRect(x - 10, y - 10, width + 20, height + 20);
							}
						}

					}

				}

			}
			};

			scene.setOnMouseDragged(mouseHandler);
			scene.setOnMousePressed(mouseHandler);
	
}
}
