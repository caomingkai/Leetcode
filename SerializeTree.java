import java.util.*;


class SerializeTree{

	public static void main( String[] args ){
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		n1.left = n2; n1.right = n3;
		n2.left = n4; n2.right = n5;
		n4.left = n6; 
		n5.left = n7; 
		SerializeAndDeserialize sdtree = new SerializeAndDeserialize();
		String res = sdtree.serializeTree(n1);
		System.out.println(res);
		Node root = sdtree.deSerializeTree(res);
		printTree(root);


	}

	private static void printTree( Node root ){
		if( root == null ) {
			System.out.print("");
			return;
		}

		Queue<Node> q = new LinkedList<>();
		q.offer(root);

		while( !q.isEmpty() ){
			Node cur = q.poll();
			if( cur != null ){
				System.out.print(cur.val + ",");
				q.offer(cur.left);
				q.offer(cur.right);
			}else{
				System.out.print("!" + ",");
			}
		}
		System.out.println();

	}
}

/*

“按层遍历”完后，就具有了下边这种特性：
left =  2*idx+1
right = 2*idx+2



Serialization：
	1. 对于非null节点，”完整“记录它的左右孩子情况
	2. 对于null节点，不需要记录
	3. 对于最后的空节点，删除至倒数第一个非空元素

			1
		   / \
		  2  3
		 / \  
		4  5  
	   /   /  
	  6   7

“按层遍历”完后，就具有了下边这种特性：
  left =  2*idx+1
  right = 2*idx+2

idx ->   0 1 2 3 4 5 6 7 8 9 10
str ->   1 2 3 4 5 ! ! 6 ! 7 ! ! ! ! !
final->  1 2 3 4 5 ! ! 6 ! 7




De-Serialization：
	1. 对于非null节点，压入queue，以备添加左右孩子情况
	2. 对于null节点，不需要压queue

*/

class SerializeAndDeserialize{
	public String serializeTree( Node root ){

		String res = "";
		if( root == null ) return res;
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while( !q.isEmpty() ){
				Node cur = q.poll();
				if( cur != null ){
					res += cur.val + ",";
					q.offer(cur.left);
					q.offer(cur.right);
				}else{
					res += "!" + ",";
				}
		}

		// 减少最后一行的null存储，剩下一半空间
		int i = res.length()-2;
		while( res.charAt(i) == '!' )
			i -= 2;

		return res.substring(0,i+1);
	}


	public Node deSerializeTree( String s ){
		if( s==null || s.length() == 0 )
			return null;

		int firstVal = Integer.valueOf(s.charAt(0)-'0');
		Node root = new Node(firstVal);
		Queue<Node> q = new LinkedList<>();
		q.offer(root);

		int i = 2;
		while( i < s.length() || !q.isEmpty() ){
			Node cur = q.poll();
			char leftVal = s.charAt(i);
			if( leftVal != '!'){
				cur.left = new Node(leftVal-'0');
				q.offer(cur.left);
			}
			else
				cur.left = null;

			// 因为省去了最后所有的null，这里需要检查一下
			if( i+2>=s.length() ) break;

			char rightVal = s.charAt(i+2);
			if( rightVal != '!'){
				cur.right = new Node(rightVal-'0');
				q.offer(cur.right);
			}
			else
				cur.right = null;
			i += 4;
		}

		return root;
	}
}

class Node{
	int val;
	Node left;
	Node right;

	Node( int v ){
		val = v;
		left = null;
		right = null;
	}

}