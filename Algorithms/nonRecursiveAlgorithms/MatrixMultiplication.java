package nonRecursiveAlgorithms;

public class MatrixMultiplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] matrix1 = { { 1 , 2 , 3 , 4 } ,
							{ 2 , 3 , 4 , 1 } ,
							{ 3 , 4 , 1 , 2 }
						  } ;
		
		int[][] matrix2 = { { 1 , 2 , 3 } ,
							{ 2 , 3 , 1 } ,
							{ 3 , 1 , 2 } ,
							{ 1 , 2 , 3 }
						  } ;
		
		int[][] matrix3 = new int[matrix1.length][matrix2[0].length];
		
		int elem = 0 ;
		
		int outerLooper = 0 ;
		
		int innerLooper = 0 ;
		
		int looper = 0 ;
		
		for ( outerLooper = 0 ; outerLooper < (matrix1.length) ; outerLooper++ ) {
			for ( innerLooper = 0 ; innerLooper < (matrix2[0].length) ; innerLooper++ ) {
				elem = 0 ;
				for ( looper = 0 ; looper < matrix1[0].length ; looper++ ) {					
					elem  = elem + ( matrix1[outerLooper][looper] * matrix2[looper][innerLooper] ) ;
				}
				
				matrix3[outerLooper][innerLooper] = elem ;	
				
			}
								
		}
		
		for ( int i = 0 ; i < matrix3.length ; i++ ) {
			for ( int j = 0 ; j < matrix3[i].length ; j++ ) {
				System.out.print(matrix3[i][j] + "\t\t");
			}
			System.out.println();
		}
		
		
	}

}
