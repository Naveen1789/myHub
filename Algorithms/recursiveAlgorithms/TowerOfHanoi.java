package recursiveAlgorithms;

public class TowerOfHanoi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		move( 3 , 'S' , 'D' , 'T' ) ;

	}
	
	private static void move ( int numOfDisks , char source , char destination , char temp ) {
		
		if ( numOfDisks ==1 ) {
			System.out.println("Move disk 1 from " + source + " to " + destination + "." );			
		}
		else {
			
			move( ( numOfDisks - 1 ) , source , temp , destination ) ;
			System.out.println("Move disk " + numOfDisks + " from " + source + " to " + destination + "." );
			move( ( numOfDisks - 1 ) , temp , destination , source ) ;
		}
	}

}
