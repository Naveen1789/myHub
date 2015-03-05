package recursiveAlgorithms;

public class Factorial {
	
	private static int fact ( int num ) {
		if ( num == 1 ) {
			return 1 ;
		}
		else {
			return ( num * fact( num - 1 ) ) ;
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println("5! = " + fact(5));
		System.out.println("6! = " + fact(6));
		System.out.println("10! = " + fact(10));
		System.out.println("11! = " + fact(11));
		
	}

}
