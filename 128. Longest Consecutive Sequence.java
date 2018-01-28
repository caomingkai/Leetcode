

class Solution {
    public int longestConsecutive(int[] nums) {
        // edge case
        if( nums == null || nums.length == 0 ) return 0;
        
        HashSet<Integer> set = new HashSet<>();
        for( int i : nums ) set.add(i);
        int res = 0;
        
        for( Integer item : set ){
            if( set.contains(item-1) )  //只要有比该元素还小的元素，我们就跳过该元素，直到遇到
                continue;               //直到这个consecutive list的首元素，再统计长度；以防止重复统计。
            else{
                int sum = 1;            //没有我该元素小的，表明这是首元素，统计长度
                int next = item+1;
                while( set.contains(next) ){
                    sum++;
                    next++;
                }
                res = Math.max( res, sum );
            }
        }
        
        return res;
    }
}

/*
// version 1: union-find


class Solution {
    public int longestConsecutive(int[] nums) {
        // edge case
        if( nums == null || nums.length == 0 ) return 0;
        
        // general case
        int len = nums.length;
        UF unionFind = new UF( len );
        
        int res = 1;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for( int i = 0; i < len; i++ ) {
            if( !hm.containsKey ( nums[i] ) ){
                hm.put( nums[i], i );
            }
        }
        
        
        for( Integer item : hm.keySet() ){
            if( hm.containsKey( item-1 ) )
                unionFind.union( hm.get(item), hm.get(item-1) );
            
            if( hm.containsKey( item+1 ) )
                unionFind.union( hm.get(item), hm.get(item+1) );
        }
        
        return unionFind.maxUnionNum();
    }
    
    
    class UF{
        private int[] parent;
        private int[] weight;
        private int n;
        
        public UF( int num ){
            n = num;
            weight = new int[n];
            parent = new int[n];
            for( int i = 0; i < n; i++ )
                parent[i] = i;
        }
        
        public int find( int i ){
            while( i != parent[i] ){
                parent[i] = parent[ parent[i] ];
                i = parent[i];
            }
            return i;
        }
        
        public void union( int i, int j ){
            int iRoot = find(i);
            int jRoot = find(j);
            if( iRoot == jRoot ) return;
            
            int iWt = weight[i];
            int jWt = weight[j];
            if( iWt < jWt ){
                parent[iRoot] = jRoot;
                weight[jRoot] += weight[iRoot];
            }else{
                parent[jRoot] = iRoot;
                weight[iRoot] += weight[jRoot];
            }
        }
        
        public int maxUnionNum(){
            int res = 1;
            int[] cnt = new int[n];
            for( int i = 0; i < n; i++ ){
                int root = find(i) ;
                cnt[root]++;
                res = Math.max( res, cnt[root] );
            }
            return res;
        }
        
    }
}

*/

/*
// version 2: start counting from smallest item of each sequences, with help of Set ( ignoring current item if exist item-1 )
class Solution {
    public int longestConsecutive(int[] nums) {
        
        // edge case
        if( nums == null || nums.length == 0 ) return 0;
        
        // general case
        int res = 1;
        HashSet<Integer> s = new HashSet<>();
        for( int i : nums ) 
            s.add(i);
        
        for( int item: nums ){
            if( s.contains( item-1 ) ){ 
                // no need to start from item, start counting from item-1 when we meet it as loop proceed
                // this step will ensure that we only start counting from the smallest number for sequences
                continue;
            }else{
                int cnt = 1;
                while( s.contains(item+1) ){
                    item++;
                    cnt++; 
                }
                res = Math.max(res, cnt);
            }
        }
        
        return res;
    }
}
*/