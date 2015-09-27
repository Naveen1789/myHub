package introduction.javaOutputFormatting;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            System.out.println("================================");
            for(int i=0;i<3;i++)
            {
                String s1=sc.next();
                int x=sc.nextInt();
                //Complete this line
                
                for(int i1 = s1.length()+1; i1 <= 15; i1++ ){
                    s1 = s1 + " ";
                }
                if(x < 10){
                    s1 = s1 + "00";
                }
                else if(x < 100){
                    s1 = s1 + "0";
                }
                else{
                    s1 = s1 + "";
                }
                System.out.println(s1 + x);
            }
            System.out.println("================================");

    }
}
