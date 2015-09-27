package introduction.javaStdinAndStdout1;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		while(in.hasNextInt()){
			int a=in.nextInt();
			System.out.println(a);
		}
	}
}
