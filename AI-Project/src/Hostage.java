import java.awt.Point;

public class Hostage {

	Point position;
	int damage;
	boolean isCarried;
	boolean isAgent;
	
	public Hostage(Point position, int damage) {
		super();
		this.position = position;
		this.damage = damage;
		this.isCarried = false;
		this.isAgent = false;
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

	public void addDamage(int damage) {
		this.damage = damage;
		if(this.damage + damage > 100 && !isAgent) {
			this.damage = 100;
			this.isAgent = true;
		}
		else if(this.damage + damage < 0 && !isAgent) {
			this.damage = 0;
		}
		else if (!isAgent) {
			this.damage += damage;
		}
	}
	
}
