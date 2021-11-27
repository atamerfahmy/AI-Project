package code;
import java.awt.Point;

public class Hostage {

	Point position;
	int damage;
	boolean isCarried;
	boolean isAgent;
	boolean isDead;

	public Hostage(Point position, int damage) {
		super();
		this.position = position;
		this.damage = damage;
		this.isCarried = false;
		this.isAgent = false;
		this.isDead = false;
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

	public int addDamage(int death, int damage) {
		this.damage = damage;
		if(this.damage + damage > 100 && !isAgent && !isDead) {
			this.damage = 100;
			death++;
			if(!isCarried) {
				this.isAgent = true;
			}else {
				this.isDead = true;
			}
		}
		else if(this.damage + damage < 0 && !isAgent && !isDead) {
			this.damage = 0;
		}
		else if (!isAgent && !isDead) {
			this.damage += damage;
		}
		return death;
	}
	
}
