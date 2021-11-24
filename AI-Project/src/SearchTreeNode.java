
public class SearchTreeNode {
	State state;
	SearchTreeNode parent;
	String operator;
	int depth;
	int pathCost;
	
	public SearchTreeNode(State state, SearchTreeNode parent, String operator,
			int depth, int pathCost) {
		
		super();
		this.state = state;
		this.parent = parent;
		this.operator = operator;
		this.depth = depth;
		this.pathCost = pathCost;
	}
}
