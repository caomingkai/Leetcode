import java.util.*;


class SerializeTreeDFS{

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

		// Node n0=new Node(0);
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

*/

class SerializeAndDeserialize{
	String prefix = "";
	public String serializeTree( Node root ){

		if( root == null ) return prefix;
		dfs( root );
		System.out.println(prefix);
		return prefix;
	}

	// 处理以node为根的子树：
	// 先append自己的值
	// 后处理孩子的值（不为null则recursion；为null则直接加“！”）
	private void dfs( Node node ){

		// 1- 如果该node左右孩子都为null，那么只append自己的值，
		// 不额外再加上两个“！”表示null值
		if( node.left == null && node.right == null ){
			prefix += node.val + ",";
			return;
		}

		// 2- 首先append自己的值
		prefix += node.val + ",";


		// 3- 然后append孩子的值（如果是null，直接append “！”）
		if( node.left != null )
			dfs( node.left );
		else
			prefix += "!" + ",";


		if( node.right != null )
			dfs( node.right );
		else
			prefix += "!" + ",";
	}


	// 1. 遇到非“！”，则表明一直是左孩子->链接左孩子&&压栈
	// 2. 遇到 “！”，表明该处理右孩子了，但不需要管“！”，因为这是null
	// 		2.1 从当前位置找后边string中第一个不为“！”的点
	//      2.2 同时，pop出相应的栈顶元素，这个元素是为非“！”node的父节点
	//      2.3 设置好右节点之后，从非“！”元素继续链接左孩子
	public Node deSerializeTree( String s ){
		if( s.length() == 0 )
			return null;

		Node root = new Node( s.charAt(0)-'0' );
		Deque<Node> stack = new LinkedList<>();
		stack.push(root);
		Node cur = root;

		int i = 2;
		while( i < s.length() ){
			char val = s.charAt(i);
			if( val == '!' ){
				// 从当前pos，找string中第一个非“！”node；
				// 并跟踪该非“！”node的父节点
				while( i < s.length() && s.charAt(i) == '!' ){
					stack.pop(); // its sibling
					stack.pop(); // its parent
					cur = stack.pop(); // its grandfather
					i += 2;
				}
				cur.right = new Node(s.charAt(i)-'0');
				cur = cur.right;
				stack.push(cur);
			}else{
				cur.left = new Node(val-'0');
				cur = cur.left;
				stack.push(cur);
			}
			i += 2;
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