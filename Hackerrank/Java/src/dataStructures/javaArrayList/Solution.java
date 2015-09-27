package dataStructures.javaArrayList;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc=new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		ArrayList<ArrayList<Integer>> arrOfArrList = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < n; i++){
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			String[] strArr = sc.nextLine().split(" ");
			for(int i1 = 1; i1 <= Integer.parseInt(strArr[0]); i1++){
				list.add((i1-1),Integer.parseInt(strArr[i1]));
			}
			
			arrOfArrList.add(list);
			
		}
		
		int numOfQueries = Integer.parseInt(sc.nextLine());
		for(int i = 0; i < numOfQueries; i++){
			String[] strArr = sc.nextLine().split(" ");
			int row = Integer.parseInt(strArr[0]);
			int col = Integer.parseInt(strArr[1]);
			
			if(row > arrOfArrList.size()){
				System.out.println("ERROR!");
			}
			
			else if(col > arrOfArrList.get(row-1).size()){
				System.out.println("ERROR!");
			}
			else{
				System.out.println(arrOfArrList.get(row-1).get(col-1));
			}
		}
	}

}
