package chapter1;

public class Eight_RotatedString {
	
	public static boolean rotatedString(String input, String rotStr){
		
		if(input.length() != rotStr.length()){
			System.out.println("Inputs are not of same same length.");
			System.out.println("false");
			return false;
		}
		String str = input + input;
		
		if(str.contains(rotStr)){
			System.out.println("true");
			return true;
		}
		else{
			System.out.println("false");
			return false;
		}
				
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		rotatedString("waterbottle","erbottlewat");
	}

}