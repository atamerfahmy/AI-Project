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
	int hostagesCount;
	
	public NeoState(Point position, ArrayList<Hostage> carried, ArrayList<Point> agents, ArrayList<Point> pills, ArrayList<Pad> pads, ArrayList<Hostage> hostages, int damage, int killed, int death, int hostagesCount) {
		
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
		this.hostagesCount = hostagesCount;
	}
	
	public Point getPosition() {
		return this.position;
	}
	 

	public static ArrayList<Hostage> addDamageToHostages(ArrayList<Hostage> hostages, int damage) {
		
		for(int i = 0; i < hostages.size(); i++) {
			Hostage hostage = hostages.get(i);
			if(hostage.damage + damage >= 100 && !hostage.isAgent && !hostage.isDead && !hostage.isSaved) {
				hostages.get(i).damage = 100;
				if(!hostage.isCarried) {
					hostages.get(i).isAgent = true;
				}else {
					hostages.get(i).isDead = true;
				}
			}
			else if(hostage.damage + damage < 0 && !hostage.isAgent && !hostage.isDead && !hostage.isSaved) {
				hostages.get(i).damage = 0;
			}
			else if (!hostage.isAgent && !hostage.isDead && !hostage.isSaved) {
				
				hostages.get(i).damage += damage;
			}
		}
		
		return hostages;
	}
	
	public static int calcDeaths(ArrayList<Hostage> hostages, ArrayList<Hostage> carried) {
		
		int deaths = 0;
		for(int i = 0; i < hostages.size(); i++) {
			Hostage hostage = hostages.get(i);
			if(hostage.isDead) {
				deaths++;
			}
		}
		
		for(int i = 0; i < carried.size(); i++) {
			Hostage hostage = carried.get(i);
			if(hostage.isDead) {
				deaths++;
			}
		}
		
		return deaths;
	}
	

	public String toString()
	{
		String value = "";
		value +=  "Position: (" + position.x + "," + position.y + ")" ;

		for(int i=0; i<carried.size();i++){
			value += " Carried: (" +carried.get(i).position.x + "," + carried.get(i).position.y + "," + carried.get(i).isSaved + "," + carried.get(i).isDead + ") ";
		}

		for(int i=0; i<hostages.size();i++){
			value += " Hostage: (" + hostages.get(i).position.x + "," + hostages.get(i).position.y + "," + hostages.get(i).isAgent + "," + hostages.get(i).isSaved + "," + hostages.get(i).isDead + ") ";
		}

		for(int i=0; i<agents.size();i++){
			value += "Agent: (" + agents.get(i).x + "," + agents.get(i).y + ") ";
		}

		for(int i=0; i<pills.size();i++){
			value += "Pill: (" + pills.get(i).x + "," + pills.get(i).y + ") ";
		}

		for(int i=0; i<pads.size();i++){
			value += "Pad: (" + pads.get(i).getStartPad().x + "," + pads.get(i).getStartPad().y + ")";
			value += "(" + pads.get(i).getEndPad().x + "," + pads.get(i).getEndPad().y + ") ";

		}
		value += damage;
		return value;
	}
	
	public String visualizeString()
	{
		String value = "";
		value += "Killed: " + killed; 
		value +=  " Position: (" + position.x + "," + position.y + "," + damage + ")" ;

		for(int i=0; i<carried.size();i++){
			value += " Carried: (" +carried.get(i).position.x + "," + carried.get(i).position.y + "," + carried.get(i).damage + "," + carried.get(i).isAgent + "," + carried.get(i).isDead;
		}

		for(int i=0; i<hostages.size();i++){
			value += " Hostage: (" + hostages.get(i).position.x + "," + hostages.get(i).position.y + "," + hostages.get(i).damage + "," + hostages.get(i).isAgent + "," + hostages.get(i).isDead;
		}
		
		for(int i=0; i<agents.size();i++){
			value += "Agent: (" + agents.get(i).x + "," + agents.get(i).y + ") ";
		}

		for(int i=0; i<pills.size();i++){
			value += "Pill: (" + pills.get(i).x + "," + pills.get(i).y + ") ";
		}

		for(int i=0; i<pads.size();i++){
			value += "Pad: (" + pads.get(i).getStartPad().x + "," + pads.get(i).getStartPad().y + ")";
			value += "(" + pads.get(i).getEndPad().x + "," + pads.get(i).getEndPad().y + ") ";

		}
		
		return value;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public ArrayList<Hostage> getCarried() {
		return carried;
	}

	public void setCarried(ArrayList<Hostage> carried) {
		this.carried = carried;
	}

	public ArrayList<Point> getAgents() {
		return agents;
	}

	public void setAgents(ArrayList<Point> agents) {
		this.agents = agents;
	}

	public ArrayList<Point> getPills() {
		return pills;
	}

	public void setPills(ArrayList<Point> pills) {
		this.pills = pills;
	}

	public ArrayList<Pad> getPads() {
		return pads;
	}

	public void setPads(ArrayList<Pad> pads) {
		this.pads = pads;
	}

	public ArrayList<Hostage> getHostages() {
		return hostages;
	}

	public void setHostages(ArrayList<Hostage> hostages) {
		this.hostages = hostages;
	}

	public int getKilled() {
		return killed;
	}

	public void setKilled(int killed) {
		this.killed = killed;
	}

	public int getDeath() {
		return death;
	}

	public void setDeath(int death) {
		this.death = death;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getHostagesCount() {
		return hostagesCount;
	}

	public void setHostagesCount(int hostagesCount) {
		this.hostagesCount = hostagesCount;
	}

}
