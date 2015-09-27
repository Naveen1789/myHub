package dataStructures.javaHashSet;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Set s = new HashSet<String>();
		Scanner sc=new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		String[] array = new String[n];
		for(int i = 0; i < n; i++){
			array[i] = sc.nextLine();
		}
		
		for(String str : array){
			s.add(str);
			System.out.println(s.size());
		}
	}

}
