package interfaces;
import planet.Planet;

public interface Actions_game {

	public int deplacement(Planet origin , Planet destination);
	public int production();
	public boolean collision();
}
