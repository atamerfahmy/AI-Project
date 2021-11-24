import java.awt.Point;

public class Hostage {

	Point position;
	int damage;
	
	public Hostage(Point position, int damage) {
		super();
		this.position = position;
		this.damage = damage;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
}
