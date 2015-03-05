package nonRecursiveAlgorithms;

public class Maximum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arr = { 17 , 22 , 5 , 25 , 9 , 12 , 32 } ;
		
		int max = arr[0] ;
		
		for ( int looper = 1 ; looper < arr.length ; looper++ ) {
			if ( arr[looper] > max ) {
				max = arr[looper] ;
			}
		}
		
		System.out.println("Max : " + max);

	}

}
