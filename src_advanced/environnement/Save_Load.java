package environnement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import controller.Player;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import planet.Planet;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.Map;



/**
 * The Class Save_Load is used to save and load map object .
 * The S key is for saving ,  The C key is for loading.
 */
public final class Save_Load {

	
 	/**
 	 * Choose path.
 	 *
 	 * @param primaryStage the primary stage
 	 * @param save the save
 	 * @return the string
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
 	public String choose_Path(final Stage primaryStage, boolean save) throws IOException{
		 	File file;
	        FileChooser fileChooser = new FileChooser();
	      //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser");
            fileChooser.getExtensionFilters().add(extFilter);
            if(save)
            	 file = fileChooser.showSaveDialog(primaryStage);
            else
            	file = fileChooser.showOpenDialog(primaryStage);
            if(file!=null)return file.toString();
	        else System.out.println("fail to choose  , default one \n");
	        return "save_001.ser";
	    }

	/**
	 * Save function which is called by save_load if key pressed S.
	 *
	 * @param m the actual map ( i.e represents the model of the application)
	 * @param fast_save the boolean to know if you want fast save
	 */
	public void save(Map m, boolean fast_save) {
		System.out.println("******************SAVE DONE **************************");
		
		ObjectOutputStream object = null; // object flux 
		try {
			//output file to save the current map object
			String path = "fast_save.ser";
			if(!fast_save) path = choose_Path(null,true);
			final FileOutputStream fichier = new FileOutputStream(path); 
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
	 * Load function which is called by save_load if key pressed C.
	 *
	 * @param m     the actual map ( i.e represents the model of the application)
	 * @param scene the scene  See <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html">https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html</a>
	 * @param fast_load the boolean to know if you want fast load
	 */
	public void load(Map m, Scene scene,boolean fast_load ) {
		// object flux
		ObjectInputStream object = null;
		// the new_map loaded from file
		Map new_m = null;	
		final FileInputStream fichier;
				System.out.println("******************LOAD DONE **************************");
				try {
					if(fast_load)
						fichier = new FileInputStream("fast_save.ser");
					else
					  fichier = new FileInputStream(choose_Path(null,false));
					object = new ObjectInputStream(fichier);
					new_m = (Map) object.readObject();
					
					Planet tab[] = new_m.getPlanet_tab();
					Planet tab_map_param[] = m.getPlanet_tab();
					Player tabp[] = m.getPlayer_tab();
					// Set the new map ( the load one) and change the current map values
					m.setNb_planets(new_m.getNb_planets());  m.setNb_players(new_m.getNb_players());
					for (int i = 0 ; i < new_m.getNb_planets() ;i++) {
						tab_map_param[i] = tab[i]; 
					}
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

