package bruteForce;

public class BubbleSort {
	
	private static void bubSort(int[] arr ) {
		
		int temp = 0 ;
		
		boolean done = true ;
		
		for ( int outerLooper = 0 ; outerLooper < (arr.length) ; outerLooper++ ) {
			for ( int innerLooper = 0 ; innerLooper < ( arr.length - outerLooper - 1 ) ; innerLooper++ ) {
				if (arr[innerLooper] > arr[innerLooper + 1]) {
					temp = arr[innerLooper] ;
					arr[innerLooper] = arr[innerLooper + 1];
					arr[innerLooper + 1] = temp ;
					done = false ;
				}

			}
			if (done){
				break ;
			}
		}
		
		for ( int i = 0 ; i < arr.length ; i++ ) {			
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arr1 = { 10 , 20 , 15 , 25 , 5 };
		int[] arr2 = { 5 , 4 , 3 , 2 , 1 };
		int[] arr3 = { 10 , 20 , 30 , 40 , 50 };
		
		bubSort(arr1);
		bubSort(arr2);
		bubSort(arr3);

	}

}
