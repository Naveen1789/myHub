package divideAndConquer;

public class BinarySearch {

	public static void main(String[] args) {
		
		int[] arr = { 10 , 20 , 30 , 40 , 50 , 60 , 70 , 80 } ;
		int key = 90 ;
		boolean found = false ;
		int numOfComparisions = 0 ;
		int low = 0 ;
		int high = arr.length - 1 ;
		int mid = ( low + high ) / 2 ;
		
		while ( low <= high ) {
			numOfComparisions++ ;
			
			mid = ( low + high ) / 2 ;
			
			if ( key == arr[mid]) {
				found = true ;
				System.out.println("Key found at " + ( mid + 1 ));
				break ;
			}
			else if ( key < arr[mid] ) {
				high = mid - 1 ;
			}
			else {
				low = mid + 1 ;
			}
			
		}
		System.out.println("Number of comparisions : " + numOfComparisions );
		if ( !found ) {
			System.out.println("Key not found.");
		}
	}
}
