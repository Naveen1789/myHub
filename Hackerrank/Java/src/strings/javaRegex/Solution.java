package strings.javaRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

class Solution{

    public static void main(String []argh)
    {
        Scanner in = new Scanner(System.in);
        while(in.hasNext())
        {
            String IP = in.next();
            System.out.println(IP.matches(new myRegex().pattern));
        }

    }
}

class myRegex{
	String pattern = "(([2](([0-4][0-9])|([5][0-6]))\\.)|([1][0-9][0-9]\\.)|([0]?[0-9]?[0-9]\\.))(([2](([0-4][0-9])|([5][0-6]))\\.)|([1][0-9][0-9]\\.)|([0]?[0-9]?[0-9]\\.))(([2](([0-4][0-9])|([5][0-6]))\\.)|([1][0-9][0-9]\\.)|([0]?[0-9]?[0-9]\\.))(([2](([0-4][0-9])|([5][0-6])))|([1][0-9][0-9])|([0]?[0-9]?[0-9]))";
}



