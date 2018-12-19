package environnement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controller.Player;
import geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import planet.Circle_Planet;
import planet.Planet;
import planet.Square_Planet;
import view.Map;


/**
 * The Class Save_Load is used to save and load map object .
 * The S key is for saving ,  The C key is for loading.
 */
public final class Save_Load {

	/**
	 * Save_load selects which mode to launch .
	 *
	 * @param m     the actual map ( i.e represents the model of the application) 
	 * @param scene the scene  See <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html">https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html</a>
	 */
	void save_load(Map m, Scene scene) {
		scene.setOnKeyPressed(event -> {
			/*  Save call */
			if (event.getCode() == KeyCode.S) {
				this.save(m);
			}
			/* Load call */
			if (event.getCode() == KeyCode.C) {
				this.load(m, scene);
			}
		});
	}

	/**
	 * Save function which is called by save_load if key pressed S
	 *
	 * @param m the actual map ( i.e represents the model of the application) 
	 */
	void save(Map m) {
		System.out.println("******************SAVE DONE **************************");
		ObjectOutputStream object = null; // object flux 
		try {
			//output file to save the current map object
			final FileOutputStream fichier = new FileOutputStream("mon_objet.ser"); 
			object = new ObjectOutputStream(fichier);
			// Write the map in the object flux then terminate
			object.writeObject(m);
			object.flush();
			object.close();
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (object != null) {
					object.flush();
					object.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}

		}
	}


	/**
	 * Load function which is called by save_load if key pressed C
	 *
	 * @param m     the actual map ( i.e represents the model of the application) 
	 * @param scene the scene  See <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html">https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html</a>
	 */
	void load(Map m, Scene scene) {
		// object flux
		ObjectInputStream object = null;
		// the new_map loaded from file
		Map new_m = null;	

				System.out.println("******************LOAD DONE **************************");
				try {
					final FileInputStream fichier = new FileInputStream("mon_objet.ser");
					object = new ObjectInputStream(fichier);
					new_m = (Map) object.readObject();
					
					Planet tab[] = new_m.getPlanet_tab();
					Planet tab_map_param[] = m.getPlanet_tab();
					Player tabp[] = m.getPlayer_tab();
					// Set the new map ( the load one) and change the current map values
					m.setNb_planets(new_m.getNb_planets());  m.setNb_players(new_m.getNb_players());
					for (int i = 0 ; i < new_m.getNb_planets() ;i++)
						tab_map_param[i] = tab[i];
					m.setPlayer_tab(tabp);  m.setPlanet_tab(tab_map_param);
					object.close();
					
				} catch (final java.io.IOException e) {
					e.printStackTrace();
				} catch (final ClassNotFoundException e) {
					e.printStackTrace();
				} 
				finally {
					
					try {
						if (object != null) {
							object.close();
						}
					} catch (final IOException ex) {
						ex.printStackTrace();
						}

				}

			}

	

	}

