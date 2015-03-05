package primeNumbers;

public class SieveOfEratosthenes {
	
	public static void main ( String args[] ) {
		
		
		int[] arr = new int[50] ;
		
		for ( int init = 0 ; init < arr.length ; init++ ) {
			arr[init] = init ;
		}
		
		for ( int begin = 2 ; begin <= (int) Math.sqrt(arr.length) ; begin++ ) {
			if ( arr[begin] != 0 ) {				
				for ( int temp = begin * begin ; temp < arr.length ; temp = temp + begin ) {
					arr[temp] = 0 ;
				}
			}
		}
		int cntr = 0 ;
		for ( int disp = 2 ; disp < arr.length ; disp++ ) {
			
			if ( arr[disp] != 0 ) {
			System.out.print(arr[disp] + "\t");
			cntr++ ;
			if ( cntr % 5 == 0 ) {
				System.out.println();
			}
			}
		}
		
	}

}
