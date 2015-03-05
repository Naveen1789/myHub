package divideAndConquer;

public class MergeSort {
	
	static int[] a = { 10 , 5 , 25 , 17 , 22 , 14 , 89 , 7 };
	static int[] b = { 10 , 5 , 25 , 17 , 22 };
	
	private static void simpleMerge ( int[] arr , int low , int mid , int high ) {
				
		int[] sortedArr = new int[arr.length] ;
		
		int i = low ;
		int j = mid + 1 ;
		int k = low ;
		
		while ( ( i <= mid ) && ( j <= high )) {
			
			if ( arr[i] < arr[j]) {
				sortedArr[k] = arr[i] ;
				k++ ;
				i++ ;
			}
			else {
				sortedArr[k] = arr[j] ;
				k++ ;
				j++ ;				
			}
			
		}
		while ( i <= mid ) {
			sortedArr[k] = arr[i] ;
			i++ ;
			k++ ;			
		}
		while ( j <= high ) {
			sortedArr[k] = arr[j] ;
			j++ ;
			k++ ;
		}
		
		for ( int temp = low ; temp <= high ; temp++ ) {
			arr[temp] = sortedArr[temp] ;
		}
		
	
	}
	
	

	private static void mergeSort (int[] arr , int low , int high ) {
		
		if ( low < high ) {
			int mid = ( low + high ) / 2 ;
			
			mergeSort( arr , low , mid );

			mergeSort( arr , ( mid + 1 ) , high );
			
			simpleMerge(arr , low , mid , high );
		}
		
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		// TODO Auto-generated method stub
		
		System.out.println("Unsorted Array\na { 10 , 5 , 25 , 17 , 22 , 14 , 89 , 7 } : " );
		System.out.println("\nSorted Array : ");
		mergeSort(a , 0 , (a.length - 1));
		for ( int i = 0 ; i < a.length ; i++ ) {
			System.out.print(a[i] + "\t");
		}
		System.out.println("\n\nUnsorted Array\nb { 10 , 5 , 25 , 17 , 22 } : " );
		System.out.println("\nSorted Array : ");
		mergeSort(b , 0 , (b.length - 1));
		for ( int i = 0 ; i < b.length ; i++ ) {
			System.out.print(b[i] + "\t");
		}
	}

}
