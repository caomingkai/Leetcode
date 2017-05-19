// Version1: Tow pointer
// Tow pointer: left right point as the ruler to get distance k
// time: O(nlogn)
// space: O(1)

public class Solution {
    
    public int findPairs(int[] nums, int k) {
        if(nums==null || nums.length==0 || k<0) return 0;
        
        int result = 0, n = nums.length;
        
        Arrays.sort(nums);
        
        int left = 0, right = 1;
        while(right<n){
            if(nums[right]-nums[left]==k){
                result++;
                left++;
                right++;
                // skip duplicates
                while(right<n && nums[right]==nums[right-1]) right++;
            }
            else if(nums[right]-nums[left]>k) left++;
            else right++;
            // keep left<right
            right = Math.max(left+1, right);
        }
        return result;
    }
}

/*
// Version2: HashMap + HashSet
// Map: record duplicate frequency, response to 'k=0' case
// Set: find out target value,  response to 'k!=0' case
// time: O(2n)
// space: O(2n)
public class Solution {
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return 0;
        }
        Map<Integer, Integer> all = new HashMap<Integer, Integer>();
        for (int i : nums) {
            if (all.containsKey(i)) {
                all.put(i, all.get(i) + 1);
            } else {
                all.put(i, 1);
            }
        }
        int count = 0;
        Set<Integer> keys = all.keySet();
        for (int i : keys) {
            if (k == 0) {
                if (all.get(i) > 1) {
                    count++;
                }
            } else {
                if (keys.contains(i + k)) {
                    count++;
                }
            }
        }
        return count;
    }
}
*/


/*
// Version3: HashSet in the fly
// Set: find out target item "in the fly"
// time: O(n)
// space: O(2n)
public class Solution {
    public int findPairs(int[] nums, int k) {
        if (k < 0) { return 0;}
        Set<Integer> starter = new HashSet<Integer>();
        Set<Integer> unique = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            // @@1 find target item first, then add the current item. this way can 
            if (unique.contains(nums[i]- k)) {
                starter.add(nums[i] - k); //
            }
            if (unique.contains(nums[i] + k)) {
                starter.add(nums[i]);     //@@2 why not "nums[i] + k"? Only add first item of the pair, avoiding dupicate count
            }
            // find target item first, then add the current item
             unique.add(nums[i]);
        }
        return starter.size();
        
    }
}

*/


/*
// Version4: brutal force
// sort first  O(nlogn)
// find target O(n*n)
public class Solution {
    public int findPairs(int[] nums, int k) {
        
        int len = nums.length;
        
        if( len == 0 || k < 0) return 0;
        
        Arrays.sort(nums);
        int cnt = 0;
        
        int prev = nums[0] - 1;// nums[0] - 1: to make the 0-index eligible to be considered(because nums[0]!= prev)

        for( int i = 0; i < len - 1; i++){
            int temp = nums[i];
            if( temp != prev ){
                
                int j = i + 1;
                while( j < len && nums[j] - temp != k ){
                    j++;
                }

                if( j != len ){
                    cnt++;
                }
                
                prev = temp;
            }
        }
        
        return cnt;
    }
}
*/