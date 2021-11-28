package code;

import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;

import org.hamcrest.core.IsSame;

public class Matrix extends SearchProblem {

	int rows;
	int columns;
	Point Telephone;
	int C;

	public Matrix(String grid) {
		super();

		String[] g1 = grid.split(";");
		String[] g2 = g1[0].split(",");

		this.rows = Integer.parseInt(g2[0]);
		this.columns = Integer.parseInt(g2[1]);

		this.C = Integer.parseInt(g1[1]);

		String[] g3 = g1[2].split(",");
		Point Neo = new Point(Integer.parseInt(g3[0]), Integer.parseInt(g3[1]));

		String[] g4 = g1[3].split(",");
		this.Telephone = new Point(Integer.parseInt(g4[0]), Integer.parseInt(g4[1]));

		String[] g5 = g1[4].split(",");
		ArrayList<Point> agents = new ArrayList<Point>();
		for (int i = 0; i + 1 < g5.length; i = i + 2) {
			agents.add(new Point(Integer.parseInt(g5[i]), Integer.parseInt(g5[i + 1])));
		}

		String[] g6 = g1[5].split(",");
		ArrayList<Point> pills = new ArrayList<Point>();
		for (int i = 0; i + 1 < g6.length; i = i + 2) {
			pills.add(new Point(Integer.parseInt(g6[i]), Integer.parseInt(g6[i + 1])));
		}

		String[] g7 = g1[6].split(",");
		ArrayList<Pad> pads = new ArrayList<Pad>();
		for (int i = 0; i + 3 < g7.length; i = i + 4) {
			pads.add(new Pad(new Point(Integer.parseInt(g7[i]), Integer.parseInt(g7[i + 1])),
					new Point(Integer.parseInt(g7[i + 2]), Integer.parseInt(g7[i + 3]))));
		}

		String[] g8 = g1[7].split(",");
		ArrayList<Hostage> hostages = new ArrayList<Hostage>();
		for (int i = 0; i + 2 < g8.length; i = i + 3) {
			hostages.add(new Hostage(new Point(Integer.parseInt(g8[i]), Integer.parseInt(g8[i + 1])),
					Integer.parseInt(g8[i + 2]), false, false, false, false));
		}

		initialState = new NeoState(Neo, new ArrayList<Hostage>(), agents, pills, pads, hostages, 0, 0, 0, Neo, hostages.size());
		operators = new String[] { "up", "down", "right", "left", "carry", "drop", "kill", "takePill", "fly" };

	}

//	public boolean agentCollision() {
//		for (int i = 0; i < agents.size(); i++) {
//			if (Neo.x == agents.get(i).x && Neo.y == agents.get(i).y)
//				return true;
//		}
//		return false;
//	}

	@SuppressWarnings("unchecked")
	public State transitionFunction(State state, String operator) {

		
		NeoState neoState = ((NeoState) ((NeoState) state));

		ArrayList<Hostage> carried = new ArrayList<Hostage>();
		for(Hostage hostage: (ArrayList<Hostage>) neoState.getCarried().clone()) {
			carried.add(new Hostage(hostage.position, hostage.damage, hostage.isCarried, hostage.isAgent, hostage.isDead, hostage.isSaved));
		}
		
		
		ArrayList<Point> agents = new ArrayList<Point>();
		for(Point agent: (ArrayList<Point>) neoState.getAgents().clone()) {
			agents.add(new Point(agent));
		}
		
		ArrayList<Point> pills = new ArrayList<Point>();
		for(Point pill: (ArrayList<Point>) neoState.getPills().clone()) {
			pills.add(new Point(pill));
		}
		
		ArrayList<Pad> pads = new ArrayList<Pad>();
		for(Pad pad: (ArrayList<Pad>) neoState.getPads().clone()) {
			pads.add(new Pad(new Point(pad.startPad), new Point(pad.finishPad)));
		}
		
		ArrayList<Hostage> hostages = new ArrayList<Hostage>();
		for(Hostage hostage: (ArrayList<Hostage>) neoState.getHostages().clone()) {
			hostages.add(new Hostage(hostage.position, hostage.damage, hostage.isCarried, hostage.isAgent, hostage.isDead, hostage.isSaved));
		}
		
		Point neoCurrentPosition = new Point(neoState.getPosition());
//		ArrayList<Hostage> carried = (ArrayList<Hostage>) neoState.getCarried().clone();
//		ArrayList<Point> agents = (ArrayList<Point>) neoState.getAgents().clone();
//		ArrayList<Point> pills = (ArrayList<Point>) neoState.getPills().clone();
//		ArrayList<Pad> pads = (ArrayList<Pad>) neoState.getPads().clone();
//		ArrayList<Hostage> hostages = (ArrayList<Hostage>) neoState.getHostages().clone();

		
		int damage = neoState.getDamage();
		int killed = neoState.getKilled();
		int death = neoState.getDeath();
		Point prev = new Point(neoState.getPrev());
		int hostagesCount = neoState.getHostagesCount();

//		hostages = NeoState.addDamageToHostages(hostages, 2);
//		carried = NeoState.addDamageToHostages(carried, 2);
		
//		for(Hostage x: hostages) {
//			if(x.isAgent) {
//				System.out.println(x.position.x + "," + x.position.y);
//			}
//		}

//		System.out.println(death);
		switch (operator) {
		case "up": {
			Point topAdj = new Point(neoCurrentPosition.x - 1, neoCurrentPosition.y);
			
			if (topAdj.x >= 0 && !agentCollision(agents, hostages, topAdj)) {
				hostages = NeoState.addDamageToHostages(hostages, 2);
				carried = NeoState.addDamageToHostages(carried, 2);
				State newState = new NeoState(topAdj, carried, agents, pills, pads, hostages, damage, killed, death, neoCurrentPosition, hostagesCount);
				if (states.containsKey(newState.toString())) {
					return null;
				}
//				if(count > 20) {
//					System.out.print(count + " " + operator + " ");
//					System.out.println(((NeoState) newState).visualizeString());
//				}
				states.put(newState.toString(), "");
				return newState;

			} else
				return null;
		}
		case "down": {
			Point downAdj = new Point(neoCurrentPosition.x + 1, neoCurrentPosition.y);
			
			if (downAdj.x < rows && !agentCollision(agents, hostages, downAdj)) {
//				neoState.positions.add(downAdj);
//				if(count == 26) {
//					System.out.println("HERE");
//				}
				hostages = NeoState.addDamageToHostages(hostages, 2);
				carried = NeoState.addDamageToHostages(carried, 2);
				State newState = new NeoState(downAdj, carried, agents, pills, pads, hostages, damage, killed, death, neoCurrentPosition, hostagesCount);

				if (states.containsKey(newState.toString())) {
					if(count == 26) {
						System.out.println("HERE2");
					}
					return null;
				}
				states.put(newState.toString(), "");
//				if(count > 20) {
//					System.out.print(count + " " + operator + " ");
//					System.out.println(((NeoState) newState).visualizeString());
//				}
				return newState;

			} else
				return null;
		}
		case "left": {
			Point leftAdj = new Point(neoCurrentPosition.x, neoCurrentPosition.y - 1);
			
			if (leftAdj.y >= 0 && !agentCollision(agents, hostages, leftAdj)) {
				
				hostages = NeoState.addDamageToHostages(hostages, 2);
				carried = NeoState.addDamageToHostages(carried, 2);
				State newState = new NeoState(leftAdj, carried, agents, pills, pads, hostages, damage, killed, death, neoCurrentPosition, hostagesCount);

				if (states.containsKey(newState.toString())) {
					return null;
				}
				states.put(newState.toString(), "");
//				if(count > 20) {
//					System.out.print(count + " " + operator + " ");
//					System.out.println(((NeoState) newState).visualizeString());
//				}
				return newState;

			} else
				return null;
		}
		case "right": {
			Point rightAdj = new Point(neoCurrentPosition.x, neoCurrentPosition.y + 1);
			
			if (rightAdj.y < columns && !agentCollision(agents, hostages, rightAdj)) {
				
				hostages = NeoState.addDamageToHostages(hostages, 2);
				carried = NeoState.addDamageToHostages(carried, 2);
				State newState = new NeoState(rightAdj, carried, agents, pills, pads, hostages, damage, killed, death, neoCurrentPosition, hostagesCount);
				
				if (states.containsKey(newState.toString())) {
					return null;
				}
//				if(count > 20) {
//					System.out.print(count + " " + operator + " ");
//					System.out.println(((NeoState) newState).visualizeString());
//				}
				states.put(newState.toString(), "");
				return newState;

			} else
				return null;
		}
		case "fly": {

			Point flyTo = null;
			for (int i = 0; i < pads.size(); i++) {
				Pad pad = pads.get(i);
				if (pad.getStartPad().x == neoCurrentPosition.x && pad.getStartPad().y == neoCurrentPosition.y) {
					flyTo = pad.getEndPad();
				} else if (pad.getEndPad().x == neoCurrentPosition.x && pad.getEndPad().y == neoCurrentPosition.y) {
					flyTo = pad.getStartPad();
				}
			}

			if (flyTo != null) {
//				System.out.println("FLY");
				
				hostages = NeoState.addDamageToHostages(hostages, 2);
				carried = NeoState.addDamageToHostages(carried, 2);
				
				State newState = new NeoState(flyTo, carried, agents, pills, pads, hostages, damage, killed, death,
						neoCurrentPosition, hostagesCount);
				if (states.containsKey(newState.toString())) {
					return null;
				}
//				if(count > 20) {
//					System.out.print(count + " " + operator + " ");
//					System.out.println(((NeoState) newState).visualizeString());
//				}
				states.put(newState.toString(), "");
				return newState;
			} else {
				return null;
			}

		}
		case "kill": {

//			ArrayList<Hostage> agentsTemp = new ArrayList<Hostage>();
			Hostage hasHostage = null;
			for(Hostage h: (ArrayList<Hostage>) hostages.clone()) {
				hasHostage = h;
			}
			if(damage >= 80)
				return null;
			
			boolean hasKilled = false;
			
			for (int i = 0; i < agents.size(); i++) {
				if (neoCurrentPosition.x - 1 == agents.get(i).x && neoCurrentPosition.y == agents.get(i).y) // adjacent																											// down
				{
					if(hasHostage != null && hasHostage.damage >= 98) {
						continue;
					}
					agents.remove(i);
					i--;
					hasKilled = true;
					killed++;
					continue;
				}
				if (neoCurrentPosition.x + 1 == agents.get(i).x && neoCurrentPosition.y == agents.get(i).y) // adjacent
				{
					if(hasHostage != null && hasHostage.damage >= 98) {
						continue;
					}
					agents.remove(i);
					i--;
					hasKilled = true;
					killed++;
					continue;

				}
				if (neoCurrentPosition.x == agents.get(i).x && neoCurrentPosition.y - 1 == agents.get(i).y) // adjacent
				{
					if(hasHostage != null && hasHostage.damage >= 98) {
						continue;
					}
					agents.remove(i);
					i--;
					hasKilled = true;
					killed++;
					continue;

				}
				if (neoCurrentPosition.x == agents.get(i).x && neoCurrentPosition.y + 1 == agents.get(i).y) // adjacent
				{
					if(hasHostage != null && hasHostage.damage >= 98) {
						continue;
					}
					agents.remove(i);
					i--;
					hasKilled = true;
					killed++;
					continue;

				}
			}
			
			ArrayList<Hostage> hostTemp = new ArrayList<Hostage>();
			
			for(Hostage hostage: (ArrayList<Hostage>) hostages.clone()) {
				hostTemp.add(new Hostage(hostage.position, hostage.damage, hostage.isCarried, hostage.isAgent, hostage.isDead, hostage.isSaved));
			}
			
			for (int i = 0; i < hostages.size(); i++) {
				if (hostages.get(i).isAgent && !hostages.get(i).isCarried && !hostages.get(i).isDead && !hostages.get(i).isSaved) {
//					System.out.println(hostages.get(i).position.x + "," + hostages.get(i).position.y);
					if (neoCurrentPosition.x - 1 == hostages.get(i).position.x
							&& neoCurrentPosition.y == hostages.get(i).position.y) // adjacent
					{
						if(hasHostage != null && hasHostage.damage >= 98) {
							continue;
						}
//						hostTemp.remove(hostages.get(i));
						hostTemp.get(i).isDead = true;
						hasKilled = true;
						killed++;
						continue;

					}
					if (neoCurrentPosition.x + 1 == hostages.get(i).position.x
							&& neoCurrentPosition.y == hostages.get(i).position.y) // adjacent
					{
						if(hasHostage != null && hasHostage.damage >= 98) {
							continue;
						}
//						hostTemp.remove(hostages.get(i));
						hostTemp.get(i).isDead = true;

						hasKilled = true;
						killed++;
						continue;

					}
					if (neoCurrentPosition.x == hostages.get(i).position.x
							&& neoCurrentPosition.y - 1 == hostages.get(i).position.y) // adjacent
					{
						if(hasHostage != null && hasHostage.damage >= 98) {
							continue;
						}
//						hostTemp.remove(hostages.get(i));
						hostTemp.get(i).isDead = true;

						hasKilled = true;
						killed++;
						continue;

					}
					if (neoCurrentPosition.x == hostages.get(i).position.x
							&& neoCurrentPosition.y + 1 == hostages.get(i).position.y) // adjacent
					{
						if(hasHostage != null && hasHostage.damage >= 98) {
							continue;
						}
//						hostTemp.remove(hostages.get(i));
						hostTemp.get(i).isDead = true;
						hasKilled = true;
						killed++;
						continue;

					}
				}
			}

			if(hasKilled) {
				
				int newDamage = setDamage(damage, 20);
				hostTemp = NeoState.addDamageToHostages(hostTemp, 2);
				carried = NeoState.addDamageToHostages(carried, 2);
				State newState = new NeoState(neoCurrentPosition, carried, agents, pills, pads, hostTemp, newDamage, killed, death, prev, hostagesCount);
				if (states.containsKey(newState.toString())) {
					return null;
				}
//				if(count > 20) {
//					System.out.print(count + " " + operator + " ");
//					System.out.println(((NeoState) newState).visualizeString());
//				}
				states.put(newState.toString(), "");
				return newState;
			}else {
				return null;
			}
		}
		case "carry": {
			Hostage hostage = null;
			int index = -1;
			for (int i = 0; i < hostages.size(); i++) {
				Hostage x = hostages.get(i);
				if (x.position.x == neoCurrentPosition.x && x.position.y == neoCurrentPosition.y && !x.isAgent && !x.isCarried && !x.isSaved) {
					hostage = x;
					index = i;
					break;
				}
			}

			if (hostage != null && carried.size() < this.C) {

				hostage.isCarried = true;
				carried.add(hostage);
				hostages.remove(index);

				hostages = NeoState.addDamageToHostages(hostages, 2);
				carried = NeoState.addDamageToHostages(carried, 2);
				
				State newState = new NeoState(neoCurrentPosition, carried, agents, pills, pads, hostages, damage, killed, death, prev, hostagesCount);
				if (states.containsKey(newState.toString())) {
					return null;
				}
//				if(count > 20) {
//					System.out.print(count + " " + operator + " ");
//					System.out.println(((NeoState) newState).visualizeString());
//				}
				states.put(newState.toString(), "");
				return newState;
			} else
				return null;

		}

		case "drop": {

			
			if (!carried.isEmpty() && neoCurrentPosition.x == Telephone.x && neoCurrentPosition.y == Telephone.y) {
//				carried = NeoState.addDamageToHostages(carried, -2);
//				death = NeoState.calcDeaths(hostages, carried, hostagesCount);

				for (int i = 0; i < carried.size(); i++) {
					
					Hostage x = new Hostage(carried.get(i).getPosition(), carried.get(i).damage, false, carried.get(i).isAgent, carried.get(i).isDead, carried.get(i).isSaved);
//					x.isCarried = false;
					if(!x.isDead) {
						x.isSaved = true;
					}
					hostages.add(x);
					
				}
				ArrayList<Hostage> newCarried = new ArrayList<Hostage>();
				
				hostages = NeoState.addDamageToHostages(hostages, 2);
//				carried = NeoState.addDamageToHostages(carried, 2);
				
				State newState = new NeoState(neoCurrentPosition, newCarried, agents, pills, pads, hostages, damage, killed, death, prev, hostagesCount);

				if (states.containsKey(newState.toString())) {
					return null;
				}
//				System.out.println("DROP: " + hostages.size());
				states.put(newState.toString(), "");
//				if(count > 20) {
//					System.out.print(count + " " + operator + " ");
//					System.out.println(((NeoState) newState).visualizeString());
//				}
				return newState;
			} else
				return null;

		}
		case "takePill": {

			Point pill = null;
			
			for(int i = 0; i < pills.size(); i++) {
				if(pills.get(i).x == neoCurrentPosition.x && pills.get(i).y == neoCurrentPosition.y) {
					pill = pills.get(i);
//					pills.remove(i);
					break;
				}
			}
			if(pill != null ) {
				int newDamage = setDamage(damage, -20);
				ArrayList<Hostage> newHostages = NeoState.addDamageToHostages(hostages, -20);
				ArrayList<Hostage> newCarried = NeoState.addDamageToHostages(carried, -20);
				
//				ArrayList<Hostage> newHostages = NeoState.addDamageToHostages(hostages, -22);
//				ArrayList<Hostage> newCarried = NeoState.addDamageToHostages(carried, -22);
				
				State newState = new NeoState(neoCurrentPosition, newCarried, agents, new ArrayList<Point>(), pads, newHostages, newDamage, killed, death, prev, hostagesCount);

				if (states.containsKey(newState.toString())) {
					return null;
				}
//				if(count > 20) {
//					System.out.print(count + " " + operator + " ");
//					System.out.println(((NeoState) newState).visualizeString());
//				}
				states.put(newState.toString(), "");
				return newState;
			}
			else
				return null;
			
		}
		default:
			return null;

		}
	}

	public static int setDamage(int damage, int value) {
		
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
		
		return damage;
		
	}
	
	public boolean agentCollision(ArrayList<Point> agentsArray, ArrayList<Hostage> hostagesArray, Point adj) {

		
		for (Point p : agentsArray) {
			if(p.x == adj.x && p.y == adj.y) {
//				if(count == 26) {
//					System.out.println(true);
//				}
				return true;
			}
		}

		for (Hostage p : hostagesArray) {
			if(p.position.x == adj.x && p.position.y == adj.y) {
				if((p.isAgent && !p.isDead) || (!p.isAgent && p.damage >= 98) ) {
//					if(count == 26) {
//						System.out.println(true);
//					}
					return true;
				}
			}
		}

//		if(count == 26) {
//			System.out.println(false);
//		}
		return false;
	}
	
	public static boolean padCollision(NeoState neoState, ArrayList<Pad> pads) {
		
		for(Pad pad: pads) {
			if(((pad.startPad.x == neoState.position.x && pad.startPad.y == neoState.position.y)
					&& (pad.finishPad.x != neoState.prev.x && pad.finishPad.y != neoState.prev.y)) ||
					((pad.finishPad.x == neoState.position.x && pad.finishPad.y == neoState.position.y)
							&& (pad.startPad.x != neoState.prev.x && pad.startPad.y != neoState.prev.y)) ) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean pillCollision(NeoState neoState, ArrayList<Point> pills) {
		
		for(Point pill: pills) {
			if(pill.x == neoState.position.x && pill.y == neoState.position.y) {
				return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) {

		String grid = "5,5;2;0,4;1,4;0,1,1,1,2,1,3,1,3,3,3,4;1,0,2,4;0,3,4,3,4,3,0,3;0,0,30,3,0,80,4,4,80";

		String s = solve(grid, "UC", false);
		System.out.println(s);

//		String grid = "5,5;2;0,4;1,4;0,1,1,1,2,1,3,1,3,3,3,4;1,0,2,4;0,3,4,3,4,3,0,3;0,0,30,3,0,80,4,4,80";
//		Matrix e1 = new Matrix(grid);
//		System.out.println(print(e1.agents));
//		System.out.println(e1.pads);
//		System.out.println(e1.);
//		System.out.println(ir4);

	}

	public static String solve(String grid, String strategy, boolean visualize) {

		Matrix matrix = new Matrix(grid);
		SearchTreeNode node = matrix.generalSearch(strategy);
		String result = "";

//		System.out.println(node);

		if (node != null) {
			NeoState neoState = ((NeoState) node.state);
//			System.out.println(neoState.visualizeString());
//			int pathcost = node.pathCost;
//			result += "snap";
			int count = 0;
			while (node.parent != null) {
				count++;
				result = node.operator + "," + result;
				System.out.println(node.operator + " " + ((NeoState) node.state).visualizeString());
				node = node.parent;
			}

			System.out.println(count);
			result = result.substring(0, result.length() - 1);
			result += ";" + neoState.death + ";" + neoState.killed + ";" + matrix.nodesExpanded;
		} else {
			result = "No Solution";
		}

		System.out.println(result);
		
		return result;

	}

	public static String genGrid() {
		String grid = "";
		ArrayList<Point> positions = new ArrayList<Point>();

		// Grid Size
		int M = genRandomInt(5, 15);
		int N = genRandomInt(5, 15);

		int maxPositions = M * N;

		grid += M + "," + N + ";";

		// C is the max number of hostages Neo can carry
		int C = genRandomInt(1, 4);

		grid += C + ";";

		// Set Neo's location
		int NeoX = genRandomInt(0, M - 1);
		int NeoY = genRandomInt(0, N - 1);

		Point neo = new Point(NeoX, NeoY);
		positions.add(neo);

		grid += NeoX + "," + NeoY + ";";

		// Set telephone's location
		int TelephoneX = genRandomInt(0, M - 1);
		int TelephoneY = genRandomInt(0, N - 1);

		Point telephone = new Point(TelephoneX, TelephoneY);

		boolean flag = false;
		while (positions.indexOf((Point) telephone) != -1 && !flag) {

			TelephoneX = genRandomInt(0, M - 1);
			TelephoneY = genRandomInt(0, N - 1);

			telephone = new Point(TelephoneX, TelephoneY);

		}

		if (!flag) {
			positions.add(telephone);

			grid += TelephoneX + "," + TelephoneY + ";";
		}

		if (positions.size() >= maxPositions) {
			flag = true;
		}

		// Generate number of hostages
		int numHostages = genRandomInt(3, 10);

		String hostages = "";
		// Set locations of the hostages on the grid
		for (int i = 0; i < numHostages && !flag; i++) {
			int hostageX = genRandomInt(0, M - 1);
			int hostageY = genRandomInt(0, N - 1);

			int hostageDamage = genRandomInt(1, 99);

			Point hostage = new Point(hostageX, hostageY);

			while (positions.indexOf((Point) hostage) != -1) {

				hostageX = genRandomInt(0, M - 1);
				hostageY = genRandomInt(0, N - 1);

				hostage = new Point(hostageX, hostageY);
			}

			if (!flag) {
				positions.add(hostage);
				hostages += hostageX + "," + hostageY + "," + hostageDamage;

				if (i == numHostages - 1) {
					hostages += "";
				} else {
					hostages += ",";
				}
			}

			if (positions.size() >= maxPositions) {
				flag = true;
			}
		}

		// Generate number of pills
		int numPills = genRandomInt(1, numHostages);

		// Set locations of the pills on the grid
		String pills = "";
		for (int i = 0; i < numPills && !flag; i++) {
			int pillX = genRandomInt(0, M - 1);
			int pillY = genRandomInt(0, N - 1);

			Point pill = new Point(pillX, pillY);

			while (positions.indexOf((Point) pill) != -1) {

				pillX = genRandomInt(0, M - 1);
				pillY = genRandomInt(0, N - 1);

				pill = new Point(pillX, pillY);
			}

			if (!flag) {
				positions.add(pill);
				pills += pillX + "," + pillY;

				if (i == numPills - 1) {
					pills += ";";
				} else {
					pills += ",";
				}
			}

			if (positions.size() >= maxPositions) {
				flag = true;
			}
		}

		// Generate number of agents
		int numAgents = genRandomInt(1, maxPositions - positions.size() - 5);
		String agents = "";

		// Set locations of the agents on the grid
		for (int i = 0; i < numAgents && !flag; i++) {
			int agentX = genRandomInt(0, M - 1);
			int agentY = genRandomInt(0, N - 1);

			Point agent = new Point(agentX, agentY);

			while (positions.indexOf((Point) agent) != -1) {
				agentX = genRandomInt(0, M - 1);
				agentY = genRandomInt(0, N - 1);

				agent = new Point(agentX, agentY);
			}

			if (!flag) {
				positions.add(agent);
				agents += agentX + "," + agentY;

				if (i == numAgents - 1) {
					agents += ";";
				} else {
					agents += ",";
				}
			}

			if (positions.size() >= maxPositions) {
				flag = true;
			}
		}

		// Generate number of pads
		int numPads = genRandomInt(1, maxPositions - positions.size());
		String pads = "";

		// Set locations of the pads on the grid
		for (int i = 0; i < numPads && !flag; i++) {

			if (maxPositions - positions.size() < 2) {
				flag = true;
			}

			int padX = genRandomInt(0, M - 1);
			int padY = genRandomInt(0, N - 1);

			Point pad = new Point(padX, padY);

			while (positions.indexOf((Point) pad) != -1) {
				padX = genRandomInt(0, M - 1);
				padY = genRandomInt(0, N - 1);

				pad = new Point(padX, padY);
			}

			int finPadX = genRandomInt(0, M - 1);
			int finPadY = genRandomInt(0, N - 1);

			Point finPad = new Point(finPadX, finPadY);

			while (positions.indexOf((Point) finPad) != -1) {
				finPadX = genRandomInt(0, M - 1);
				finPadY = genRandomInt(0, N - 1);

				finPad = new Point(finPadX, finPadY);
			}

			if (!flag) {
				positions.add(pad);
				positions.add(finPad);

				pads += padX + "," + padY + "," + finPadX + "," + finPadY;

				if (i == numPads - 1 || maxPositions - positions.size() < 2) {
					pads += ";";
				} else {
					pads += ",";
				}
			}

			if (positions.size() >= maxPositions) {
				flag = true;
			}
		}
//		System.out.println(grid);

		grid += agents + pills + pads + hostages;
//		System.out.println(agents);
//		System.out.println(pills);
//		System.out.println(pads);
//		System.out.println(hostages);

		return grid;
	}

	public static int genRandomInt(int lowerBound, int upperBound) {

		if (lowerBound > upperBound) {
			int temp = upperBound;
			upperBound = lowerBound;
			lowerBound = temp;
		}

		int random_int = (int) Math.floor(Math.random() * (upperBound - lowerBound + 1) + lowerBound);

		return random_int;
	}

	public static String print(ArrayList<Point> x) {
		String s = "";

		for (Point p : x) {
			s += "(" + p.x + "," + p.y + ")";
		}
		return s;
	}

	public boolean goalTest(SearchTreeNode node) {

		NeoState s1 = (NeoState) node.state;
		ArrayList<Hostage> hostages = s1.hostages;
		ArrayList<Hostage> carried = s1.carried;
		
		boolean hostageAlive = false;
		for(Hostage x: hostages) {
			if(!x.isDead && !x.isSaved) {
				hostageAlive = true;
			}
		}
		
		if (s1.damage < 100 && !hostageAlive && carried.isEmpty() && s1.position.x == Telephone.x && s1.position.y == Telephone.y) {
			s1.death = NeoState.calcDeaths(hostages, carried);
			
			return true;
		}
		else
			return false;
	}
}
