import java.util.*;

class LCAwithParent {
    public static void main(String[] args ) {

        Node singleNode = new Node(0);

    	Node root = new Node(1);
    	Node n2 = new Node(2);
    	Node n3 = new Node(3);
    	Node n4 = new Node(4);
    	Node n5 = new Node(5);
    	Node n6 = new Node(6);
    	Node n7 = new Node(7);
    	root.left = n2;
        n2.parent = root;

        root.right = n3;
        n3.parent = root;

    	n2.left = n4;
        n4.parent = n2;

        n2.right = n5;
        n5.parent = n2;

        n3.left = n6;
        n6.parent = n3;

        n3.right = n7;
        n7.parent = n3;

        Node left = n5;
        Node right = n3;
    	LCA lca = new LCA();
    	Node ancestor = lca.findLCA(left, right);
    	
        if( ancestor != null )
            System.out.println(ancestor.val);
        else
            System.out.println("null");

    }
}
	
    
class LCA{
    public Node findLCA( Node left, Node right ) {
        int leftL = height(left);
        int leftR = height(right);
        System.out.println(leftL);
        System.out.println(leftR);

        if( leftL > leftR ) 
            return findLCA( right, left );

        int diff = leftR - leftL;
        
        while( diff > 0 ){
            right = right.parent;
            diff--;
        }

        while( left != null && right != null ){
            if( left == right )
                return left;
            left = left.parent;
            right = right.parent;
        }
        return null;
    }

    private int height( Node n ){
        int h = 0;
        while( n != null ){
            h++;
            n = n.parent;
        }
        return h;
    }
}


class Node{
	int val;
	Node left;
    Node right;
    Node parent;

	Node( int v ){
		val = v;
        left = null;
        right = null;
		parent = null;
	}
}

