package trees.SwapNodesAlgo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;

class Tree {
	int data;
	Tree leftSubTree;
	Tree rightSubTree;
}



public class Solution {
	
	public static String traverseInInorder(Tree n, String str){
	    if(n == null){
	        return str;
	    }
	    else{
	        
	        str = traverseInInorder(n.leftSubTree,str);
	        str = str + " " + n.data;
	        str = traverseInInorder(n.rightSubTree,str);
	        return str;
	    }
	}
	
	public static Tree makeTree(Scanner in){
		
		
		int numOfNodes = Integer.parseInt(in.nextLine());
		
		Queue<Tree> q = new LinkedList<Tree>();
		
		Tree head = new Tree();
		head.data = 1;
		head.leftSubTree = null;
		head.rightSubTree = null;
		
		q.add(head);
		
		String[] tempArr = new String[2];
		Tree tempTree = null;
		
		for(int i = 0; i < numOfNodes; i++){
			
			tempTree = q.remove();
			
			tempArr = in.nextLine().split(" ");
			
			// Make left sub tree
			Tree lst = new Tree();
			if(tempArr[0].equals("-1")){
				lst = null;
			}
			else{
				lst.data = Integer.parseInt(tempArr[0]);
				lst.leftSubTree = null;
				lst.rightSubTree = null;
			}
		
			// Make right sub tree
			Tree rst = new Tree();
			if(tempArr[1].equals("-1")){
				rst = null;
			}
			else{
				rst.data = Integer.parseInt(tempArr[1]);
				rst.leftSubTree = null;
				rst.rightSubTree = null;
			}
			
			tempTree.leftSubTree = lst;
			tempTree.rightSubTree = rst;

			if(lst != null){
				q.add(lst);
			}
			if(rst != null){
				q.add(rst);
			}
			
		}
		
		return head;
	}
	
	public static int height(Tree root){
	    if(root == null){
	        return 0;
	    }
	    else{
	        return 1 + Math.max(height(root.leftSubTree),height(root.rightSubTree));
	    }
	         
	 }
	
	public static Tree goToDepthDAndSwap(Tree head, int depth){
		
		Queue<Tree> q = new LinkedList<Tree>();
		q.add(head);
		
		HashMap<Tree,Integer> hMap = new HashMap<Tree,Integer>();
		hMap.put(head, 1);
		
		while(! q.isEmpty()){
			Tree t = q.remove();
			int val = hMap.get(t);
			
			if(t.leftSubTree != null){
				q.add(t.leftSubTree);
				hMap.put(t.leftSubTree, val+1);
			}
			if(t.rightSubTree != null){
				q.add(t.rightSubTree);
				hMap.put(t.rightSubTree, val+1);
			}
			
		}
		
        for (Entry<Tree, Integer> entry : hMap.entrySet()) {
            Tree key = entry.getKey();
            int de = entry.getValue();
            
            // System.out.println("#"+key.data);
            if(de % depth == 0){
            	Tree temp = key.leftSubTree;
            	key.leftSubTree = key.rightSubTree;
            	key.rightSubTree = temp;
            }
        }
        
        return head;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		Tree tree = makeTree(in);
		int h = height(tree);
		
		int t = Integer.parseInt(in.nextLine());
		
		for(int i = 0 ; i < t; i++){
			int k = Integer.parseInt(in.nextLine());
			
			System.out.println(traverseInInorder(goToDepthDAndSwap(tree,k),"").trim());
		}
		

	}

}