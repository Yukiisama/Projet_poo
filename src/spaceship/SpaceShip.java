package spaceship;



public abstract class SpaceShip {
	
	private int speed;
	private int product_time;
	private int attack_power;
	public int getSpeed() {
		return speed;
	}
	
	
	/**
	 * @param speed
	 * @param product_time
	 * @param attack_power
	 */
	public SpaceShip(int speed, int product_time, int attack_power) {
		this.speed = speed;
		this.product_time = product_time;
		this.attack_power = attack_power;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getProduct_time() {
		return product_time;
	}
	public void setProduct_time(int product_time) {
		this.product_time = product_time;
	}
	public int getAttack_power() {
		return attack_power;
	}
	public void setAttack_power(int attack_power) {
		this.attack_power = attack_power;
	}
	
	
	}

	