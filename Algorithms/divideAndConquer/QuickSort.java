package divideAndConquer;

public class QuickSort {
	
	static int loopCounter = 0 ;
	
	private static void quickSort ( int[] arr , int low , int high ) {

		if ( low < high ) {
			int k = partition ( arr , low , high );
			quickSort(arr , low , k-1 ) ;
			quickSort(arr , k+1 , high ) ;
		}
		
		else {
			return ;
		}
		
		

	}
	
	private static int partition ( int[] arr , int low , int high ) {
		
		int i = low ;
		int j = high + 1 ;
		int pivot = arr[low];
		
		while ( i <= j ) {
			do {
				i = i + 1 ;
			} while ( ( i <= high ) && ( pivot >= arr[i] ) );
			
			do {
				j = j - 1 ;
			} while ( ( j >= low ) && ( pivot < arr[j] ) );
			
			if ( i < j ) {
				int temp = arr[i] ;
				arr[i] = arr[j];
				arr[j] = temp ;
			}
			
		}
		
		
		int anotherTemp = arr[j] ;
		arr[j] = arr[low];
		arr[low] = anotherTemp ;
		
		return j ;
	}
	
	public static void main(String[] args) {
		
		int[] a = { 10 , 5 , 25 , 17 , 22 , 14 , 89 , 7 , 90 , 2 };
		
		
		System.out.println("Unsorted Array\na { 10 , 5 , 25 , 17  , 22 , 14 , 89 , 7 , 90 , 2 } : " );
		
		quickSort(a , 0 , ( a.length - 1 ));
		
		for ( int i = 0 ; i < a.length ; i++ ) {
			System.out.print(a[i] + "\t");
		}
		
		
	}

}
