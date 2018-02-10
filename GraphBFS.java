import java.util.*;

class GraphBFS{

	/*				  A
					 / | 
					/  |
				   /   |
				  /    |
				 B --- C

		Traverse by-level  ： 这里的Set 存的是 to-be-visited, 而非 visited ！！
			1. 此处Set的作用是防止重复遍历，与”树的遍历“不一样
			   - 树的遍历： 在打印时，将该点设置为 visited
			   - 图的遍历： 在压入queue的时候，紧接将该点设置为：to-be-visited
			   + why ?
			   + 压入queue的点是下一层“将要”遍历的点 [ B, C ]
			   + 如果这时候不把它们设置为 to-be-visited，那么如果B、C直接也有edge的话
			   	 在访问B的时候，会重复的把 C 压入queue

			2. 前提：图是联通的； 
			   如果图不是联通的，那么还需要传入另一个参数： Node Set
			   从某点遍历完，还有剩余点时候，从Set中对剩余点继续遍历
	*/

	public static void levelBFS(Node node){
		if( node == null ) return;

		Queue<Node> q = new LinkedList<>();
		Set<Node> set = new HashSet<>();
		q.offer(node);
		set.add(node);

		while( !q.isEmpty() ){					 // 检查当前层的节点，是否满足条件
			int size = q.size();
			for( int i = 0; i < size; i++ ){  	 // 访问当前level的所有Nodes
				Node temp = q.poll();
				System.out.println(temp.email);	 // 访问该节点
				for( Node adj: temp.neighbors ){
					if( !set.contains(adj) ){
						q.offer(adj);			// Queue中的Nodes，是下一层要访问的节点
						set.add(adj);    		// 防止同一level的节点有连接时，重复压入队列
					}
				}
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
		nba.neighbors.add(nca);
		nba.neighbors.add(nff);

		nca.neighbors.add(naa);
		nca.neighbors.add(nba);
		nca.neighbors.add(nee);
		nca.neighbors.add(nga);

		nda.neighbors.add(nba);

		nff.neighbors.add(nba);

		nee.neighbors.add(nca);

		nga.neighbors.add(nca);


		levelBFS(naa);
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
