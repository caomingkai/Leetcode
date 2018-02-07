/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */


/*
    1. 为什么用到了HashMap？？
        - 用HashMap将clone出来的node的reference保存起来，以便后续使用
        - 因为graph可能成环，所以DFS／BFS访问时候要设置visited，防止死循环
        - 又因为存在几个点共享某个孩子的情况，然而孩子只能在第一次traverse到的时候创建，后续就不可能访问到了
        - 所以在其他节点需要纳入该孩子的时候，直接从hashmap中取reference就好
        
    2. 思路： 
        - traverse 原图中的每个节点
        - 访问该节点的时候，创建新节点，同时纳入节点的孩子
        - 纳入节点孩子有两种方式：1. 孩子已经创建好了，直接纳入ref 2.孩子还未访问到，clone孩子，再纳入
*/
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if( node == null ) return null;
        HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
        return clone(node,map);
    }
    
    private UndirectedGraphNode clone( UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> map){
        if( !map.containsKey(node.label) ){
            UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
            map.put(node.label, newNode);
            for( UndirectedGraphNode childNode : node.neighbors ){
                if( map.containsKey(childNode.label) )
                    newNode.neighbors.add( map.get(childNode.label) );
                else
                    newNode.neighbors.add( clone(childNode, map) );
            }   
            return newNode;
        }
        return map.get(node.label);   
    }
}