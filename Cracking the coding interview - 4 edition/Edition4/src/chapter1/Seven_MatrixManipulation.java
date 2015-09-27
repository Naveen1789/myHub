package chapter1;

import java.util.HashSet;
import java.util.Iterator;

public class Seven_MatrixManipulation {
	
	public static int[][] manipulateMat(int[][] mat){
		
		int row = mat.length;
		int col = mat[0].length;
		
		HashSet<Integer> rowHashSet = new HashSet<Integer>();
		HashSet<Integer> colHashSet = new HashSet<Integer>();
		for(int i = 0 ; i < row ; i++){
			for(int j = 0 ; j < col ; j++){
				if(mat[i][j] == 0){
					rowHashSet.add(i);
					colHashSet.add(j);
				}
			}
		}
		
		Iterator itr1 = rowHashSet.iterator();
	      while(itr1.hasNext()) {
	         int r = (int) itr1.next();
	         // Set entire row to zeros
	         for(int i = 0; i < col; i++){
	        	 mat[r][i] = 0;
	         }
	      }

			Iterator itr2 = colHashSet.iterator();
		      while(itr2.hasNext()) {
		         int c = (int) itr2.next();
		         // Set entire col to zeros
		         for(int i = 0; i < row; i++){
		        	 mat[i][c] = 0;
		         }
		      }		
		
				for(int i = 0 ; i < row ; i++){
					for(int j = 0 ; j < col ; j++){
						System.out.print(mat[i][j]);
					}
					System.out.println();
				}
		return mat ;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] mat1 = {{1,2,8},{3,4,9},{5,0,17},{0,7,22}};
		manipulateMat(mat1);
	}

}