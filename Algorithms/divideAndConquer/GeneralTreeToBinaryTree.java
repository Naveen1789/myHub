package divideAndConquer;

import java.util.ArrayList;
import java.util.Scanner;

public class GeneralTreeToBinaryTree {
	
	private static GeneralTree genTree = new GeneralTree();
	
	private static BinaryTree binTree = new BinaryTree();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		createGenTree();
		
		genTreeToBinTree( genTree , binTree );
		
		binTree.display();
		
	}
	
	public static BinaryTree genTreeToBinTree( GeneralTree gen , BinaryTree bin ) {

		bin.setInfo(gen.getRoot());
		
		if ( gen.subTrees.size() == 0 ) {
			bin.setLeftSubTree(null);
		}
		else {
			bin.setLeftSubTree(getLeftSubTreeForBinTree( gen.subTrees ));
		}
		
			bin.setRightSubTree(null);
		
		return bin ;
	}
	
	public static BinaryTree getLeftSubTreeForBinTree ( ArrayList<GeneralTree> subs  ) {
				
		BinaryTree bin = new BinaryTree();
		
		if ( subs == null || subs.size() == 0 ) {
			bin.setLeftSubTree(null);
		}
		else {
			GeneralTree firstSubTreeOfGenTree = subs.get(0);
			bin.setInfo(firstSubTreeOfGenTree.getRoot());
			
			if( ( firstSubTreeOfGenTree.subTrees != null ) && ( firstSubTreeOfGenTree.subTrees.size() != 0) ) {
				
			bin.setLeftSubTree( getLeftSubTreeForBinTree(firstSubTreeOfGenTree.subTrees) );	
			}
			else {
				bin.setLeftSubTree( null );	
			}
			
			if ( subs.size() == 1 ) {
				bin.setRightSubTree(null);
			}
			else {
				ArrayList<GeneralTree> subTrsTemp = new ArrayList<GeneralTree>(subs);
				subTrsTemp.remove(0);				
				bin.setRightSubTree(getRightSubTreeForBinTree(subTrsTemp));
			}			
		}				
		return bin ;
		
	}
	
	public static BinaryTree getRightSubTreeForBinTree ( ArrayList<GeneralTree> SubTrs ) {
		
		BinaryTree bin = new BinaryTree();
		bin.setInfo(SubTrs.get(0).getRoot());
		
		if ( SubTrs.get(0).subTrees.size() == 0 ) {
			bin.setLeftSubTree(null);
		}
		else {
			bin.setLeftSubTree(getLeftSubTreeForBinTree(SubTrs.get(0).subTrees));
		}
		
		if ( SubTrs.size() == 1 ) {
			bin.setRightSubTree(null);
		}
		else {
		ArrayList<GeneralTree> subTrsTemp = new ArrayList<GeneralTree>(SubTrs);
		subTrsTemp.remove(0);
		bin.setRightSubTree(getRightSubTreeForBinTree(subTrsTemp));
		}
		return bin ;
		
	}
	
	
//	public BinaryTree gen2Bin ( GeneralTree gen , BinaryTree bin ) {
//		
//		bin.setInfo(gen.getRoot());
//		
//		if ( gen.subTrees.size() == 0 ) {
//			bin.setLeftSubTree(null);
//			return null ;
//		}
//		else {
//			bin.getLeftSubTree().setInfo(gen.subTrees.get(0).getRoot());
//			
//			
//		}
//		
//		
//		
//		
//		for ( int i = 1 ; i < gen.subTrees.size() ; i++ ) {
//			BinaryTree b = new BinaryTree();
//			bin.getRightSubTree().setInfo(gen.subTrees.get(i).getRoot());
//		}
//		
//	}
	

	

	
	// Make first child node as leftSubNode
	// 
	public static void createGenTree( ) {
		
		boolean done = false ;
		int root = 0 ;
		
		do {
			
		System.out.println("Enter the Info for root node : ");
		
		Scanner in = new Scanner(System.in);
		boolean read = in.hasNextInt() ;				
		
		if ( read ) {
			root = in.nextInt();
			
			if ( root == 0 ) {
				return ;
			}
			else {
				genTree.setRoot(root);
				done = true ;	
			}
			
		}
		else {
			System.out.println("Invalid Input.\nValid Input - [ Integers only ].");
		}
		} while ( ! done ) ;
		
		genTree.addSubTree();
		
		genTree.display();
		
	}

}

//==================================================================//

class GeneralTree {
	
	private int root ;
	
	ArrayList<GeneralTree> subTrees = new ArrayList<GeneralTree>();	
	
	public void setRoot ( int root ) {
		this.root = root ;
	}
	
	public int getRoot() {
		return this.root ;
	}
	
	public void setSubTree ( GeneralTree sub ) {
		this.subTrees.add(sub);
	}
	
	public ArrayList<GeneralTree> getSubTree () {
		return this.subTrees ;
		
	}
	
	public void addSubTree ( ) {
		
		boolean done = false ;
		
		do {
		System.out.println("Current Node : " + this.getRoot());
		System.out.println("Enter the info for the sub tree.\nEnter '0' to exit.");
		
		Scanner in = new Scanner(System.in);
		boolean read = in.hasNextInt() ;
		if ( read ) {
			
			int subTreeInfo = in.nextInt();
			
			if ( subTreeInfo == 0 ) {
				done = true ;
				return ;
			}
			else {
				GeneralTree sub = new GeneralTree();
				sub.setRoot(subTreeInfo);				
				sub.addSubTree();
				
				this.setSubTree(sub);
				
			}
			
		}
		else {
			System.out.println("Invalid Input.\nValid Input - [ Integers only ].");
		}
		} while ( !done );
	}
	
	
	
	public void display() {
		System.out.println("Current Node - " + this.getRoot());
		
		if ( this.subTrees == null ) {
			System.out.println("The current node has no children.");
			return ;
		}
		else {
			if ( this.subTrees.size() == 0 ) {
				System.out.println("The current node (" + this.getRoot() +")  has no children.");
				return ;
			}
			
			else {
				System.out.println("Sub nodes of " + this.getRoot());
				for ( int i = 0 ; i < this.subTrees.size() ; i++ ) {
					System.out.println("->" + this.subTrees.get(i).getRoot());
				}
		
		
				for ( int i = 0 ; i < this.subTrees.size() ; i++ ) {
					this.subTrees.get(i).display();
				}
		
			}
		}
	}
	

	
}

//=======================================================================================//

class BinaryTree {
	
	private BinaryTree leftSubTree ;
	private BinaryTree rightSubTree ;
	private int info ;
	
	public void setLeftSubTree ( BinaryTree leftSubTree ) {
		this.leftSubTree = leftSubTree ;		
	}
	
	public void setRightSubTree ( BinaryTree rightSubTree ) {
		this.rightSubTree = rightSubTree ;		
	}
	
	public void setInfo ( int info ) {
		this.info = info ;
	}
	
	public BinaryTree getLeftSubTree() {
		return this.leftSubTree ;
	}
	
	public BinaryTree getRightSubTree() {
		return this.rightSubTree ;
	}
	
	public int getInfo() {
		return this.info ;
	}
	
	public void display() {
		System.out.println("Current Node : " + this.info );
		if ( this.getLeftSubTree() == null ) {
			System.out.println("Left Sub Node : null ");
		}
		else {
		System.out.println("Left Sub Node : " + this.getLeftSubTree().getInfo() );
		}
		if ( this.getRightSubTree() == null ) {
			System.out.println("Right Sub Node : null ");
		}
		else {
		System.out.println("Right Sub Node : " + this.getRightSubTree().getInfo() );
		}
		if ( this.getLeftSubTree() != null ) {
			this.getLeftSubTree().display();
		}
		
		if ( this.getRightSubTree() != null ) {
			this.getRightSubTree().display();
		}
		
	}
	
}