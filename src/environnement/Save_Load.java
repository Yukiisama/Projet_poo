package environnement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import planet.Circle_Planet;
import planet.Planet;
import planet.Square_Planet;
import view.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Save_Load.
 */
public final class Save_Load {

	/**
	 * Save load.
	 *
	 * @param m     the m
	 * @param scene the scene
	 */
	void save_load(Map m, Scene scene) {

		scene.setOnKeyPressed(event -> {
			String str = event.getCode().toString();
			if (event.getCode() == KeyCode.S) {
				this.save(m);
				
			}
			if (event.getCode() == KeyCode.C) {
				this.load(m, scene);
			}

		});
	}

	/**
	 * Save.
	 *
	 * @param m the m
	 */
	void save(Map m) {
		System.out.println("******************SAVE DONE **************************");
		ObjectOutputStream object = null;
		Planet tab[] = m.getPlanet_tab();
		try {

			final FileOutputStream fichier = new FileOutputStream("mon_objet.ser");
			
			object = new ObjectOutputStream(fichier);
			for (int i = 0; i < m.getNb_planets(); i++) {
				tab[i].setXY_serialize();
			}
			object.writeObject(m);
			object.flush();
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
	 * Load.
	 *
	 * @param m     the m
	 * @param scene the scene
	 */
	void load(Map m, Scene scene) {

		ObjectInputStream object = null;
		Map new_m = null;

				System.out.println("******************LOAD DONE **************************");
				try {

					final FileInputStream fichier = new FileInputStream("mon_objet.ser");

					object = new ObjectInputStream(fichier);
					new_m = (Map) object.readObject();
					System.out.println(m);
					System.out.println(new_m);
					Planet tab[] = new_m.getPlanet_tab();
					Planet tab_map_param[] = m.getPlanet_tab();
					System.out.println(new_m.getNb_planets());
					System.out.println(new_m.getNb_players());
					for (int i = 0; i < new_m.getNb_planets(); i++) {
						Point2D p = new Point2D(tab[i].x,tab[i].y);
						tab_map_param[i].setCenter(p);
						if(tab[i] instanceof Square_Planet) {
							Square_Planet sq_p = (Square_Planet) tab_map_param[i];
							sq_p.setSize(((Square_Planet)tab[i]).getSize());
							tab[i]=sq_p;
						}
						else if(tab[i] instanceof Circle_Planet) {
							Circle_Planet circle_p = (Circle_Planet) tab_map_param[i];
							circle_p.setRadius(((Circle_Planet)tab[i]).getRadius());
							tab[i]=circle_p;
							
						}
					}
					m.setNb_planets(new_m.getNb_planets());
					m.setNb_players(new_m.getNb_players());
					m.setPlanet_tab(tab_map_param);
					m.setPlayer_tab(m.getPlayer_tab());

				} catch (final java.io.IOException e) {

					e.printStackTrace();

				} catch (final ClassNotFoundException e) {

					e.printStackTrace();

				} finally {

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

