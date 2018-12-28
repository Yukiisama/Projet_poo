package controller;

import environnement.Save_Load;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import planet.Planet;
import view.Map;


/**
 * The Class Key_Handler.
 */

public final class Key_Handler {
	
	/**
	 * Nine instance.
	 *
	 * @param scene the scene
	 * @param map the map
	 * @param map_mult the map mult
	 * @param scene2 the scene 2
	 * @param stage the stage
	 */
	public void event_keyboard (Scene scene ,Map map ,Scene scene2 , Stage stage) {
		
		scene.setOnKeyPressed(event -> {
			Save_Load save_load = new Save_Load();
			//Fast Save call
			if (event.getCode() == KeyCode.S) {
				save_load.save(map,true);
			}
			//Selection one
			if (event.getCode() == KeyCode.X) {
				save_load.save(map,false);
			}
			/* Load fast call */
			if (event.getCode() == KeyCode.C) {
				save_load.load(map, scene,true);
			}
			//Selection one
			if (event.getCode() == KeyCode.V) {
				save_load.load(map, scene,false);
			}
			
				});

	}
	
	
	
}
