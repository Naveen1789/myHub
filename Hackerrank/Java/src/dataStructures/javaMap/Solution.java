package dataStructures.javaMap;

import java.util.HashMap;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		
		int numOfEntries = Integer.parseInt(in.nextLine());
		
		HashMap<String,String> phBook = new HashMap<String,String>();
		for(int i = 0; i < numOfEntries; i++){
			String name = in.nextLine();
			String phNum = in.nextLine();
			phBook.put(name, phNum);
			
		}
		
		while(in.hasNextLine()){
			String lookingFor = in.nextLine();
			if(phBook.containsKey(lookingFor)){
				System.out.println(lookingFor+"="+phBook.get(lookingFor));
			}
			else{
				System.out.println("Not found");
			}
		}
	}

}