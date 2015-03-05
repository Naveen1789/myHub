package decreaseAndConquer;

public class InsertionSort {

	private static void insSort ( int[] arr ) {
		int n = arr.length ;
		
		for ( int i = 1 ; i < n ; i++ ) {
			int item = arr[i];
			int j = i -1 ;
			
			while ( j >= 0 && item < arr[j] ) {
				arr[j+1] = arr[j] ;
				j = j - 1 ;
			}
			arr[j+1] = item ;
		}

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		int[] arr1 = { 10 , 7 , 22 , 17 , 8 , 25 } ;
		int[] arr2 = { 10 , 7 , 22 , 17 , 8 , 25 , 23 } ;
		
		System.out.println("Before sorting : ");
		System.out.println("arr1 : ");
		for ( int i = 0 ; i < arr1.length ; i++ ) {
			System.out.print(arr1[i] + " \t");
		}
		System.out.println("\narr2 : ");
		for ( int i = 0 ; i < arr2.length ; i++ ) {
			System.out.print(arr2[i] + " \t");
		}
		insSort(arr1);
		insSort(arr2);
		
		System.out.println("\nAfter sorting : ");
		System.out.println("arr1 : ");
		for ( int i = 0 ; i < arr1.length ; i++ ) {
			System.out.print(arr1[i] + " \t");
		}
		System.out.println("\narr2 : ");
		for ( int i = 0 ; i < arr2.length ; i++ ) {
			System.out.print(arr2[i] + " \t");
		}
	}

}
