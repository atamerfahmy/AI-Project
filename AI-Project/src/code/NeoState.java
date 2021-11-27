package code;
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
	int killed;
	int death;
	Point prev;
	
	public NeoState(Point position, ArrayList<Hostage> carried, ArrayList<Point> agents, ArrayList<Point> pills, ArrayList<Pad> pads, ArrayList<Hostage> hostages, int damage, int killed, int death, Point prev) {
		
		super();
		this.position = position;
		this.pathCost = this.damage;
		this.carried = carried;
		this.agents = agents;
		this.pills = pills;
		this.pads = pads;
		this.hostages = hostages;
		this.damage = damage;
		this.killed = killed;
		this.death = death;
		this.prev = prev;
	}
	
	public Point getPosition() {
//		System.out.println(this.positions.get(this.positions.size()-1));
//		for(Point x: positions) {
//			System.out.println(x.x + " " + x.y);
//		}
		return this.position;
	}
	 

	
	public String toString()
	{
		String value = "";
		value += "(" + position.x + "," + position.y +")";
//		value += "(" + carried.size() + "," + agents.size() + "," + pills.size() + "," + pads.size() + "," + hostages.size() + ")";

		value += "(" + -1 + "," + -1 +")";

		for(int i=0; i<carried.size();i++){
			//if(positions.get(i).x == -1)
				//value += ";";
			value += "(" + carried.get(i).position.x + "," + carried.get(i).position.y +")";
		}
		value += "(" + -1 + "," + -1 +")";

		for(int i=0; i<hostages.size();i++){
			//if(positions.get(i).x == -1)
				//value += ";";
			value += "(" + hostages.get(i).position.x + "," + hostages.get(i).position.y + ")";
		}
		value += "(" + -1 + "," + -1 +")";

		for(int i=0; i<agents.size();i++){
			//if(positions.get(i).x == -1)
				//value += ";";
			value += "(" + agents.get(i).x + "," + agents.get(i).y +")";
		}
		value += "(" + -1 + "," + -1 +")";

		for(int i=0; i<pills.size();i++){
			//if(positions.get(i).x == -1)
				//value += ";";
			value += "(" + pills.get(i).x + "," + pills.get(i).y +")";
		}
		value += "(" + -1 + "," + -1 +")";

		for(int i=0; i<pads.size();i++){
			//if(positions.get(i).x == -1)
				//value += ";";
			value += "(" + pads.get(i).getStartPad().x + "," + pads.get(i).getStartPad().y +")";
			value += "(" + pads.get(i).getEndPad().x + "," + pads.get(i).getEndPad().y +")";

		}
		
//		value +=  "(" + damage +")";
		return value;
	}

}
