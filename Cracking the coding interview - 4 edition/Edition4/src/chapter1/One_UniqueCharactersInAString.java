package chapter1;

import java.util.HashSet;

public class One_UniqueCharactersInAString {
	
	
	// Time Complexity - n^2
	public static boolean doesThisStringHaveUniqueCharacters(String input){
		
		if(input == null){
			System.out.println("Input is null.");
			return false;
		}
		else if(input.length() == 0){
			System.out.println("Input is an empty String.");
			return false;
		}
		
		int strLen = input.length();
		for(int i = 0; i < strLen; i++){
			for(int j = (i+1); j < strLen; j++){
				if(input.charAt(i)==input.charAt(j)){
					return false;
				}
			}
		}
		return true;
	}

	// Time Complexity - n [ if time complexity for looking up the HashSet is 1 ]
	public static boolean doesThisStringHaveUniqueCharactersWithDS(String input){
		
		HashSet<Character> hSet = new HashSet<Character>();
		if(input == null){
			System.out.println("Input is null.");
			return false;
		}
		else if(input.length() == 0){
			System.out.println("Input is an empty String.");
			return false;
		}
		
		int strLen = input.length();
		for(int i = 0; i < strLen; i++){
			if(hSet.contains(input.charAt(i))){
				return false;
			}
			else{
				hSet.add(input.charAt(i));
			}
		}
		
		return true;
	}
	
	// Time Complexity - n
	public static boolean doesThisStringHaveUniqueCharactersWithBoolVec(String input){
		
		int ascii = 127;
		int extendedAscii = 255;
		
		int range = ascii;
		
		if(input == null){
			System.out.println("Input is null.");
			return false;
		}
		else if(input.length() == 0){
			System.out.println("Input is an empty String.");
			return false;
		}
		
		boolean[] boolArr = new boolean[range];
		int strLen = input.length();
		for(int i = 0; i < strLen; i++){
			int c = input.charAt(i);
			if(c > range){
				System.out.println("Character is out of checking range.");
				return false;
			}
			if(boolArr[c]){
				return false;
			}
			else{
				boolArr[input.charAt(i)] = true;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Test doesThisStringHaveUniqueCharacters("str")
//		System.out.println(doesThisStringHaveUniqueCharacters("aaa"));
//		System.out.println(doesThisStringHaveUniqueCharacters("abc"));
//		System.out.println(doesThisStringHaveUniqueCharacters("abca"));
//		System.out.println(doesThisStringHaveUniqueCharacters(null));
//		System.out.println(doesThisStringHaveUniqueCharacters(""));
		
//		System.out.println(doesThisStringHaveUniqueCharactersWithDS("aaa"));
//		System.out.println(doesThisStringHaveUniqueCharactersWithDS("abc"));
//		System.out.println(doesThisStringHaveUniqueCharactersWithDS("abca"));
//		System.out.println(doesThisStringHaveUniqueCharactersWithDS(null));
//		System.out.println(doesThisStringHaveUniqueCharactersWithDS(""));
		
//		System.out.println(doesThisStringHaveUniqueCharactersWithBoolVec("aaa"));
//		System.out.println(doesThisStringHaveUniqueCharactersWithBoolVec("abc"));
//		System.out.println(doesThisStringHaveUniqueCharactersWithBoolVec("abca"));
//		System.out.println(doesThisStringHaveUniqueCharactersWithBoolVec(null));
//		System.out.println(doesThisStringHaveUniqueCharactersWithBoolVec(""));
		

	}

}
