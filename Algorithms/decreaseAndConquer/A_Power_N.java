package decreaseAndConquer;

public class A_Power_N {
	
	private static int aPowerN_iterative ( int a , int n ) {
		if ( n < 0 ) {
			return -1 ;
		}
		else if ( n == 0 ) {
			return 1 ;
		}
		else {
			int aPowN = a ;
			for ( int i = 2 ; i <= n ; i++ ) {
				aPowN = aPowN * a ;
			}
			return aPowN ;
		}
	}
	
	private static int aPowerN_recursive_decByOne ( int a , int n ) {
		if ( n == 1 ) {
			return a ;
		}
		else {
			return ( a * (aPowerN_recursive_decByOne(a,n-1)));
		}
	}
	
	private static int aPowerN_recursive_decByAConst ( int a , int n ) {
		if ( n == 1 ) {
			return a ;
		}
		else {
			if ( n % 2 == 0 ) {
			return ((aPowerN_recursive_decByAConst(a,n/2)) * (aPowerN_recursive_decByAConst(a,n/2)));
			}
			else {
				return ((aPowerN_recursive_decByAConst(a,(n-1)/2)) * (aPowerN_recursive_decByAConst(a,(n-1)/2)) * a );
			}
		}
	}	
	
	public static void main ( String args[] ) {
		System.out.println("Iterative : ");
		System.out.println("2 ^ 5 = " + aPowerN_iterative(2,5));
		System.out.println("3 ^ 4 = " + aPowerN_iterative(3,4));
		
		System.out.println("Recursive ( Dec by one ) : ");
		System.out.println("2 ^ 5 = " + aPowerN_recursive_decByOne(2,5));
		System.out.println("3 ^ 4 = " + aPowerN_recursive_decByOne(3,4));

		System.out.println("Recursive ( Dec by a const ) : ");
		System.out.println("2 ^ 5 = " + aPowerN_recursive_decByAConst(2,5));
		System.out.println("3 ^ 4 = " + aPowerN_recursive_decByAConst(3,4));
		
	}

}
