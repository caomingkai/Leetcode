/*
class Solution {
    int res = 0;
    public int pathSum(int[] nums) {
        
        if( nums==null || nums.length==0 )
            return res;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for( int i=0; i<nums.length; i++ )
            map.put( nums[i]/10, i ); // key:value  =  tree pos : array idx
        
        backtrack( nums, nums[0]/10, 0, map ); 
        return res;
    }
    
    
    private void  backtrack( int[] nums, int treePos, int curSum, HashMap<Integer, Integer> map ){
        
        int lvl = treePos/10;
        int pos = treePos-lvl*10;
        int nxtLvl = lvl+1;
        int nxtLeftPos = pos*2-1;
        int nxtRightPos = pos*2;
        int leftChild = nxtLvl*10+nxtLeftPos;
        int rightChild = nxtLvl*10+nxtRightPos;
        
        curSum += nums[map.get(treePos)]%10;
        
        if( !map.containsKey(leftChild) && !map.containsKey(rightChild) )
            res += curSum;
        
        if(  map.containsKey(leftChild) ) backtrack(nums, leftChild, curSum, map);
        if(  map.containsKey(rightChild) ) backtrack(nums, rightChild, curSum, map);

    } 
}

*/

class Solution {
    int ans = 0;
    Map<Integer, Integer> values;
    public int pathSum(int[] nums) {
        values = new HashMap();
        for (int num: nums)
            values.put(num / 10, num % 10);

        dfs(nums[0] / 10, 0);
        return ans;
    }

    public void dfs(int node, int sum) {
        if (!values.containsKey(node)) return;
        sum += values.get(node);

        int depth = node / 10, pos = node % 10;
        int left = (depth + 1) * 10 + 2 * pos - 1;
        int right = left + 1;

        if (!values.containsKey(left) && !values.containsKey(right)) {
            ans += sum;
        } else {
            dfs(left, sum);
            dfs(right, sum);
        }
    }
}