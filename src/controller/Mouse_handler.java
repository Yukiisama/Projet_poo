package controller;


import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import planet.Planet;
import view.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Mouse_Handler.
 */
public final class Mouse_Handler {
	
	/** The clicked one. */
	private Planet clicked_one;
	
	/** The clicked. */
	private boolean clicked;
	
	/** The clicked y. */
	private int clicked_y;
	

	/**
	 * Apply event mouse.
	 *
	 * @param map   the map
	 * @param gc4   the gc 4
	 * @param scene the scene
	 */
	public void apply_event_mouse (Map map, GraphicsContext gc4, Scene scene){
		
		scene.setOnMousePressed(event -> {
			int size = map.getNb_planets();
			Planet planet_tab[] = map.getPlanet_tab();
			boolean clicked_on_planet = false;
			for (int i = 0 ; i < size ; i++) {
				int id_player = planet_tab[i].getID_player();
				if (planet_tab[i].is_inside(new Point2D(event.getX(), event.getY()))) { // If clicked on a planet
					clicked_on_planet = true;
					if (id_player == 0) { // If it's a planet of its own, select it
						this.clicked_one = planet_tab[i];
						this.clicked = true;
						this.clicked_y = (int)event.getY();
					}
					else if (id_player != 0) { // Otherwise, if we have planets selected, we're attacking
						for (int j = 0 ; j < size ; j++) if (planet_tab[j].getNb_ship() > 0 && planet_tab[j].getSelected()*100/planet_tab[j].getNb_ship() > 0)
							planet_tab[j].attack(planet_tab[i]);
					}
				}
			}
			if (!clicked_on_planet) { for (int j = 0 ; j < size ; j++) { planet_tab[j].setSelected(0); } } // If clicked elsewhere, unselect every planet
		});
		
		scene.setOnMouseDragged(event -> {
			if (this.clicked) {
				int deltaY = (int)event.getY() - this.clicked_y;
				int new_selected = -deltaY*100/this.clicked_one.getHeight();
				if (new_selected >= 0 && new_selected <= 100) this.clicked_one.setSelected(new_selected);
				else if (new_selected < 0) this.clicked_one.setSelected(0);
			}
		});
		
		scene.setOnMouseReleased(event -> { this.clicked = false; });
	}
}
