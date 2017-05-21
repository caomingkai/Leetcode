
// better approach by others
public class Solution {
    public int hammingDistance(int x, int y) {
        int num = x ^ y;
        int setBitCount = 0;
        while ( num > 0 ){
            setBitCount += num & 1;
            num = num >> 1;
        }
        return setBitCount;
    }
}


/*
// my approach
public class Solution {
    public int hammingDistance(int x, int y) {
        
        int res = x^y;
        System.out.println(res);
        int d = 31;
        int mask = 1<<d;
        
        int cnt = 0;
        while( d >= 0){
            if( ( mask & res ) != 0){
                cnt++;
            }
            d--;
            mask = 1<<d;
        }
        return cnt;
    }
}
*/