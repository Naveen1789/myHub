package strings.javaStringToken;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
	
	public static void main(String args[]){

		Scanner in = new Scanner(System.in);
		
		String input = in.nextLine();
		input = input.trim();
		// String input = "He is a very very good boy, isn't he?";

		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(input);
		
		int count = 0;
		String msg = "";
		StringBuffer msgBuffer = new StringBuffer();
		
		while(m.find()){
			count++;
			// System.out.println("->" + m.group());
			// msg = msg + m.group() + "\n" ;
			msgBuffer = msgBuffer.append((m.group() + "\n"));
			
		}
		
		msg = count + "\n" + msgBuffer.toString();
		msg = msg.trim();
		
		System.out.println(msg);
	}
}

// Moral : Using StringBuffer is very efficient than using String.
