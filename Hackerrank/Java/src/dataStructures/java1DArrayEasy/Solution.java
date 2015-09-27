package dataStructures.java1DArrayEasy;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 0;
		Scanner sc=new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		
		String[] input = sc.nextLine().split(" ");

		int[] array = new int[n];
		
		for(int i = 0; i < n; i++){
			array[i] = Integer.parseInt(input[i]);
		}
		
		for(int i = 0; i < n; i++ ){
			int sum = 0;
			for(int j = i; j < n; j++){
				sum = sum + array[j];
				if(sum < 0){
					count++;
				}
			}
		}
		
		System.out.println(count);
	}

}
