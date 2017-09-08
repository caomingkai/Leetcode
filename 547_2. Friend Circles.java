// version 0: union-find
// 1. rank: 'weighted quick union' --->used to determine which one to be the root
// 2. find: path compression ---------> make p's parent as its grandparent
// 3. union: 'quick union'   ---------> based on tree, not just array
public class Solution {
    class UnionFind {
        private int count = 0;
        private int[] parent, rank;
        
        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int p) {
        	while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }
        
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            if (rank[rootQ] > rank[rootP]) {
                parent[rootP] = rootQ;
            }
            else {
                parent[rootQ] = rootP;
                if (rank[rootP] == rank[rootQ]) {
                    rank[rootP]++;
                }
            }
            count--;
        }
        
        public int count() {
            return count;
        }
    }
    
    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) uf.union(i, j);
            }
        }
        return uf.count();
    }
}

/*
// version 1: dfs + decrease and conquer
// put all people into a set
// pick out a reprsentitive of a certain friend circle, recursively find his directed/indirected friends, delete from set; until he there is no friend belongs to this circle
// pick out next representitive, do the same thing, until set become empty.
class Solution {
    public int findCircleNum(int[][] M) {
        
        int res = 0;
        int n = M.length;
        
        // a set of individuals
        HashSet<Integer> s = new HashSet<>();
        for( int i = 0; i < n; i++)
            s.add(i);

        
        Iterator<Integer> iter = s.iterator();
        while( iter.hasNext() ){
            int index = iter.next(); // find a representitive of a certain friend circle
            iter.remove();           // remove this person from set
            shrinkSet(index, M, s);
            
            res++;
            iter = s.iterator();   // Note: since s has been changed, have to update Iterator!!!
        }
        return res;
    }
    
    // find directed and indirected friends of 'index', delete them from set:s ( equals: adding them to current friend circle )
    private void shrinkSet( int index, int[][] M, HashSet<Integer> s ){
        for( int i = 0; i < M.length; i++ ){
            if( M[index][i] == 1  && s.contains(i) ){
                s.remove(i);
                shrinkSet( i, M, s);
            }
        }
    }
    
}
*/

/*
// version 2: use an array maintaining the status of people, instead of Set
public class Solution {
    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }
}
*/