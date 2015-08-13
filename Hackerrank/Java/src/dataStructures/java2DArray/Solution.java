package dataStructures.java2DArray;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		
		int numOfRows = 2;
		int numOfCols = 2;
		int[][] twoDimArr = new int[numOfRows][numOfCols];
		
		int count = 0;
		while(in.hasNextLine()){
			String s = in.nextLine();
			System.out.println(s);
			String[] temp = s.split("#");
			if(temp.length != numOfCols){
				System.out.println(temp.length);
				for(String str : temp){
					System.out.println(str);
				}
				System.out.println("1");
				return;
			}
			for(int i = 0; i < numOfCols; i++){
				twoDimArr[count][i] = Integer.parseInt(temp[i]);
			}
			count++;
		}
		
		if(count != numOfRows){
			System.out.println("2");
			return;
		}
		
		System.out.println(twoDimArr);
	}

}