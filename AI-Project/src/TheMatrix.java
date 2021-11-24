import java.awt.Point;
import java.util.ArrayList;

public class TheMatrix extends SearchProblem {

	int rows;
	int columns;
	Point Neo;
	Point Telephone;
	int C;

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
		this.Neo = new Point(Integer.parseInt(g3[0]), Integer.parseInt(g3[1]));

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
			pads.add(new Pad(
					new Point(Integer.parseInt(g7[i]), Integer.parseInt(g7[i + 1])),
					new Point(Integer.parseInt(g7[i + 2]), Integer.parseInt(g7[i + 3]))
					));
		}
		
		String[] g8 = g1[7].split(",");
		ArrayList<Hostage> hostages = new ArrayList<Hostage>();
		for (int i = 0; i + 2 < g8.length; i = i + 3) {
			hostages.add(new Hostage(
					new Point(Integer.parseInt(g8[i]), Integer.parseInt(g8[i + 1])),
					Integer.parseInt(g8[i + 2])
					));
		}
		
	}

	public boolean goalTest(SearchTreeNode node) {

		return false;
	}

	public State transitionFunction(State state, String operator) {

		return null;
	}
}
