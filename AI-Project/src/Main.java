import java.awt.Point;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//	    System.out.println(System.getProperty("java.runtime.version"));
//		System.out.println(genGrid());
//		System.out.println(genRandomInt(6, 5));
		
//		String grid = genGrid();
		String grid= "5,5;2;0,4;1,4;0,1,1,1,2,1,3,1,3,3,3,4;1,0,2,4;0,3,4,3,4,3,0,3;0,0,30,3,0,80,4,4,80";

		String s = solve(grid,"BF",false);
		System.out.println(s);

	}
	
	public static String solve(String grid, String strategy, boolean visualize) {
		TheMatrix theMatrix = new TheMatrix(grid);
		
		SearchTreeNode node = theMatrix.generalSearch(strategy);
		String result = "";
//		System.out.println(node);

		if(node != null){
			int pathcost = node.pathCost;
			result += "snap";
			while(node.parent != null){
				result = node.operator + ", "+ result;
				node = node.parent;
			}
			result +=";"+pathcost+";"+theMatrix.nodesExpanded;
		}
		
		return result;
		
	}

	public static String genGrid() {
		String grid = "";
		ArrayList<Point> positions = new ArrayList<Point>();
		
		//Grid Size
		int M = genRandomInt(5, 15);
		int N = genRandomInt(5, 15);

		int maxPositions = M * N;

		grid += M + "," + N + ";";

		//C is the max number of hostages Neo can carry
		int C = genRandomInt(1, 4);

		grid += C + ";";

		//Set Neo's location
		int NeoX = genRandomInt(0, M - 1);
		int NeoY = genRandomInt(0, N - 1);

		Point neo = new Point(NeoX, NeoY);
		positions.add(neo);

		grid += NeoX + "," + NeoY + ";";

		//Set telephone's location
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

		//Generate number of hostages
		int numHostages = genRandomInt(3, 10);

		String hostages = "";
		//Set locations of the hostages on the grid
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
				
		//Generate number of pills
		int numPills = genRandomInt(1, numHostages);
		
		//Set locations of the pills on the grid
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

		//Generate number of agents
		int numAgents = genRandomInt(1, maxPositions - positions.size() - 5);
		String agents = "";
		
		//Set locations of the agents on the grid
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
		
		//Generate number of pads
		int numPads = genRandomInt(1, maxPositions - positions.size());
		String pads = "";
		
		//Set locations of the pads on the grid
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

}
