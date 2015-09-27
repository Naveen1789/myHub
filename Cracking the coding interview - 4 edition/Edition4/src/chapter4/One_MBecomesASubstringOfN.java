package chapter4;

public class One_MBecomesASubstringOfN {
	
	public static void makeMSubstringOfN(int n, int m, int i, int j){
		if(i < 0 || j < 0 || i > 31 || j > 31 || (i > j)){
			System.out.println("Invalid input.");
			return;
		}
		System.out.println("m = " + Integer.toBinaryString(m));
		System.out.println("n = " + Integer.toBinaryString(n));
		
		
		int max = ~0;
		// System.out.println("max = " + Integer.toBinaryString(max));
		int temp = ( max - ( (1 << (j+1)) - 1 ) ) | ((1 << i) - 1 );
		int tempM = (~temp) & m; 
		int tempN = temp & n; 
		int mSubstrOfN = tempM | tempN;
//		System.out.println("1 << (j+1) = " + Integer.toBinaryString(1 << (j+1)));
//		System.out.println("(1 << (j+1)) - 1 = " + Integer.toBinaryString((1 << (j+1)) - 1));
//		System.out.println("max - ( (1 << (j+1)) - 1 ) = " + Integer.toBinaryString(max - ( (1 << (j+1)) - 1 )));
//		
//		System.out.println("1 << i = " + Integer.toBinaryString(1 << i));
//		System.out.println("(1 << i) - 1 = " + Integer.toBinaryString((1 << i) - 1));
		System.out.println("temp = " + Integer.toBinaryString((temp)));
		System.out.println("~temp = " + Integer.toBinaryString((~temp)));
		System.out.println("tempM = " + Integer.toBinaryString((tempM)));
		System.out.println("tempN = " + Integer.toBinaryString((tempN)));
		
		System.out.println("mSubstrOfN = " + Integer.toBinaryString((mSubstrOfN)));
		
	}
	
	

	public static void main(String[] args) {
		
		
		
		
		// TODO Auto-generated method stub
		// N = 10000000000, M = 10101, i = 2, j = 6
		// Output = 10001010100	// Assuming index starts at 1
		// 			10000010100 // Assuming index starts at 0
		makeMSubstringOfN(0b10000000000, 0b10101,2,6 );

	}

}
