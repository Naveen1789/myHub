package bruteForce;

public class SelectionSort {
	
	private static void selSort(int[] arr ) {
		
		int temp = 0 ;
		
		int pos = 0 ;
		
		for ( int outerLooper = 0 ; outerLooper < (arr.length) ; outerLooper++ ) {
			pos = outerLooper ;
			for ( int innerLooper = outerLooper + 1 ; innerLooper < ( arr.length ) ; innerLooper++ ) {
				if (arr[innerLooper] < arr[pos]) {
					pos = innerLooper ;
				}

			}
			
			temp = arr[pos] ;
			arr[pos] = arr[outerLooper] ;
			arr[outerLooper] = temp ;
			

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
		
		selSort(arr1);
		selSort(arr2);
		selSort(arr3);

	}

}
