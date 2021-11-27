import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TheMatrix extends SearchProblem {

	int rows;
	int columns;
//	Point Neo;
	Point Telephone;
	int C;

//	NeoState neoState;

//	ArrayList<Point> agents;
//	ArrayList<Point> pills;
//	ArrayList<Pad> pads;
//	ArrayList<Hostage> hostages;

	public TheMatrix(String grid) {
		super();

		String[] g1 = grid.split(";");

//		System.out.println();
//		
//		System.out.println(g1.length);
//
//		System.out.println();

//		for (String x : g1) {
//			System.out.println(x);
//		}

		String[] g2 = g1[0].split(",");

		this.rows = Integer.parseInt(g2[0]);
		this.columns = Integer.parseInt(g2[1]);

		this.C = Integer.parseInt(g1[1]);

		String[] g3 = g1[2].split(",");
		Point Neo = new Point(Integer.parseInt(g3[0]), Integer.parseInt(g3[1]));

		ArrayList<Point> neoPos = new ArrayList<Point>();
		neoPos.add(new Point(Integer.parseInt(g3[0]), Integer.parseInt(g3[1])));
//		this.neoState = new NeoState(neoPos, 0);

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
					Integer.parseInt(g8[i + 2])));
		}

		initialState = new NeoState(Neo, new ArrayList<Hostage>(), agents, pills, pads, hostages, 0);
		operators = new String[] { "carry", "down", "left", "right", "up", "drop", "takePill", "kill", "fly" };

	}

//	public boolean agentCollision() {
//		for (int i = 0; i < agents.size(); i++) {
//			if (Neo.x == agents.get(i).x && Neo.y == agents.get(i).y)
//				return true;
//		}
//		return false;
//	}

	public State transitionFunction(State state, String operator) {

		NeoState neoState = ((NeoState) ((NeoState) state));

//		if(neoState == null) {
//			return null;
//		}
//		Point neoCurrentPosition = neoState.getPosition();
		Point neoCurrentPosition = neoState.position;
		ArrayList<Hostage> carried = neoState.carried;
		ArrayList<Point> agents = neoState.agents;
		ArrayList<Point> pills = neoState.pills;
		ArrayList<Pad> pads = neoState.pads;
		ArrayList<Hostage> hostages = neoState.hostages;
		int damage = neoState.damage;

//		if(neoCurrentPosition == null) {
//			return null;
//		}
//		
//		System.out.println(neoCurrentPosition);

		for (int i = 0; i < hostages.size(); i++) {
			hostages.get(i).addDamage(2);
		}

		switch (operator) {
		case "up": {
			Point topAdj = new Point(neoCurrentPosition.x - 1, neoCurrentPosition.y);
			if (topAdj.x >= 0 && topAdj.x <= rows && !checkAgent(neoCurrentPosition, agents, hostages)) {
				neoCurrentPosition = topAdj;
				State newState = new NeoState(neoCurrentPosition, carried, agents, pills, pads, hostages, damage);
				if (states.containsKey(newState.toString())) {
					return null;
				}

				states.put(newState.toString(), "");
				return newState;

			} else
				return null;
		}
		case "down": {
			Point downAdj = new Point(neoCurrentPosition.x + 1, neoCurrentPosition.y);
			if (downAdj.x >= 0 && downAdj.x <= rows && !checkAgent(neoCurrentPosition, agents, hostages)) {
//				neoState.positions.add(downAdj);
				neoCurrentPosition = downAdj;
				State newState = new NeoState(neoCurrentPosition, carried, agents, pills, pads, hostages, damage);

				if (states.containsKey(newState.toString())) {
					return null;
				}
				states.put(newState.toString(), "");

				return newState;

			} else
				return null;
		}
		case "left": {
			Point leftAdj = new Point(neoCurrentPosition.x, neoCurrentPosition.y - 1);
			if (leftAdj.y >= 0 && leftAdj.y <= columns && !checkAgent(neoCurrentPosition, agents, hostages)) {
				neoCurrentPosition = leftAdj;
				State newState = new NeoState(neoCurrentPosition, carried, agents, pills, pads, hostages, damage);

				if (states.containsKey(newState.toString())) {
					return null;
				}
				states.put(newState.toString(), "");

				return newState;

			} else
				return null;
		}
		case "right": {
			Point rightAdj = new Point(neoCurrentPosition.x, neoCurrentPosition.y + 1);
			if (rightAdj.y >= 0 && rightAdj.y <= columns && !checkAgent(neoCurrentPosition, agents, hostages)) {
				neoCurrentPosition = rightAdj;
				State newState = new NeoState(neoCurrentPosition, carried, agents, pills, pads, hostages, damage);
				if (states.containsKey(newState.toString())) {
					return null;
				}

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
				neoCurrentPosition = flyTo;
			} else {
				return null;
			}

			State newState = new NeoState(neoCurrentPosition, carried, agents, pills, pads, hostages, damage);
			if (states.containsKey(newState.toString())) {
				return null;
			}

			states.put(newState.toString(), "");
			return newState;
		}
		case "kill": {

			@SuppressWarnings("unchecked")
			ArrayList<Point> agentsTemp = (ArrayList<Point>) agents.clone();

			for (int i = 0; i < agents.size(); i++) {
				if (neoCurrentPosition.x - 1 == agents.get(i).x && neoCurrentPosition.y == agents.get(i).y) // adjacent
																											// warrior
																											// down
				{
					damage += 20;
					agentsTemp.remove(agents.get(i));
				}
				if (neoCurrentPosition.x + 1 == agents.get(i).x && neoCurrentPosition.y == agents.get(i).y) // adjacent
																											// warrior
																											// up
				{
					damage += 20;
					agentsTemp.remove(agents.get(i));
				}
				if (neoCurrentPosition.x == agents.get(i).x && neoCurrentPosition.y - 1 == agents.get(i).y) // adjacent
																											// warrior
																											// left
				{
					damage += 20;
					agentsTemp.remove(agents.get(i));
				}
				if (neoCurrentPosition.x == agents.get(i).x && neoCurrentPosition.y + 1 == agents.get(i).y) // adjacent
																											// warrior
																											// right
				{
					damage += 20;
					agentsTemp.remove(agents.get(i));
				}
			}
			
			@SuppressWarnings("unchecked")
			ArrayList<Hostage> hostTemp = (ArrayList<Hostage>) hostages.clone();

			for (int i = 0; i < hostages.size(); i++) {
				if (hostages.get(i).isAgent && !hostages.get(i).isCarried) {
					if (neoCurrentPosition.x - 1 == hostages.get(i).position.x
							&& neoCurrentPosition.y == hostages.get(i).position.y) // adjacent
					// warrior
					// down
					{
						damage += 20;
						hostTemp.remove(hostages.get(i));
					}
					if (neoCurrentPosition.x + 1 == hostages.get(i).position.x
							&& neoCurrentPosition.y == hostages.get(i).position.y) // adjacent
					// warrior
					// up
					{
						damage += 20;
						hostTemp.remove(hostages.get(i));
					}
					if (neoCurrentPosition.x == hostages.get(i).position.x
							&& neoCurrentPosition.y - 1 == hostages.get(i).position.y) // adjacent
					// warrior
					// left
					{
						damage += 20;
						hostTemp.remove(hostages.get(i));
					}
					if (neoCurrentPosition.x == hostages.get(i).position.x
							&& neoCurrentPosition.y + 1 == hostages.get(i).position.y) // adjacent
					// warrior
					// right
					{
						damage += 20;
						hostTemp.remove(hostages.get(i));
					}
				}
			}

			State newState = new NeoState(neoCurrentPosition, carried, agentsTemp, pills, pads, hostTemp, damage);
			if (states.containsKey(newState.toString())) {
				return null;
			}

			states.put(newState.toString(), "");
			return newState;
		}
		case "carry": {
			Hostage hostage = null;
			for (Hostage x : hostages) {
				if (x.position.x == neoCurrentPosition.x && x.position.y == neoCurrentPosition.y) {
					hostage = x;
					break;
				}
			}

			if (hostage != null && carried.size() < this.C) {
//				System.out.println("Carry");

				hostage.isCarried = true;
				carried.add(hostage);

				State newState = new NeoState(neoCurrentPosition, carried, agents, pills, pads, hostages, damage);
				if (states.containsKey(newState.toString())) {
					return null;
				}

				states.put(newState.toString(), "");
				return newState;
			} else
				return null;

		}

		case "drop": {

			if (!carried.isEmpty() && neoCurrentPosition.x == Telephone.x && neoCurrentPosition.y == Telephone.y) {
				System.out.println(carried.size());
				for (int i = 0; i < carried.size(); i++) {
					int index = hostages.indexOf(carried.get(i));
					if(index != -1) {
						Hostage f = hostages.remove(index);
						System.out.println(true);
					}
					
				}
				carried = new ArrayList<Hostage>();

				State newState = new NeoState(neoCurrentPosition, carried, agents, pills, pads, hostages, damage);
				if (states.containsKey(newState.toString())) {
					return null;
				}
				System.out.println("DROP: " + hostages.size());
				states.put(newState.toString(), "");
				return newState;
			} else
				return null;

		}
		case "takePill": {

			damage = -20;
			State newState = new NeoState(neoCurrentPosition, carried, agents, pills, pads, hostages, damage);
			if (states.containsKey(newState.toString())) {
				return null;
			}

			states.put(newState.toString(), "");
			return newState;

		}
		default:
			return null;

		}
	}
	
	public static boolean checkAgent(Point neoLoc, ArrayList<Point> agentsArray, ArrayList<Hostage> hostagesArray) {
		
		for(Point p: agentsArray) {
			if(p.x == neoLoc.x && p.y == neoLoc.y) {
				return true;
			}
		}
		
		for(Hostage p: hostagesArray) {
			if(p.position.x == neoLoc.x && p.position.y == neoLoc.y) {
				return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) {
		String grid = "5,5;2;0,4;1,4;0,1,1,1,2,1,3,1,3,3,3,4;1,0,2,4;0,3,4,3,4,3,0,3;0,0,30,3,0,80,4,4,80";
		TheMatrix e1 = new TheMatrix(grid);
//		System.out.println(print(e1.agents));
//		System.out.println(e1.pads);
//		System.out.println(e1.);
//		System.out.println(ir4);

		// SearchTreeNode root = new SearchTreeNode(initialState,null,null,0,0);
		State ir = e1.transitionFunction((NeoState) e1.initialState, "left");
//		State ir1 = e1.transitionFunction((NeoState) ir, "collect");
		State ir2 = e1.transitionFunction((NeoState) ir, "left");
		State ir3 = e1.transitionFunction((NeoState) ir2, "kill");
//		State ir4 = e1.transitionFunction((NeoState) ir3, "right");
//		System.out.println(e1.states);
//		System.out.println(print(e1.agents));

//		System.out.println(ir3);
//		State ir4 = e1.transitionFunction((NeoState) ir3, "collect");
//		State ir5 = e1.transitionFunction((NeoState) ir4, "down");
//		State ir6 = e1.transitionFunction((NeoState) ir5, "collect");
//		State ir7 = e1.transitionFunction((NeoState) ir6, "right");
//		State ir8 = e1.transitionFunction((NeoState) ir7, "collect");
//		State ir9 = e1.transitionFunction((NeoState) ir8, "kill");
//		State ir10 = e1.transitionFunction((NeoState) ir9, "down");
//		State ir11 = e1.transitionFunction((NeoState) ir10, "down");
//		State ir12 = e1.transitionFunction((NeoState) ir11, "left");
//		State ir13 = e1.transitionFunction((NeoState) ir12, "collect");
//		State ir14 = e1.transitionFunction((NeoState) ir13, "left");
//		State ir15 = e1.transitionFunction((NeoState) ir14, "collect");
//		State ir16 = e1.transitionFunction((NeoState) ir15, "right");
//		State ir17 = e1.transitionFunction((NeoState) ir16, "up");
		// State ir18 = e1.transitionFunction((NeoState) ir17, "snap");

//		System.out.println(((NeoState)ir17).positions);
//		System.out.println("pathCost:"+((NeoState)ir17).pathCost);
//		

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

		if (s1.damage < 100 && hostages.isEmpty())
			return true;
		else
			return false;
	}
}
