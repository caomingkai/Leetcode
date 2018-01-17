/*

Key Idea: 
1 - 遍历所有的 ( nums[i], nums[j] ) 组合， 用两层for循环
2 - 另外两个数则只需要从 [ j+1 ~ l-1 ] 中寻找即可，不必从起始点开始寻找


        i = 0 ~ l
            j = i+1 ~ l
                k = j+1 : 双指针
                m = l-1 : 双指针



*/



class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        
        List<List<Integer>> res = new ArrayList<>();
        
        // edge case:
        if( nums == null || nums.length < 4 )
            return res;
        
        // general case:
        Arrays.sort(nums);
        int l = nums.length;
        for( int i = 0; i < l; i++ ){
            for( int j = i+1; j < l; j++ ){
                twoSum( nums, i, j, res , target );
                j = skimDup(j, nums, true );
            }
            i = skimDup( i, nums, true );
        }
        
        return res;
    }
    
    private void twoSum( int[] nums, int i, int j, List<List<Integer>> res, int target ){
        int t = target - ( nums[i] + nums[j] );
        int l = nums.length;
        int x = j+1;
        int y = l-1;
        
        while( x < y ){
            int sum = nums[x] + nums[y];
            if( sum < t ){
                x = skimDup(x, nums, true );
                x++;
            }else if( sum > t ){
                y = skimDup(y, nums, false );
                y--;
            }else{
                res.add( new ArrayList( Arrays.asList(nums[i], nums[j], nums[x], nums[y] )));

                x = skimDup(x, nums, true );
                x++;
                
                y = skimDup(y, nums, false );
                y--;
            }
        }
    }
    
    private int skimDup( int i, int[] nums, boolean increase ){
        int l = nums.length;
        if( increase ){
            while( i < l-1 && nums[i] == nums[i+1])
                    i++;
        }else{
             while( i > 1 && nums[i] == nums[i-1])
                    i--;
        }
        return i;
    }
    
}