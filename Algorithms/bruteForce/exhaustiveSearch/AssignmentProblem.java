package bruteForce.exhaustiveSearch;

import java.util.ArrayList;

public class AssignmentProblem {
	
	static int[][] assignmentMatrix = { { 10 , 3 , 8 , 9 } ,
								 		{ 7 , 5 , 4 , 8 } ,
								 		{ 6 , 9 , 2 , 9 } ,
								 		{ 8 , 7 , 10 , 5 }
										};
	
	public static int cntr = 0 ; // Counter
	
	public static int numOfElements = 0 ; // Number of elements
	
	static int minCost = 999 ;
	
	static ArrayList<Integer> optimalSolution = new ArrayList<Integer>(); 

	
	/*
	This method adds an element ( elem ) at all possible positions for a given ArrayList
	
	Ex :
	ArrayList - [ 1 , 2 ]
	
	Possible Positions -  
	i. [ _ , 1 , 2 ]
	ii. [ 1 , _ , 2 ]
	iii. [ 1 , 2 , _ ]
	
	*/
	
	private static void addNextElement ( ArrayList<Integer> ar1 , ArrayList<Integer> ar2 , Integer elem ) 
	{
		
		ArrayList<Integer> tempArl1 = new ArrayList<Integer>(); // Creating a temporary ArrayList to duplicate 'ar1'
		ArrayList<Integer> tempArl2 = new ArrayList<Integer>(); // Creating a temporary ArrayList to duplicate 'ar2'
		
		tempArl1.addAll(ar1); // Duplicating 'ar1'
		tempArl2.addAll(ar2); // Duplicating 'ar2'
		
		// Adding elem at all possible positions
		for ( int i = 0 ; i <= tempArl2.size() ; i++ )
		{
			
			tempArl2.add(i,elem); // Adding an element ( elem ) to the duplicate ArrayList ( at one of the possible positions )
			/*
			Recursion : Pick the next element .			
			Picking the next element to be added
			[ Calls the function 'pickNextElement' , which retrieves the element ( Item at index 0 in 'tempArl1' ) to be next added ]
			*/
			pickNextElement( tempArl1 , tempArl2 ); 
			
			tempArl2.remove(i); // Removing the added element ( elem )

		}
		
	}
	
	/*
	This method picks the next element to be added ( 'elem' of addNextElement ).
	
	The element at index [0] in the ArrayList ar1 is picked.
	*/
	private static void pickNextElement( ArrayList<Integer> ar1 , ArrayList<Integer> ar2 )
	{
		ArrayList<Integer> tempArl1 = new ArrayList<Integer>(); // Creating a temporary ArrayList to duplicate 'ar1'
		ArrayList<Integer> tempArl2 = new ArrayList<Integer>(); // Creating a temporary ArrayList to duplicate 'ar2'
		
		tempArl1.addAll(ar1); // Duplicating 'ar1'
		tempArl2.addAll(ar2); // Duplicating 'ar2'
		
		/*
		Display when ar2 has all the elements of the Initial ArrayList ( ar2 is one of the permutations )
		*/
		if ( ar2.size() == numOfElements )
		{
			ArrayList<Integer> solution = new ArrayList<Integer>(); // Creating a temporary ArrayList to duplicate 'ar2'
			solution.addAll(ar2);
			
			cntr++ ; // Counter
			
			System.out.println( solution );
			
			calculateCost(solution) ;

			return ;
		}
		
		/*
		Pick the element at index [0] in tempArl1.
		Call the addNextElement method.
		*/
		for ( int i1 = 0 ; i1 < tempArl1.size() ; i1++ )
		{
			Integer elem = tempArl1.get(0);
			tempArl1.remove(0);
			addNextElement( tempArl1 , tempArl2 , elem );
			
		}
		
	}
	
	private static void calculateCost ( ArrayList<Integer> arList ) {
		
		int cost = 0 ;
		
		for ( int i = 0 ; i < arList.size() ; i++ ) {
			cost = cost + assignmentMatrix[ (arList.get(i)) - 1 ][ i ] ;
		}
		System.out.println("Cost : " + cost + "\n");
		
		if ( cost < minCost ) {
			minCost = cost ;
			optimalSolution = arList ;
			
		}
		
	}
	

	public static void main ( String args[] )
	{	
		
		// Initial Array , holding the Initial values :
		ArrayList<Integer> arl1 = new ArrayList<Integer>();
		
		for ( int i = 0 ; i < assignmentMatrix.length ; i++ ) {
			arl1.add(i + 1);
		}
		
		numOfElements = arl1.size();
		
		ArrayList<Integer> tmpArrList = new ArrayList<Integer>();
		pickNextElement( arl1 , tmpArrList );
		
		System.out.println("Optimal Solution : " + optimalSolution );
		for ( int i = 0 ; i < optimalSolution.size() ; i++ ) {
			System.out.println("Person # " + optimalSolution.get(i) + " is assigned job # " + ( i+ 1 ));
		}
		System.out.println("Minimal Cost : " + minCost );
		
	}

}
