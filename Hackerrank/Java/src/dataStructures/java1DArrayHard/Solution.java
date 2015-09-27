package dataStructures.java1DArrayHard;

import java.util.Scanner;

public class Solution {
	
	public static boolean pathFound = false;
	
	public static boolean moveFwd(int[] arr, int curPos){
		
		 // System.out.println("moveFwd : " + curPos);
		
//		if(curPos+1 >= (arr.length)){
//			return true;
//		}
		
		if(arr[curPos+1] == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean moveFwdByM(int[] arr, int curPos,int m){
		
		// System.out.println("moveFwdByM : " + curPos);
		
//		if((curPos+m >= (arr.length))){
//			return true;
//		}
		
		if(m <= 0){
			return false;
		}
		if(arr[curPos+m] == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean moveBckward(int[] arr, int curPos){
		// System.out.println("moveBckward : " + curPos);
		if(curPos <= 0){
			return false;
		}
//		if(curPos >= arr.length){
//			return false;
//		}
		if(arr[curPos-1] == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean findPath(int[] arr, int curPos, int m){
		
		if(curPos == (arr.length - 1) || ((curPos + m) > (arr.length - 1)) ){
			pathFound = true;
			return pathFound;
		}
		if(moveFwd(arr,curPos) && (!pathFound)){
			findPath(arr,curPos+1,m);
		}
		if(moveFwdByM(arr,curPos,m) && (!pathFound)){
			findPath(arr,curPos+m,m);
		}
		arr[curPos] = 1;
		if(moveBckward(arr,curPos) && (!pathFound)){
			findPath(arr,curPos-1,m);
		}
		
		return pathFound;
		
	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		String msg = "";
		
		Scanner sc=new Scanner(System.in);
		
		int numOfTestCases = Integer.parseInt(sc.nextLine());
		
		for(int i = 0; i < numOfTestCases; i++){
			String[] strArr = sc.nextLine().split(" ");
			int n = Integer.parseInt(strArr[0]);
			int m = Integer.parseInt(strArr[1]);
			
			String[] strArr1 = sc.nextLine().split(" ");
			int[] numArr = new int[strArr1.length];
			for(int i1 = 0; i1 < strArr1.length; i1++){
				numArr[i1] = Integer.parseInt(strArr1[i1]);
			}
			
			pathFound = false;

			if(findPath(numArr,0,m)){
				msg = msg + "YES\n";
			}
			else{
				msg = msg + "NO\n";
			}
		}
		
		System.out.println(msg.trim());
	}
	
	/*
	 * 
	 */

}
