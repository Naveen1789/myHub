package otherSimpleAlgorithms;

public class LinkedLists {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Node n1 = new Node(10);
		Node n2 = new Node(20);
		Node n3 = new Node(30);
		Node n4 = new Node(40);
		Node n5 = new Node(50);
		
		LinkedList l1 = new LinkedList();
		l1.setHead(n1);
		
		l1.display();
		System.out.println("Number of nodes : " + l1.getSize());
		
		l1.addNode(n2);
		l1.addNode(n3);
		l1.addNode(n4);
		l1.addNode(n5);
		
		l1.display();
		System.out.println("Number of nodes : " + l1.getSize());
		
//		l1.addNode(n3);
//		
//		l1.display();
//		System.out.println("Number of nodes : " + l1.getSize());

	}

}

//==================================================================================== //

class LinkedList {
	
	Node head ;
	Node lastNode ;
	int size ;
	
	LinkedList () {
		this.head = null ;
		this.lastNode = null ;
		this.size = 0 ;
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
		this.lastNode = head ;
		this.size = 1 ;
	}

	public Node getLastNode() {
		return lastNode;
	}

//	public void setLastNode(Node lastNode) {
//		this.lastNode = lastNode;
//	}
	
	public int getSize() {
		return size;
	}

//	public void setSize(int size) {
//		this.size = size;
//	}

	public void addNode ( Node n ) {
		
		if ( lastNode.getNextNode() == null ) {
		
			// Adding a stand alone Node.
		if ( n.getNextNode() == null ) {	
		lastNode.setNextNode(n) ;
		size++ ;
		
		lastNode = n ;
		}
		
		else {
			
			// Under Construction
			
//			int count = 0 ;
//			
//			while ( )
			
		}
		
		}
		
		else {
			System.out.println("[ This linked list has a loop. ]");
			System.out.println("Sorry.\nYou cannot add any more nodes.");
		}
	}
	
	public void display () {
		
		displayFunc ( head ) ;
		
	}
	
	private void displayFunc ( Node n ) {
		
		if ( n.getNextNode() != null ) {
			System.out.print(n.getInfo() + "\t");
			displayFunc(n.getNextNode());
		}
		else {
			System.out.println(n.getInfo());
			return ;
		}
//		do {
//		System.out.println(n.getInfo());
//		displayFunc(n.getNextNode());
//		}while ( n.getNextNode() != null );
		
	}
		
}
// ==================================================================================== //

class Node {
	
	int info ;
	Node nextNode ;
	
	Node ( int info , Node next ) {
		this.info = info ;
		this.nextNode = next ;
	}
	
	Node ( int info ) {
		this.info = info ;
		this.nextNode = null ;
	}
	
	Node () {
		this.info = 0 ;
		this.nextNode = null ;
	}
	
	public int getInfo() {
		return info;
	}
	public void setInfo(int info) {
		this.info = info;
	}
	public Node getNextNode() {
		return nextNode;
	}
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	
}

//==================================================================================== //

/*
If the linked list has a loop :

	1. The last node points to any of the previous nodes ( or to itself ).
*/	