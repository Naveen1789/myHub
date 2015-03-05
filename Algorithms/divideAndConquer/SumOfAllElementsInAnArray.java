package divideAndConquer;

public class SumOfAllElementsInAnArray {
	
	public static void main ( String args[] ) {
		
		int[] a = { 1 , 2 , 3 , 4 , 5 };
		int[] b = { 1 , 2 , 3 , 4 , 5 , 6 };
		System.out.println("Sum of all elements of the array a { 1 , 2 , 3 , 4 , 5 } : " + add(a , 0 , ( a.length - 1 )) );
		System.out.println("Sum of all elements of the array b { 1 , 2 , 3 , 4 , 5 , 6 } : " + add(b , 0 , ( b.length - 1 )) );
		
	}
	
	private static int add( int[] arr , int low , int high ) {
		
		if ( low == high ) {
			return arr[low];
		}
		else {			
			int mid = ( low + high ) / 2 ;
			return add( arr , low , mid ) + add( arr , mid+1 , high );
		}
	}

}
