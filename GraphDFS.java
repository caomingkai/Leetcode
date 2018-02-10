import java.util.*;

class GraphDFS{

	/*				  A
					 / | 
					/  |
				   /   |
				  /    |
				 B --- C

			1. 前提：图是联通的； 
			   如果图不是联通的，那么还需要传入另一个参数： Node Set
			   从某点遍历完，还有剩余点时候，从Set中对剩余点继续遍历
	*/

	public static void traverseDFS(Node node, Set visited){
		
		// base case:
		if( node == null ) return;

		// general case:
		System.out.println(node.email);
		visited.add( node );

		for( Node adj: node.neighbors ){
			if( !visited.contains(adj) ){
				traverseDFS( adj, visited );
			}
		}
	}


	// Test cases
	public static void main( String[] args ){
		User aa = new User("a","a");
		User ba = new User("b","a");
		User ca = new User("c","a");
		User da = new User("d","a");
		User ee = new User("e","e");
		User ff = new User("f","f");
		User ga = new User("g","a");

		Node naa = new Node(aa,"@aa");
		Node nba = new Node(ba,"@ba");
		Node nca = new Node(ca,"@ca");
		Node nda = new Node(da,"@da");
		Node nee = new Node(ee,"@ee");
		Node nff = new Node(ff,"@ff");
		Node nga = new Node(ga,"@ga");

		naa.neighbors.add(nba);
		naa.neighbors.add(nca);

		nba.neighbors.add(naa);
		nba.neighbors.add(nda);
		nba.neighbors.add(nff);
		nba.neighbors.add(nca);

		nca.neighbors.add(naa);
		nca.neighbors.add(nba);
		nca.neighbors.add(nee);
		nca.neighbors.add(nga);

		nda.neighbors.add(nba);

		nff.neighbors.add(nba);

		nee.neighbors.add(nca);

		nga.neighbors.add(nca);

		Set<Node> visited = new HashSet<>();
		traverseDFS(naa, visited);
	}
	
}


class Node {
	User user;
	String email;
	List<Node> neighbors;
	public Node (User user, String email){
		this.user = user;
		this.email = email;
		this.neighbors = new ArrayList<>();
	}
}

class User {
	String firstname;
	String lastname;
	public User(String first, String last){
		firstname = first;
		lastname = last;
	}
}
