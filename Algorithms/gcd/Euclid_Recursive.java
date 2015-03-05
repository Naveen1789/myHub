package gcd;

public class Euclid_Recursive {
	
	private int getGCD(int a , int b ) {
		if ( b == 0 ) {
			return a ;
		}
		return getGCD( b , a % b ) ;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Euclid_Recursive demo = new Euclid_Recursive();
		
		int gcd1 = demo.getGCD(6,10);
		System.out.println("GCD(6,10) = " + gcd1);
		
		int gcd2 = demo.getGCD(17,5);
		System.out.println("GCD(17,5) = " + gcd2);
		
		int gcd3 = demo.getGCD(175,10);
		System.out.println("GCD(175,10) = " + gcd3);
				
	}

}
