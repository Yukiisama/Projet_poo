package controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import planet.Planet;
import view.Map;

/**
 * The Class Key_Handler.
 */

public final class Key_Handler {

	// Contiendra les fonctions de listen/handle du clavier
	
	/*  Switch beetween 9 maps */
	
	
	/**
	 * Nine instance.
	 *
	 * @param scene the scene
	 * @param map the map
	 * @param map_mult the map mult
	 */
	public void nine_instance (Scene scene ,Map map ,Map map_mult[]) {
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
	public void switch_map(Map map , Map map2) { 
		Planet tab[] = map.getPlanet_tab();
		Planet tab2[] = map2.getPlanet_tab();
		for (int i = 0 ; i < map2.getNb_planets() ;i++) tab[i] = tab2[i];
		map.setNb_planets(map2.getNb_planets());  map.setNb_players(map2.getNb_players());
	}
	
}
