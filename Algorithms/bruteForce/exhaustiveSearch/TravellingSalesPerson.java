package bruteForce.exhaustiveSearch;

import java.util.ArrayList;
import java.util.Scanner;

public class TravellingSalesPerson {
	
	static int[][] costMatrix = {
			{ 0 , 3 , 6 , 8 } ,
			{ 3 , 0 , 9 , 4 } ,
			{ 6 , 9 , 0 , 2 } ,
			{ 8 , 4 , 2 , 0 } 
		} ;
	
	static int numOfCities = costMatrix.length ;
	
	static int minCost = 999 ;
	
	static ArrayList<Integer> optimalPath = new ArrayList<Integer>();
	
	static int cntr = 0 ; // Counter
	
	static int source = 0 ;
	
	/*
	private static int findDist ( int src , int dest ) {
		return costMatrix[src][dest] ;
	}
	*/
	
	private static void path( ArrayList<Integer> cityList , ArrayList<Integer> path , int nextCity ) {
		
		ArrayList<Integer> tempCityList = new ArrayList<Integer>(); // Creating a temporary ArrayList to duplicate 'cityList'
		ArrayList<Integer> tempPath = new ArrayList<Integer>(); // Creating a temporary ArrayList to duplicate 'path'
		
		tempCityList.addAll(cityList); // Duplicating 'cityList'
		tempPath.addAll(path); // Duplicating 'path'
		
		for ( int i = 0 ;  i <= tempPath.size(); i++ ) {			
			tempPath.add(i,nextCity);
			pickNextCity(tempCityList , tempPath);
			tempPath.remove(i);
		}
	}
	
	private static void calculateCost ( ArrayList<Integer> path ) {
		
		int cost = 0 ;
		for ( int i = 0 ; i < ( path.size() - 1 ) ; i++ ) {
			if ( costMatrix[path.get(i)][path.get(i+1)] == 99 ) {
				cost = 999 ;
				break ;
			}
			else {
			cost = cost + costMatrix[path.get(i)][path.get(i+1)] ;
			}
		}
		
		System.out.println("Cost : " + cost );
		
		if ( cost < minCost ) {
			minCost = cost ;
			optimalPath = path ;
		}
	}
	
	private static void pickNextCity ( ArrayList<Integer> cityList , ArrayList<Integer> path ) {
		
		ArrayList<Integer> tempCityList = new ArrayList<Integer>(); // Creating a temporary ArrayList to duplicate 'cityList'
		ArrayList<Integer> tempPath = new ArrayList<Integer>(); // Creating a temporary ArrayList to duplicate 'path'
		
		tempCityList.addAll(cityList); // Duplicating 'cityList'
		tempPath.addAll(path); // Duplicating 'path'
		
		if ( tempCityList.size() > 0 ) {
			int nextCity = tempCityList.get(0);
			tempCityList.remove(0);			
			path( tempCityList , tempPath , nextCity );
		
		}
		else {
			cntr++ ;
			System.out.println(cntr);
			
			tempPath.add(0,source);
			tempPath.add(source);
			
			System.out.println(tempPath);			
			calculateCost(tempPath) ;			
			return ;
			
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean read = false ;
		
		ArrayList<Integer> stops = new ArrayList<Integer>();
		
		while ( !read ) {
			System.out.println("Enter the starting point ( 0 - " + ( numOfCities - 1 ) + " ) : ");
			Scanner in = new Scanner(System.in);
			read = in.hasNextInt() ;
			
			if ( read ) {
				
				source = in.nextInt();
				
				if( source >= numOfCities ) {
					System.out.println("Invalid option( input out of range ).");
					read = false ;
				}
			}
			else {
				System.out.println("Invalid option ( only Integers between 0 - " + ( numOfCities - 1 ) + " ) is allowed." );
			}			
		}
		
		for ( int i = 0 ; i < numOfCities ; i++ ) {
			if ( i != source ) {
				stops.add(i);
			}
		}
		
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		pickNextCity(stops , path);
		
		System.out.println("\n\nOptimal Path : " + optimalPath);
		System.out.println("Min Cost : " + minCost);

	}

}
