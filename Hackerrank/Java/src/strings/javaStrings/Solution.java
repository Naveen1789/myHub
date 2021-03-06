package strings.javaStrings;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		try{		
			Scanner in = new Scanner(System.in);
			
			String msg = in.nextLine();
			// msg.trim();
			// msg.replaceAll("\\s", "");
			
			int subStrLen = in.nextInt();		
			String lexicographicallySmallest = "{{{";
			String lexicographicallyLargest = "@@@";
			
			if((subStrLen <= 0) || (msg.length() <= 0)){
				lexicographicallySmallest = "";
				lexicographicallyLargest = "";
				System.out.println(lexicographicallySmallest + "\n" + lexicographicallyLargest);
			}
			else if(subStrLen >= msg.length()){
				lexicographicallySmallest = msg;
				lexicographicallyLargest = msg;
				System.out.println(lexicographicallySmallest + "\n" + lexicographicallyLargest);
			}
			else{
				for(int i = 0 ; i <= ( msg.length() - subStrLen ) ; i++){
	//				System.out.println("i = " + i);
	//				System.out.println(msg.substring(i, (i+3)));
					lexicographicallySmallest = ( (msg.substring(i, (i+subStrLen))).compareTo(lexicographicallySmallest) < 0 ) ? msg.substring(i, (i+subStrLen)) : lexicographicallySmallest;
					lexicographicallyLargest = ( (msg.substring(i, (i+subStrLen))).compareTo(lexicographicallyLargest) > 0 ) ? msg.substring(i, (i+subStrLen)) : lexicographicallyLargest;
				}
				System.out.println(lexicographicallySmallest + "\n" + lexicographicallyLargest);
			}
		}
		catch(Exception e){
			System.out.println("" + "\n" + "");
		}
	}

}
