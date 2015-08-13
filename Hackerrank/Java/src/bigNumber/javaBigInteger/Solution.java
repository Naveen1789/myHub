package bigNumber.javaBigInteger;

import java.math.BigInteger;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		
		String a = in.next();
		BigInteger bigA = new BigInteger(a);
		String b = in.next();
		BigInteger bigB = new BigInteger(b);
		
		System.out.println((bigA.add(bigB)) + "\n" + (bigA.multiply(bigB)));

	}

}

/*

java.math
Class BigInteger


    public class BigInteger
    extends Number
    implements Comparable<BigInteger>

    Immutable arbitrary-precision integers. All operations behave as if BigIntegers were represented in two's-complement notation (like Java's primitive integer types). 
    BigInteger provides analogues to all of Java's primitive integer operators, and all relevant methods from java.lang.Math. 
    Additionally, BigInteger provides operations for modular arithmetic, GCD calculation, primality testing, prime generation, bit manipulation, and a few other 
    miscellaneous operations.  
*/