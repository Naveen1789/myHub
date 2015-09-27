package chapter1;

public class Six_MatrixRotation {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] mat1 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
		rotate(mat1);
	}
	
	public static void rotate(int[][] mat) {

		int n = mat.length;
		if(n != mat[0].length){
			System.out.println("Input is not a square matrix.");
			return;
		}
		
		int[][] rotatedMat = new int[n][n];
		
		for(int r = 0; r < n; r++){
			for(int c = 0; c < n; c++){
				rotatedMat[c][((n-1) - r)] = mat[r][c];
			}
		}
		
		for(int r = 0; r < n; r++){
			for(int c = 0; c < n; c++){
				System.out.print(rotatedMat[r][c] + "\t");
			}
			System.out.println();
		}
	}
	


}
