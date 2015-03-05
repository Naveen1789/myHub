package decreaseAndConquer.graphTraversals;

public class DFS {
	
	private static int[][] adjMatrix1 = {{ 99 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9  } ,
		 { 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } ,
		 { 2 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } ,
		 { 3 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 0 , 0 } ,
		 { 4 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 } ,
		 { 5 , 0  ,1 , 0 , 1 , 0 , 0 , 0 , 0 , 0 } ,
		 { 6 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } ,
		 { 7 , 0 , 0 , 0 , 0 , 5 , 0 , 0 , 0 , 1 } ,
		 { 8 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } ,
		 { 9 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 } ,
		 
};
		
		/*
		{{ 99 , 1 , 2 , 3 , 4 , 5 , 6 , 7 } ,
			{ 1 , 0 , 1 , 1 , 0 , 0 , 0 , 0 } ,
			{ 2 , 0 , 0 , 0 , 0 , 1 , 0 , 1 } ,
			{ 3 , 0 , 0 , 0 , 0 , 0 , 1 , 0 } ,
			{ 4 , 1 , 1 , 1 , 0 , 0 , 1 , 1 } ,
			{ 5 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } ,
			{ 6 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } ,
			{ 7 , 0 , 0 , 0 , 0 , 1 , 1 , 0 }
		};
		*/
	
	private static int[][] adjMatrix2 = {{ 99 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 } ,
		 { 1 , 0 , 0 , 1 , 1 , 1 , 0 , 0 , 0 , 0 , 0 } ,
		 { 2 , 0 , 0 , 0 , 0 , 1 , 1 , 0 , 0 , 0 , 0 } ,
		 { 3 , 1 , 0 , 0 , 1 , 0 , 1 , 0 , 0 , 0 , 0 } ,
		 { 4 , 1 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 } ,
		 { 5 , 1  ,1 , 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 } ,
		 { 6 , 0 , 1 , 1 , 0 , 1 , 0 , 0 , 0 , 0 , 0 } ,
		 { 7 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 1 } ,
		 { 8 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 1 , 0 } ,
		 { 9 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 1 } ,
		 { 10 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 1 , 0 }
};
	
	static int pushSubScript = 0 ;
	static int popSubScript = 0 ;
	
	static boolean cyclicGraph = false ;
	
	static int[][] traversalOrder = new int[adjMatrix1.length][3];
	
	
	private static void topologicalSortUsingDFS () {
		
		if ( cyclicGraph ) {
			System.out.println("The given graph is cyclic");
		}
		else {
		Stack topSort = new Stack();
		
		for ( int i = 1 ; i < traversalOrder.length ; i++ ) {
			for ( int j = 1 ; j < traversalOrder.length ; j++ ) {
				if ( traversalOrder[j][2] == i ) {
					topSort.push(j);
					break;
				}
			}
		}		
		topSort.displayTopToBottom();
		}
	}
	
//	private static void checkForCyclicity ( int[][] adjMatr ) {
//		for ( int i = 1 ; i < adjMatr.length ; i++ ) {
//			for (int j = 1 ; j < i ; j++ ) {
//				if ( adjMatr[i][j] != adjMatr[j][i] ) {
//					cyclicGraph = true ;
//					break ;
//				}
//			}
//			if ( cyclicGraph ) {
//				break ;
//			}
//		}
//	}
	
	
	private static void traverseDF ( int[][] adjMatrix , Stack st , int[] nodesVisited , int curNode ) {
		
		while ( st.getTopOfStack() >= 0 ) {
			for ( int i = 1 ; i < adjMatrix[curNode].length ; i++ ) {

				if ( ( adjMatrix[curNode][i] != 0 ) && ( nodesVisited[i] != 1 ) ) {
					System.out.println(curNode + " - " + i );
					st.push(i);
					
					pushSubScript++ ;
					traversalOrder[i][1] = pushSubScript ;
					
					nodesVisited[i] = 1 ;
					traverseDF(adjMatrix , st , nodesVisited , i);
				}
			}
			int item = st.pop();
			
			popSubScript++ ;
			traversalOrder[item][2] = popSubScript ;
			
			break ;
		}
	}

	private static void dfs( int[][] adjMatrix ) {
		
		Stack st = new Stack();
		
		int[] nodesVisited = new int[adjMatrix.length];
		
		st.push(1);
		nodesVisited[1] = 1 ;
		
		pushSubScript++ ;
		traversalOrder[1][1] = pushSubScript ;
		
		traverseDF(adjMatrix , st , nodesVisited , 1 );
		
		boolean done = false ;
		
		while ( !done ) {
			done = true ;
			for ( int i = 1 ; i < nodesVisited.length ; i++ ) {
				if ( nodesVisited[i] == 0 ) {
					done = false ;
					st.push(i);
					nodesVisited[i] = 1 ;
				
					pushSubScript++ ;
					traversalOrder[i][1] = pushSubScript ;
					
					traverseDF( adjMatrix , st , nodesVisited , i );
				}
			}
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for ( int i = 1 ; i < traversalOrder.length ; i++ ) {
			traversalOrder[i][0] = i ;
		}
		
//		checkForCyclicity(adjMatrix1);
		
		dfs(adjMatrix1);
		
		System.out.println("Traversal order for DFS : ");
		for ( int i = 1 ; i < traversalOrder.length ; i++ ) {
			for ( int j = 0 ; j < traversalOrder[i].length ; j++ ) {
				System.out.print(traversalOrder[i][j] + "\t");
			}
			System.out.println();
		}
		
		topologicalSortUsingDFS();
	}

}

//=======================================S=T=A=C=K=================================//

class Stack {
	int[] stk = new int[10] ;
	
	int tos = -1 ;
	
	public int getTopOfStack() {
		return this.tos ;
	}
	
	public void push ( int item ) {
		if ( tos >= 9 ) {
			System.out.println("Stack overflow");
		}
		else {
			tos++ ;
			stk[tos] = item ;
		}
	}
	
	public int pop () {
		if ( tos == -1 ) {
			System.out.println("Stack empty");
			return -1 ;
		}
		else {
			int item = stk[tos] ;
			tos-- ;
			return item ;
		}
	}
	
	public void displayBottomToTop() {
		for ( int i = 0 ; i <= tos ; i++ ) {
			System.out.print(stk[i] + "\t");
		}
		System.out.println();
	}
	
	public void displayTopToBottom() {
		
		System.out.println("Topological Sort -");
		for ( int i = tos ; i >= 0 ; i-- ) {
			System.out.print(stk[i] + "\t");
		}
		System.out.println();
	}
}
