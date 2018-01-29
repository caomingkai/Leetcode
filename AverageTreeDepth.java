import java.io.*;
import java.util.*;

class Node{
	public int val;
	public Node left;
	public Node right;

	public Node( int i ){
		val = i;
		left = null;
		right = null;
	}
}

class AverageTreeDepth{
  public static void main(String[] args){
  	Node n1 = new Node(1);
  	Node n2 = new Node(2);
  	Node n3 = new Node(3);
  	Node n4 = new Node(4);
  	Node n5 = new Node(5);
  	Node n6 = new Node(6);

  	n1.left = n2;
  	n1.right = n3;
  	n2.left = n4;
  	n2.right = n5;
  	n5.left = n6;

  	System.out.println( averageTreeDepth(n1) );

  }

  /*
  // iterative version
  private static int averageTreeDepth( Node node ){
  	if( node == null )
  		return 0;

  	int sum = 0;
  	int num = 0;
  	int curLvl = 0;
  	Queue<Node> q = new LinkedList<>();
  	q.offer(node);
  	while( !q.isEmpty() ){
  		int n = q.size();
  		num += n;
  		sum += curLvl*n;
  		System.out.println(num);
  		System.out.println(sum);
  		for( int i = 0; i < n; i++ ){
  			Node curr = q.poll();
  			if( curr.left != null )
  				q.offer(curr.left);
  			if( curr.right != null )
  				q.offer(curr.right);
  		}
  		curLvl++;
  	}
  	return sum/num;
  }
	*/
  
  // recursion version
  static int  sum = 0;
  static int  num = 0;

  private static int averageTreeDepth( Node node ){
  	recursion(0, node);
  	System.out.println(sum);
  	System.out.println(num);
  	return sum/num;
  }	

  private static void recursion( int curLvl, Node node ){
  	if( node == null )
  		return ;
  	num++;
  	sum += curLvl;
  	recursion(curLvl+1, node.left);
  	recursion(curLvl+1, node.right);
  }
  

}