package recursiveAlgorithms;

public class Fibonacci {
	
	private static int fib ( int num ) {
		
		if ( num == 1 ) {
			return 0 ;
		}
		else if ( num == 2 ) {
			return 1 ;
		}
		else {
			return (fib(num-1) + fib(num-2));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("fib(5) = " + fib(5));
		System.out.println("fib(6) = " + fib(6));
		System.out.println("fib(10) = " + fib(10));
		System.out.println("fib(11) = " + fib(11));

	}

}
