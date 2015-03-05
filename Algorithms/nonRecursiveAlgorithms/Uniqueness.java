package nonRecursiveAlgorithms;

public class Uniqueness {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arr = { 17 , 22 , 5 , 25 , 9 , 12 , 32 } ;
		boolean duplicate = false ;
		
		for ( int outerLooper = 0 ; outerLooper < (arr.length - 1) ; outerLooper++ ) {
			for ( int innerLooper = ( outerLooper + 1 ) ; innerLooper < arr.length ; innerLooper++ ) {
				if (arr[outerLooper] == arr[innerLooper]) {
					System.out.println("Duplicates at " + (outerLooper + 1) + " & " + (innerLooper + 1));
					duplicate = true ;
					break ;
				}
			}
			if (duplicate) {
				break ;
			}
		}
		if (duplicate) {
			System.out.println("Array contains repetitions.");
		}
		else {
			System.out.println("Array is unique.");
		}

	}

}
