package controller;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Player {

	private int ID;
	private Paint color;
	
	public Player(int id) {
		this.ID = id;
		switch (ID) {
			case 0:
				this.color = Color.YELLOW;
				break;
			case 1:
				this.color = Color.LIGHTBLUE;
				break;
			case 2:
				this.color = Color.HOTPINK;
				break;
			case 3:
				this.color = Color.LIGHTSLATEGREY;
				break;
		}
	}
	
	public int getID() { return ID; }
	public void setID(int id) { ID = id; }

	public Paint getColor() { return color; }
	public void setColor(Paint color) { this.color = color; }
}
