class Solution {
    public int[] constructArray(int n, int k) {
        
        int[] res = new int[n];
        // loop n times with condition: k distinct abs diff
        int dec = n - 1;
        res[0] = n;
        HashSet<Integer> set = new HashSet<>();
        set.add(res[0]);
        for( int i = 1; i < n; i++ ){
            if( i < k ){
                if( res[i-1] + dec > 0 && res[i-1] + dec <= n && !set.contains(res[i-1] + dec) ){
                    res[i] = res[i-1] + dec;
                }else{
                    res[i] = res[i-1] - dec;
                }
                dec--;
                set.add(res[i]);
            }else{
                dec = 1;
                if( res[i-1] - dec > 0 && res[i-1] - dec <= n && !set.contains(res[i-1] - dec) ){
                    res[i] = res[i-1] - dec;
                }else{
                    res[i] = res[i-1] + dec;
                }
                set.add(res[i]);
            }
            
        }
       
        
        return res;
    }
}


/*

// version 2:
class Solution {
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int c = 0;
        for (int v = 1; v < n-k; v++) {
            ans[c++] = v;
        }
        
        for (int i = 0; i <= k; i++) {
            ans[c++] = (i%2 == 0) ? (n-k + i/2) : (n - i/2);
        }
        return ans;
    }
}


*/


