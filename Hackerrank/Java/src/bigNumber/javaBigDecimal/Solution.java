package bigNumber.javaBigDecimal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashMap<BigDecimal,String> hMap = new HashMap<BigDecimal,String>();
		ArrayList<BigDecimal> bigDecArrList = new ArrayList<BigDecimal>();
		
		// bigDecArrList.sort();
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		
		if(n > 0){
			for(int i = 0 ; i < n ; i++ ){
				String temp = in.next();
				
				bigDecArrList.add(new BigDecimal(temp));
				hMap.put(new BigDecimal(temp),temp);
				
			}
			
			Collections.sort(bigDecArrList);
			
			for(int i = (bigDecArrList.size() - 1 ) ; i >= 0 ; i--){
				System.out.println(hMap.get(bigDecArrList.get(i)));
			}
		}
	}

}
