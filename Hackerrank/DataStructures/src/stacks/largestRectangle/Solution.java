package stacks.largestRectangle;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		
		int n = Integer.parseInt(in.nextLine());
		int[] arr = new int[n];
		
		String[] strArr = in.nextLine().split(" ");
		for(int i = 0 ; i < n ; i++){
			arr[i] = Integer.parseInt(strArr[i]);
		}

		Stack<Integer> stk = new Stack<Integer>();
		int maxArea = 0;
		int width = 0;
		for(int i = 0; i < n ; i++){
			if( (stk.isEmpty()) || (arr[i] >= arr[stk.peek()]) ){			
				stk.push(i);
			}
			else{
				int temp = stk.pop();
				// int tempH = arr[temp];
				if(stk.isEmpty()){
					width = i;
				}
				else{
					width = i - stk.peek() - 1;
				}
				maxArea = Math.max(maxArea, width * arr[temp]);
				
				i--;
			}
		}
		
		int x = arr.length;
		
		while(!stk.isEmpty()){
			int anotherTemp = stk.pop();
			if(stk.isEmpty()){
				width = arr.length;
			}
			else{
				width = arr.length - stk.peek() - 1;
			}
			maxArea = Math.max(maxArea, width * arr[anotherTemp]);
		}
		
		System.out.println(maxArea);
	}

}