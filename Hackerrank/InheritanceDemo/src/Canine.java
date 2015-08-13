
public class Canine extends Animal {
	
	String color;
	
	Canine( String clr){
		this.color = clr;
		System.out.println("Creating a " + clr + " colored Canine.");
	}
	
	public void run(){
		System.out.println("Running fast !!!");
	}
	
	public void roam(){
		System.out.println("Roaming !!!");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
