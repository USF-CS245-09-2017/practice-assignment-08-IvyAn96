

import java.util.Stack;

public class GraphAdjMatrix implements Graph {

	private int[][] edgeMatrix;
	private int size;
	
	/**
	 * constructor 
	 * set up matrix's length to 0
	 */
	public GraphAdjMatrix() {
		edgeMatrix = new int[0][0];
		size = 0;
	}
	
	/**
	 * Constructor
	 * @param size
	 * num of vertices in the graph
	 */
	public GraphAdjMatrix(int vertices){
		size= vertices;
		edgeMatrix = new int[size][size];
	}
	
	/**
	 * @param v1 v2
	 * add edge between two vertices by setting 0 to 1
	 */
	 @Override
	public void addEdge(int v1, int v2){
		// if v1 or v2 is valid number
		if(v1 < 0 || v2 < 0 || v1 > edgeMatrix.length || v2 > edgeMatrix.length){
			System.out.print("Input vertex not exist");
		}
		edgeMatrix[v1][v2] = 1;
	}
	 
	 /**
	  * 
	  * @param vertex
	  * calculate how many edge from other vertices to given vertex
	  * @return inDegree
	  */
	public int inDegree(int vertex){
		int degree  = 0;
		for(int i = 0; i < edgeMatrix.length; i++){
			if(edgeMatrix[i][vertex] != 0){
				degree++;
			}
		}
		return degree;
	}
	
	/**
	 * 
	 * @param incident
	 * find inDegree of vertex is 0
	 * @return vertex 
	 */
	public int findZero(int[] incident){
		for(int i = 0; i < incident.length; i++){
			if(incident[i] == 0){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Topological Sort for this graph
	 */
	
	 @Override
	public void topologicalSort(){
		int[]  incident = new int[edgeMatrix.length];
		int count = 0;
		for(int i = 0; i < edgeMatrix.length; i++){
			incident[i] = inDegree(i);
		}
		Stack<Integer> stack = new Stack<Integer>();
		
			int position = findZero(incident);
			if(position == 0){
				stack.push(position);
				}
			
		while(!stack.isEmpty()){
			int vertex = stack.pop(); 
			System.out.print(vertex + " ");
			count = count + 1;
			for(int i = 0; i < edgeMatrix.length; i ++){
				incident[i]--;
				if(incident[i] == 0){
					stack.push(i);
				}
			}
		}
		if(count != size){
			 System.out.println("Graph has a circle");
		}
		System.out.println("");
		
	}
	 
	 /**
	  * @param vertex
	  * Collect all the neighbors of the given vertex
	  * @return int[] newNeighbor
	  */
	 @Override
	public int[] neighbors(int vertex){
		 if(vertex > size){
			 System.out.println("there is no this vertex");
		 }
		int[] neightbors = new int[size];
		int pos = 0;
		for(int i = 0; i < size; i++){
			if(edgeMatrix[vertex][i] == 1 && i != vertex){
				neightbors[pos] = i;
				pos++;
			}
			
		}
		
		//given a new array : copy the valid number in neighbor
		int[] newNeighbor = new int[pos];
		System.arraycopy(neightbors, 0, newNeighbor, 0, pos);
		return newNeighbor;
		
	}
	
}
