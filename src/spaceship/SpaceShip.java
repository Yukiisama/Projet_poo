package spaceship;



public abstract class SpaceShip {
	
	private int speed;
	private int attack_power;
	public int getSpeed() {
		return speed;
	}
	
	
	/**
	 * @param speed
	 * @param product_time
	 * @param attack_power
	 */
	public SpaceShip(int speed, int attack_power) {
		this.speed = speed;
		this.attack_power = attack_power;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getAttack_power() {
		return attack_power;
	}
	public void setAttack_power(int attack_power) {
		this.attack_power = attack_power;
	}
	
	
	}

	