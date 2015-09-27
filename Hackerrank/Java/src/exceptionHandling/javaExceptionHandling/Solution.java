package exceptionHandling.javaExceptionHandling;

public class Solution {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class MyException extends Exception{
	
    public MyException () {

    }

    public MyException (String message) {
        super (message);
    }

    public MyException (Throwable cause) {
        super (cause);
    }

    public MyException (String message, Throwable cause) {
        super (message, cause);
    }
}

class myCalculator{
    public int power(int n, int p) throws Exception{
        if(n < 0 || p < 0){
            throw new Exception("n and p should be non-negative");
        }
        
        return (int) Math.pow(n, p);
    }
}


/*
class MyException extends Exception{
	
    public MyException () {

    }

    public MyException (String message) {
        super (message);
    }

    public MyException (Throwable cause) {
        super (cause);
    }

    public MyException (String message, Throwable cause) {
        super (message, cause);
    }
}

class myCalculator{
    public int power(int n, int p) throws MyException{
        if(n < 0 || p < 0){
            throw new MyException("n and p should be non-negative");
        }
        
        return (int) Math.pow(n, p);
    }
}
*/