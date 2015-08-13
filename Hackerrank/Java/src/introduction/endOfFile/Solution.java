package introduction.endOfFile;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		
		int i = 1;
		
		while(in.hasNext()){
			System.out.println(i + " " + in.nextLine());
			i++;
		}
	}

}
