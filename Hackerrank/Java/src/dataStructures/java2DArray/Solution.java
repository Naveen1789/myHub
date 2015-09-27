package dataStructures.java2DArray;

import java.util.Scanner;



public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		
		int numOfRows = 6;
		int numOfCols = 6;
		int[][] twoDimArr = new int[numOfRows][numOfCols];
		
		int sizeOfHourGlass = 3;
		
		int i = 0;
		while(in.hasNextLine()){
			String[] strArr = in.nextLine().split(" ");
			for(int j = 0; j < numOfCols; j++){
				twoDimArr[i][j] = Integer.parseInt(strArr[j]);
			}
			i++;
		}
		if(sizeOfHourGlass%2 == 0){
			System.out.println("Size of hour glass can't be even.");
			return;
		}
		
		int maxSum = -9999;
		
		for(int r = 0; r <= (numOfRows-sizeOfHourGlass); r++){
			for(int c = 0; c <= (numOfCols-sizeOfHourGlass); c++){
				int sum = 0;
				// Top row
				for(int c1 = c; c1 < (c+sizeOfHourGlass); c1++){
					sum = sum + twoDimArr[r][c1];
				}
				// Bottom row
				for(int c2 = c; c2 < (c+sizeOfHourGlass); c2++){
					sum = sum + twoDimArr[r+sizeOfHourGlass-1][c2];
				}
				// Mid col
				for(int r1 = r+1; r1 < (r+sizeOfHourGlass-1); r1++){
					int x = c + (sizeOfHourGlass/2);
					sum = sum + twoDimArr[r1][x];
				}
				
				if(sum > maxSum){
					maxSum = sum;
				}
				
			}
		}
		
		System.out.println(maxSum);
		
	}

}
