package introduction.dataTypes;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// Read T - Number of integers that will be entered
		// Assumption : T can be held in an integer variable
		int T = 0;
		
		// Source : http://stackoverflow.com/questions/4644415/java-how-to-get-input-from-system-console
		Scanner in = new Scanner(System.in);
		// System.out.println("Enter the number of inputs.");
		try{
			T = in.nextInt();
		}
		catch (java.util.InputMismatchException e) {
			// System.out.println("Enter an integer.");
			}
				
		
		String temp = "";
		boolean sign = true;
		int numOfDigs = 0;
		String msg = "";
		long n = 0;
		for(int i = 0 ; i < T ; i++){
			msg = "";
			// System.out.println("Enter input # " + (i+1) + ".");
			temp = in.next();
			
			sign = ( temp.charAt(0) == '-' ) ? false : true;
			
			numOfDigs = (sign) ? temp.length() : (temp.length() - 1);
			
			if(numOfDigs > 19){
				System.out.println(temp + " can't be fitted anywhere.");
			}
			else if(numOfDigs == 19){
				if(sign){	// +ve number
					msg = ( temp.compareTo("9223372036854775807") >= 1 ) ? (temp + " can't be fitted anywhere.") : (temp + " can be fitted in:\n* long");
				}
				else{
					msg = ( (temp.substring(1)).compareTo("9223372036854775808") >= 1 ) ? (temp + " can't be fitted anywhere.") : (temp + " can be fitted in:\n* long");
				}
				System.out.println(msg);
			}
			else{
				n = Long.parseLong(temp,10);
				msg = ( ( n >= -2147483648 ) & ( n <= 2147483647 ) ) ? ("* int\n" + msg) : msg;
				msg = ( ( n >= -32768 ) & ( n <= 32767 ) ) ? ("* short\n" + msg) : msg;
				msg = ( ( n >= -128 ) & ( n <= 127 ) ) ? ("* byte\n" + msg) : msg;
				
				
				System.out.println(temp + " can be fitted in:\n" + msg + "* long");
			}
		}
		
		
    	
		
		
	}

}
