package code;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;


public abstract class SearchProblem {
	State initialState;
	String [] operators;
	int nodesExpanded = 0;
	int currlevel = 0;
	int count = 0;
	Hashtable<String, String> states = new Hashtable<String, String>();
	
	public abstract boolean goalTest(SearchTreeNode node);
	
	public abstract State transitionFunction(State state, String operator, String strategy);
	
	public ArrayList<SearchTreeNode> expand(SearchTreeNode node, String strategy){
		ArrayList<SearchTreeNode> nodes = new ArrayList<SearchTreeNode>();
		nodesExpanded +=1;
		for(int i =0; i<operators.length ; i++)
		{
			State state = transitionFunction(node.state, operators[i],strategy);
			if(state !=null){
				SearchTreeNode nodeTemp = new SearchTreeNode(state,node,operators[i],node.depth+1,state.pathCost, state.heuristicCost, state.asCost);
				nodes.add(nodeTemp);
			}
		}
		return nodes;
	}
	
	public ArrayList<SearchTreeNode> qingFunction(String strategy, ArrayList<SearchTreeNode> oldNodes, ArrayList<SearchTreeNode> newNodes){
		switch(strategy){
		case "BF":{
			 oldNodes.addAll(newNodes);
			 count++;
			 return oldNodes;
		}
		case "DF":{
			 newNodes.addAll(oldNodes);
			 return newNodes;
		}
		case "ID":{
			
			if(!newNodes.isEmpty() && newNodes.get(0).depth <= currlevel){
				newNodes.addAll(oldNodes);
				return newNodes;
			}
			else if(oldNodes.isEmpty()){
				currlevel +=1;
				states= new Hashtable<String,String>();
				oldNodes.add(new SearchTreeNode(this.initialState,null,null,0,0, 0, 0)); // new queue with the root node only
				return oldNodes;
			}
			return oldNodes;
			
		}
		
		case "UC":{
			oldNodes.addAll(newNodes);
			return sort(oldNodes);
		}
		
		case "GR1":{
			oldNodes.addAll(newNodes);
			return sortGreedy(oldNodes);
		}
		case "GR2":{
			oldNodes.addAll(newNodes);
			return sortGreedy(oldNodes);
		}
		
		case "AS1":{
			oldNodes.addAll(newNodes);
			return sortAS(oldNodes);
		}
		case "AS2":{
			oldNodes.addAll(newNodes);
			return sortAS(oldNodes);
		}
		
		 default:
			return oldNodes;
			
		}
	}

	public ArrayList<SearchTreeNode> sort(ArrayList<SearchTreeNode> nodes)  
    { 
        int n = nodes.size(); 
        for (int i = 1; i < n; ++i) { 
            int key = nodes.get(i).pathCost; 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && nodes.get(j).pathCost > key) { 
            	nodes.get(j+1).pathCost = nodes.get(j).pathCost; 
                j = j - 1; 
            } 
            nodes.get(j+1).pathCost= key; 
        }
        return nodes;
    }
	
	public ArrayList<SearchTreeNode> sortGreedy(ArrayList<SearchTreeNode> nodes)  
    { 
        int n = nodes.size(); 
        for (int i = 1; i < n; ++i) { 
            int key = nodes.get(i).heuristicCost; 
            int j = i - 1; 

            while (j >= 0 && nodes.get(j).heuristicCost > key) { 
            	nodes.get(j+1).heuristicCost = nodes.get(j).heuristicCost; 
                j = j - 1; 
            } 
            nodes.get(j+1).heuristicCost = key; 
        }
        return nodes;
    }
	
	public ArrayList<SearchTreeNode> sortAS(ArrayList<SearchTreeNode> nodes)  
    { 
		int n = nodes.size(); 
        for (int i = 1; i < n; ++i) { 
            int key = nodes.get(i).asCost; 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && nodes.get(j).asCost > key) { 
            	nodes.get(j+1).asCost = nodes.get(j).asCost; 
                j = j - 1; 
            }
            nodes.get(j+1).asCost= key; 
        }
        return nodes;
    }
	
	static boolean goalTest;
	
	public SearchTreeNode generalSearch(String strategy){
		ArrayList<SearchTreeNode> nodes = new ArrayList<SearchTreeNode>();
		nodes.add(new SearchTreeNode(this.initialState,null,null,0,0, 0, 0));
		
		while(!nodes.isEmpty()){
			SearchTreeNode node = nodes.remove(0);
			goalTest=this.goalTest(node);
			if(this.goalTest(node)){
				return node;
			}
//			if(count == 31) {
//				System.exit(0);
//			}
			nodes = qingFunction(strategy, nodes, expand(node,strategy));
		}	
		return null;
	}
	public static boolean getGoalTest(){
		return goalTest;
	}
}
