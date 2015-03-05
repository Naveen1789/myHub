package otherSimpleAlgorithms;

public class DecimalToBinary {
	
	private static void decToBin ( int num ) {
		int[] binary = new int[numOfBinDigits(num)] ;
		int cnt = 0 ;
		int temp = num ;
		while ( temp != 1 ) {			
			binary[cnt] = temp % 2 ;
			temp = temp / 2 ;
			cnt++ ;
		}
		binary[cnt] = 1 ;
		
		System.out.println(num + " in binary : ");
		for ( int looper = ( binary.length - 1 ) ; looper >= 0  ; looper-- ) {
			System.out.print(binary[looper]);
		}
		System.out.println();
	}
	
	private static int numOfBinDigits ( int num ) {
		if ( num == 1 ) {
			return 1 ;
		}
		else {
			return ( 1 + numOfBinDigits(num / 2) );
		}
		
	}
	
	/*
	
	private static int numOfBinDigits2 ( int num ) {
		
		int pow = 0 ;
		
		do {
			pow++ ;			
		}while ( num >= (int)( Math.pow(2 , pow) ) ) ;
		
		return ( pow ) ;
	}
	*/
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		decToBin(5);
		decToBin(8);
		decToBin(10);
		decToBin(16);
				
	}
	
}
