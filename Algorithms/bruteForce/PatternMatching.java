package bruteForce;

public class PatternMatching {
	
	private static void patternSearch ( char[] text , char[] pattern ) {
		
		int j = 0 ;
		
		boolean found = false ;
		
		if ( text.length < pattern.length ) {
			System.out.println("Pattern not found.");
			return ;
		}
		for ( int i = 0 ; i < text.length ; i++ ) {
			 j = 0 ;
			while ( ( j < pattern.length ) && ( pattern[j] == text[i+j]) ) {
				j++ ;
			}
			
			if ( j == pattern.length ) {
				System.out.println("Pattern found , starting at " + ( i + 1 ) + "\n");
				found = true ;
				break ;
			}
		}
		if(!found) {
			System.out.println("Pattern not found.\n");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		char[] text = { 'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h' , 'i' };
		char[] pattern1 = {'a' , 'b' , 'c' };
		char[] pattern2 = {'c' , 'b' , 'a' };
		char[] pattern3 = {'d' , 'e' , 'f' , 'g' };
		char[] pattern4 = { 'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h' , 'i' };
		
		System.out.println("Searching for 'abc' : ");
		patternSearch(text , pattern1 );
		System.out.println("Searching for 'cba' : ");
		patternSearch(text , pattern2 );
		System.out.println("Searching for 'defg' : ");
		patternSearch(text , pattern3 );
		System.out.println("Searching for 'abcdefghi' : ");
		patternSearch(text , pattern4 );

	}

}