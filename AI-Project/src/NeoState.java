import java.awt.Point;
import java.util.ArrayList;


public class NeoState extends State {
	Point position;
	int damage;
	ArrayList<Hostage> carried;
	ArrayList<Point> agents;
	ArrayList<Point> pills;
	ArrayList<Pad> pads;
	ArrayList<Hostage> hostages;

	public NeoState(Point position, ArrayList<Hostage> carried, ArrayList<Point> agents, ArrayList<Point> pills, ArrayList<Pad> pads, ArrayList<Hostage> hostages, int damage) {
		
		super();
		this.position = position;
		this.pathCost = this.damage;
		this.carried = carried;
		this.agents = agents;
		this.pills = pills;
		this.pads = pads;
		this.hostages = hostages;
		this.damage = damage;

	}
	
	public Point getPosition() {
//		System.out.println(this.positions.get(this.positions.size()-1));
//		for(Point x: positions) {
//			System.out.println(x.x + " " + x.y);
//		}
		return this.position;
	}
	 
	public void setDamage(int value) {
		
		//value is negative to increase health/decrease damage
		if(damage + value < 0) {
			damage = 0;
		}
		else if(damage + value > 100) {
			damage = 100;
		}
		else {
			damage += value;
		}
		
	}
	
	public String toString()
	{
		String value = "";
		value += "(" + position.x + "," + position.y + ")";
		
		for(int i=0; i<carried.size();i++){
			//if(positions.get(i).x == -1)
				//value += ";";
			value += "(" + carried.get(i).position.x + "," + carried.get(i).position.y +")";
		}
		
		for(int i=0; i<agents.size();i++){
			//if(positions.get(i).x == -1)
				//value += ";";
			value += "(" + agents.get(i).x + "," + agents.get(i).y +")";
		}
		
		for(int i=0; i<pills.size();i++){
			//if(positions.get(i).x == -1)
				//value += ";";
			value += "(" + pills.get(i).x + "," + pills.get(i).y +")";
		}
		
		for(int i=0; i<pads.size();i++){
			//if(positions.get(i).x == -1)
				//value += ";";
			value += "(" + pads.get(i).getStartPad().x + "," + pads.get(i).getStartPad().y +")";
			value += "(" + pads.get(i).getEndPad().x + "," + pads.get(i).getEndPad().y +")";

		}
		
		for(int i=0; i<hostages.size();i++){
			//if(positions.get(i).x == -1)
				//value += ";";
			value += "(" + hostages.get(i).position.x + "," + hostages.get(i).position.y +")";
		}
		
		return value;
	}

}
