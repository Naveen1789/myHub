package gcd;

public class RepSubtraction_Iterative {
	
	private int getGCD( int a , int b ) {
		
		while ( a != b ) {
			if ( a > b ) {
				a = a - b ;
			}
			else {
				b = b - a ;
			}	
		}
		return a ;
				
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		RepSubtraction_Iterative demo = new RepSubtraction_Iterative();
		
		int gcd1 = demo.getGCD(6,10);
		System.out.println("GCD(6,10) = " + gcd1);
		
		int gcd2 = demo.getGCD(17,5);
		System.out.println("GCD(17,5) = " + gcd2);
		
		int gcd3 = demo.getGCD(175,10);
		System.out.println("GCD(175,10) = " + gcd3);

	}

}
