package divideAndConquer;

import java.util.Scanner;

public class BinaryTrees {
	
	private static int numOfNodes = 0 ;
	private static int numOfLeaves = 0 ;
	
	private static Tree binTree = new Tree();
		
	private static Tree createNode( int info ) {
		
		Tree t = new Tree();
		
		t.setInfo(info);
		
		return t ;
	}
	
	private static void addLeftSubTree ( Tree t ) {
		
		int leftSubNode = 0 ;	
		System.out.println("Enter the Info for left sub node of " + t.getInfo() + ".\n( '0' for NULL ) : ");
		
		Scanner in = new Scanner(System.in);
		boolean read = in.hasNextInt() ;				
		
		if ( read ) {
			leftSubNode = in.nextInt();
		}
		else {
			System.out.println("Invalid Input.\nValid Input - [ Integers only ].");
		}
		
		if ( leftSubNode == 0 ) {
			t.setLeftSubTree(null);
		}
		else {
			Tree leftSubTree = createNode(leftSubNode);
			addLeftSubTree(leftSubTree);
			addRightSubTree(leftSubTree);
			t.setLeftSubTree(leftSubTree);
		}		
		
	}
	
	private static void addRightSubTree ( Tree t ) {
		
		int rightSubNode = 0 ;	
		System.out.println("Enter the Info for right sub node of " + t.getInfo() + ".\n( '0' for NULL ) : ");
		
		Scanner in = new Scanner(System.in);
		boolean read = in.hasNextInt() ;				
		
		if ( read ) {
			rightSubNode = in.nextInt();
		}
		else {
			System.out.println("Invalid Input.\nValid Input - [ Integers only ].");
		}
		
		if ( rightSubNode == 0 ) {
			t.setRightSubTree(null);
		}
		else {
			Tree rightSubTree = createNode(rightSubNode);
			addLeftSubTree(rightSubTree);
			addRightSubTree(rightSubTree);
			t.setRightSubTree(rightSubTree);
		}
		
	}
	
	public static void main ( String args[] ) {
		
		boolean done = false ;
		int root = 0 ;
		
		do {
			
		System.out.println("Enter the Info for root node : ");
		
		Scanner in = new Scanner(System.in);
		boolean read = in.hasNextInt() ;				
		
		if ( read ) {
			root = in.nextInt();
			
			if ( root == 0 ) {
				System.out.println("Root cannot be '0'");
				done = false ;
			}
			else {
				done = true ;	
			}
			
		}
		else {
			System.out.println("Invalid Input.\nValid Input - [ Integers only ].");
		}
		} while ( ! done ) ;
		
		binTree.setInfo(root);
		addLeftSubTree(binTree);
		addRightSubTree(binTree);
		
		System.out.println("Reading the binary tree in Pre-Order : ");
		preOrder(binTree);
		
		System.out.println("\nReading the binary tree in Pre-Order : ");
		inOrder(binTree);
		
		System.out.println("\nReading the binary tree in Pre-Order : ");
		postOrder(binTree);
		
		System.out.println("\nHeight of the binary tree : " + height(binTree) );
		
		numOfNodes(binTree);
		System.out.println("\nNumber of nodes in the binary tree : " + numOfNodes );
		
		numOfLeaves(binTree);
		System.out.println("\nNumber of leaves in the binary tree : " + numOfLeaves );
		
		
	}
	
	public static int height ( Tree t ) {
		if ( t == null ) {
			return 0 ;
		}
		else {
			return ( 1 + 
					( ( ( height(t.getLeftSubTree()) ) > ( height(t.getRightSubTree()) ) ) 
							? ( height(t.getLeftSubTree()) ) 
									: ( height(t.getRightSubTree()) ) ) ) ;
		}
	}
	
	public static void numOfNodes ( Tree t ) {
		
		if ( t == null ) {
			return ;
		}
		else {
			numOfNodes = numOfNodes + 1 ;
			numOfNodes(t.getLeftSubTree());
			numOfNodes(t.getRightSubTree());
		}
		
	}
	
	public static void numOfLeaves ( Tree t ) {
		
		if ( t == null ) {
			return ;
		}
		else {
			if ( ( t.getLeftSubTree() == null ) && ( t.getRightSubTree() == null ) ) {
				numOfLeaves = numOfLeaves + 1 ;				
			}
			numOfLeaves(t.getLeftSubTree());
			numOfLeaves(t.getRightSubTree());
		}
		
	}
	public static void preOrder ( Tree t ) {
		if ( t == null ) {
			return ;
		}
		
		System.out.print(t.getInfo() + "\t");
		preOrder(t.getLeftSubTree());
		preOrder(t.getRightSubTree());
	}
	
	public static void inOrder ( Tree t ) {
		if ( t == null ) {
			return ;
		}
		
		inOrder(t.getLeftSubTree());
		System.out.print(t.getInfo() + "\t");
		inOrder(t.getRightSubTree());
	}
	
	public static void postOrder ( Tree t ) {
		if ( t == null ) {
			return ;
		}
		
		postOrder(t.getLeftSubTree());
		postOrder(t.getRightSubTree());
		System.out.print(t.getInfo() + "\t");
	}

}

// Number of nodes 

//==================================================================//

class Tree {
	
	private Tree leftSubTree ;
	private Tree rightSubTree ;
	private int info ;
	
	public void setLeftSubTree ( Tree leftSubTree ) {
		this.leftSubTree = leftSubTree ;		
	}
	
	public void setRightSubTree ( Tree rightSubTree ) {
		this.rightSubTree = rightSubTree ;		
	}
	
	public void setInfo ( int info ) {
		this.info = info ;
	}
	
	public Tree getLeftSubTree() {
		return this.leftSubTree ;
	}
	
	public Tree getRightSubTree() {
		return this.rightSubTree ;
	}
	
	public int getInfo() {
		return this.info ;
	}
	
}