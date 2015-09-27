package stacks.poisonousPlants;

import java.util.Scanner;
import java.util.Stack;

public class Solution {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		
		int n = Integer.parseInt(in.nextLine());
		int[] numOfDaysThatThePlantLives = new int[n];
		
		String[] strArr = in.nextLine().split(" ");
		
		Stack<Integer> stk1 = new Stack<Integer>();
		
		for(int i = 0 ; i < n ; i++){
			
			if(stk1.isEmpty()){
				stk1.push(Integer.parseInt(strArr[i]));
				numOfDaysThatThePlantLives[i] = 999;
			}
			// arr[i] = ;
			stk1.push(Integer.parseInt(strArr[i]));
		}
	}
	
	/*

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		
		int n = Integer.parseInt(in.nextLine());
		// int[] arr = new int[n];
		
		String[] strArr = in.nextLine().split(" ");
		
		Stack<Integer> stk1 = new Stack<Integer>();
		
		for(int i = 0 ; i < n ; i++){
			// arr[i] = ;
			stk1.push(Integer.parseInt(strArr[i]));
		}
		
		Stack<Integer> stk2 = new Stack<Integer>();
		
		boolean done = true;
		int count = 0;
		
		do{
		
			done = true;
			while(! stk1.isEmpty()){
				
				int temp = stk1.pop();
				if(stk1.isEmpty() ||  temp <= stk1.peek()  ){
					stk2.push(temp);
				}
				else{
					done = false;
				}
			}
			
			while(! stk2.isEmpty()){
				stk1.push(stk2.pop());
			}
			
			if(! done){
				count++;
			}
			
			
		}while(!done);
		
		System.out.println(count);

	}

	*/
}
