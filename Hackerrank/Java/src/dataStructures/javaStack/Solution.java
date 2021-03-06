package dataStructures.javaStack;

import java.util.Scanner;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		String exp = "";
		boolean valid = true;
		
		Stack<Character> stk = new Stack<Character>();
		while(in.hasNext()){
			stk.clear();
			valid = true;
			exp = in.next();
			for(int i = 0; i < exp.length(); i++){
				char c = exp.charAt(i);
				if(c == '(' || c == '{' || c == '['){
					stk.push(c);
				}
				else{
					if(stk.empty()){
						valid = false;
					}
					else if(((char)stk.peek() == '(') && (c == ')')){
						stk.pop();
					}
					else if(((char)stk.peek() == '{') && (c == '}')){
						stk.pop();
					}
					else if(((char)stk.peek() == '[') && (c == ']')){
						stk.pop();
					}
				}
			}
			
			if(valid && stk.empty()){
				System.out.println("true");
			}
			else{
				System.out.println("false");
			}
			
		}

	}

}
