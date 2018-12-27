package controller;


import geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import planet.Planet;
import view.Map;



/**
 * The Class Mouse_Handler handles all interractions with mouse .
 */
public final class Mouse_Handler {
	
	/** The clicked one design the planet selected by the user. */
	private Planet clicked_one;
	
	/** The clicked_on_planet is a boolean state to indicate if a clic has occured. */
	private boolean clicked_on_planet;

	/** The clicked_elsewhere is a boolean state to indicate if a clic has occured. */
	private boolean clicked_elsewhere;
	
	/** The clicked y represents the ordered (Y) coordinates. */
	private int clicked_y;
	
	/** The clicked y represents the ordered (X) coordinates. */
	private int clicked_x;
	

	/**
	 * Apply the event mouse which is basically actually used for the selection of planets and to proceed to the attack.
	 *
	 * @param map   the actual map ( i.e represents the model of the application) 
	 * @param gc4   the GraphicsContext we use for drawing the planets and their informations
	 * @param scene the scene  See <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html">https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html</a>
	 */
	public void apply_event_mouse (Map map, GraphicsContext gc4, Scene scene){
		
		scene.setOnMousePressed(event -> {
			int size = map.getNb_planets();
			Planet planet_tab[] = map.getPlanet_tab();
			boolean clicked_on_planet = false;
			for (int i = 0 ; i < size ; i++) {
				int id_player = planet_tab[i].getID_player();
				if (planet_tab[i].is_inside(new Point2D((int)event.getX()+map.getCamera().getX(), (int)event.getY()+map.getCamera().getY()))) { // If clicked on a planet
					clicked_on_planet = true;
					if (id_player == 0) { // If it's a planet of its own, select it
						this.clicked_one = planet_tab[i];
						this.clicked_on_planet = true;
						this.clicked_y = (int)event.getY();
					}
					else if (id_player != 0) { // Otherwise, if we have planets selected, we're attacking
						for (int j = 0 ; j < size ; j++) if (planet_tab[j].getNb_ship() > 0 && planet_tab[j].getSelected()*100/planet_tab[j].getNb_ship() > 0)
							planet_tab[j].attack(planet_tab[i]);
					}
				}
			}
			if (!clicked_on_planet) {
				for (int j = 0 ; j < size ; j++) { planet_tab[j].setSelected(0); }
				this.clicked_elsewhere = true;
				this.clicked_y = (int)event.getY();
				this.clicked_x = (int)event.getX();
			} // If clicked elsewhere, unselect every planet
		});
		
		scene.setOnMouseDragged(event -> {
			if (this.clicked_on_planet) {
				int deltaY = this.clicked_y - (int)event.getY();
				double ratio = (double)deltaY/(double)this.clicked_one.getHeight();
				double new_selected = ratio*100;
				if (new_selected > 100) new_selected = 100;
				if (new_selected >= 0) this.clicked_one.setSelected(new_selected);
				else if (new_selected < 0) this.clicked_one.setSelected(0);
			}
			else if (this.clicked_elsewhere) {
				int deltaY = this.clicked_y - (int)event.getY();
				int deltaX = this.clicked_x - (int)event.getX();
				Point2D new_camera = new Point2D(map.getCamera().getX(), map.getCamera().getY());
				if (new_camera.getX()+deltaX >= 0 && new_camera.getX()+deltaX < Map.WIDTH - Map.FOV_WIDTH)
					new_camera.setX(map.getCamera().getX()+deltaX);
				if ( new_camera.getY()+deltaY >= 0 && new_camera.getY()+deltaY < Map.HEIGHT - Map.FOV_HEIGHT)
					new_camera.setY(map.getCamera().getY()+deltaY);
				map.setCamera(new_camera);
				this.clicked_y = (int)event.getY();
				this.clicked_x = (int)event.getX();
			}
		});
		
		scene.setOnMouseReleased(event -> {
			this.clicked_on_planet = false;
			this.clicked_elsewhere = false;
		});
	}
}
