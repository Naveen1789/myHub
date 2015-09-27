package introduction.javaStdinAndStdout2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int count = 0;
		int i = 0;
		double d = 0;
		String s = "";
		while(in.hasNextLine()){
			switch(count){
			case 0: i = Integer.parseInt(in.nextLine());
			break;
			case 1: d = Double.parseDouble(in.nextLine());
			break;
			case 2: s = in.nextLine();
			break;
			default:
			}
			count++;
		}
		
		System.out.println("String: " + s);
		System.out.println("Double: " + d);
		System.out.println("Int: " + i);
	}

}
