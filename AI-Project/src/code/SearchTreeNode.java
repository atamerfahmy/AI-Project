package code;

public class SearchTreeNode {
	State state;
	SearchTreeNode parent;
	String operator;
	int depth;
	int pathCost;
	int heuristicCost;
	int asCost;

	public SearchTreeNode(State state, SearchTreeNode parent, String operator, int depth, int pathCost,
			int heuristicCost, int asCost) {

		super();
		this.state = state;
		this.parent = parent;
		this.operator = operator;
		this.depth = depth;
		this.pathCost = pathCost;
		this.heuristicCost = heuristicCost;
		this.asCost = asCost;
		
	}
}
