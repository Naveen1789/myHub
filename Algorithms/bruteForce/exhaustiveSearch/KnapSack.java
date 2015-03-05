package bruteForce.exhaustiveSearch;

import java.util.ArrayList;

public class KnapSack {
	
	static int[] weights = { 20 , 25 , 10 } ;
	static int[] profits = { 30 , 40 , 35 } ;
	
	static int combNumber = 0 ;
	
	static int weightLimit = 40 ;
	static int maxProfit = 0 ;
	static ArrayList<Integer> optimalSolution = new ArrayList<Integer>();
	
	private static void combinations( ArrayList<Integer> combination , ArrayList<Integer> arList , int numOfElemsInThisCombination ) {
		

		ArrayList<Integer> tempArList = new ArrayList<Integer>(); // Creating a temporary ArrayList to duplicate 'arList'
		tempArList.addAll(arList); // Duplicating 'arList'
		
		while ( tempArList.size() > 0 ) {
			
			ArrayList<Integer> tempCombination = new ArrayList<Integer>(); // Creating a temporary ArrayList to duplicate 'combination'
			tempCombination.addAll(combination); // Duplicating 'combination'
			
			int elem = tempArList.get(0);
			tempArList.remove(0);
			
			tempCombination.add(elem);
			
			if (tempCombination.size() == numOfElemsInThisCombination ) {
				combNumber++;
				System.out.println(combNumber + " : " + tempCombination);
				calculateProfit(tempCombination);
			}
			else {
				combinations(tempCombination , tempArList , numOfElemsInThisCombination);
			}
			
		}
	
	}
	
	private static void calculateProfit( ArrayList<Integer> comb ) {
		int profit = 0 ;
		int weight = 0 ;
		for ( int i = 0 ; i < comb.size() ; i++ ) {
			weight = weight + weights[(comb.get(i)) - 1 ] ; 
			profit = profit + profits[(comb.get(i)) - 1] ;
		}
		
		System.out.println("Weight : " + weight);
		
		if ( weight > weightLimit ) {
			System.out.println("Not Feasible.");
		}
		else {
			System.out.println("Profit : " + profit + "\n");
			if ( profit > maxProfit ) {
				maxProfit = profit ;
				optimalSolution = comb ;
			}
			
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		ArrayList<Integer> arl1 = new ArrayList<Integer>();
		
		for ( int temp = 0 ; temp < weights.length ; temp++ ) {
			arl1.add(temp + 1);
		}
		
		for ( int i = 0 ; i < arl1.size() ; i++ ) {
			System.out.println("Loop # : " + ( i + 1 ));
			ArrayList<Integer> combination = new ArrayList<Integer>();
			combinations(combination , arl1 , ( i + 1 ) );
		}
		
		System.out.println("========================\nMax Profit : " + maxProfit );
		System.out.println("Optimal Solution : " + optimalSolution );
	}

}
