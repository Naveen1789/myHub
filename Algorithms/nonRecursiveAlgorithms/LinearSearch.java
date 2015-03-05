package nonRecursiveAlgorithms;

import java.util.Scanner;

public class LinearSearch {
	
	public static void main ( String args[] ) {
		
		int[] arr = { 17 , 22 , 5 , 25 , 9 , 12 , 32 } ;
		int key = 0 ;
		boolean read = true ;
		boolean found = false ;
		
		
		System.out.println("Enter the integer to be searched : ");
		Scanner in = new Scanner(System.in);
		read = in.hasNextInt() ;				
			
		if ( read ) {
			key = in.nextInt();
			for ( int looper = 0 ; looper < arr.length ; looper++ ) {
				if ( arr[looper] == key ) {
					System.out.println("Key found at : " + ( looper + 1 ) );
					found = true ;
					break ;
				}
			}
			
			if ( !found ) {
				System.out.println("Key not found");
			}
		
		}
		else {
			System.out.println("Invalid Input.\nPlease enter an integer.");
		}
	}

}
