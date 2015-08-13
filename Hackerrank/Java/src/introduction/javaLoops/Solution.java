package introduction.javaLoops;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);		
		int T = Integer.parseInt(in.nextLine());
		
		int a = 0, b = 0, n = 0;
		
		String temp = "";
		String[] tempArr = new String[3];
		
		int tempInt = 0;
		String msg = "";
		
		for(int i = 0 ; i < T ; i++){
						
			temp = in.nextLine();
			tempArr = temp.split(" ");
//			System.out.println(temp);
//			System.out.println(tempArr);
//			System.out.println(tempArr[0]);
//			System.out.println(tempArr[1]);
//			System.out.println(tempArr[2]);
			a = Integer.parseInt(tempArr[0]);
			b = Integer.parseInt(tempArr[1]);
			n = Integer.parseInt(tempArr[2]);
			
			tempInt = a + (b) ;
			msg = tempInt + " ";
			
			for(int j = 1 ; j < n ; j++){
				tempInt = tempInt + ( b * (int)(Math.pow(2, j)) );
				
				msg= msg + tempInt + " ";
			}
			System.out.println(msg.trim());
			
		}

	}

}
