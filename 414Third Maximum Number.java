public class Solution {
    public int thirdMax(int[] nums) {
        
        int m1 = Integer.MIN_VALUE,  m2 = Integer.MIN_VALUE,  m3 = Integer.MIN_VALUE;
        
        int l = nums.length;
        boolean f = false;
        
        for( int i = 0; i < l; i++){
            int c = nums[i];
            if( c > m1 ){
                m3 = m2;
                m2 = m1;
                m1 = c;
            }else if( m1 > c && c > m2 ){
                m3 = m2;
                m2 = c;
            }else if( m2 > c && c > m3 ){
                m3 = c;
            }else if( c ==  Integer.MIN_VALUE ){
                f = true;
            }
        }

        if( m2 == m3 ) return m1;
        if( m2 != m3 && m3 == Integer.MIN_VALUE && f == false ) return m1;
        
        return m3;
    }
}

/*
// TreeSet  

public class Solution {
    public int thirdMax(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int n : nums) {
            set.add(n);
            if (set.size() > 3) set.pollFirst(); // Retrieves and removes the first (lowest) element
        }
        return set.size() == 3 ? set.first() : set.last();
    }
}

*/