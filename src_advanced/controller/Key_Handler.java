package controller;

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
	public void nine_instance (Scene scene ,Map map ,Map map_mult[],Scene scene2 , Stage stage) {
		switch_map(map,map_mult[2]);
		scene.setOnKeyPressed(event -> {
	if (event.getCode() == KeyCode.NUMPAD1) 
		switch_map(map,map_mult[2]);
	if (event.getCode() == KeyCode.NUMPAD2) 
		switch_map(map,map_mult[3]);
	if (event.getCode() == KeyCode.NUMPAD3) 
		switch_map(map,map_mult[4]);
	if (event.getCode() == KeyCode.NUMPAD4) 
		switch_map(map,map_mult[5]);
	if (event.getCode() == KeyCode.NUMPAD5) 
		switch_map(map,map_mult[6]);
	if (event.getCode() == KeyCode.NUMPAD6) 
		switch_map(map,map_mult[7]);
	if (event.getCode() == KeyCode.NUMPAD7) 
		switch_map(map,map_mult[8]);
	if (event.getCode() == KeyCode.NUMPAD8) 
		switch_map(map,map_mult[9]);
	if (event.getCode() == KeyCode.NUMPAD9) 
		switch_map(map,map_mult[10]);	
		});

	}
	
	/**
	 * switch_map.
	 *
	 * @param map the map
	 * @param map2 the map 2
	 */
	private void switch_map(Map map , Map map2) { 
		Planet tab[] = map.getPlanet_tab();
		Planet tab2[] = map2.getPlanet_tab();
		for (int i = 0 ; i < map2.getNb_planets() ;i++) tab[i] = tab2[i];
		map.setNb_planets(map2.getNb_planets());  map.setNb_players(map2.getNb_players());
	}
	
}
