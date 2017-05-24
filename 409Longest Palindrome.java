/*
// Version 1: HashMap
// HashMap  : < char , frequency >
// 1: for each char, if freq >= 2, obtain the first even number <= freq ( freq/2*2 ). Add it to sum 
// 2: if sum < s.length(), add 1 to sum; else do nothing to sum.

public class Solution {
    public int longestPalindrome(String s) {
        
        int l = s.length();
        Map< Character, Integer> dict = new HashMap<>();
        
        for( int i = 0; i < l; i++){
            char c = s.charAt(i);
            dict.put( c, dict.getOrDefault(c,0)+1 );
        }
        
        int sum = 0;
        for( int f : dict.values() ){
            sum += f / 2 * 2;
        }
        
        return l > sum ? sum+1: sum;
    }
}
*/

// Version 2: Bucket sort
// Since 'a' != 'A', the bucket size has to be larger enough to cover all A(65)~Z(90) and a(97)~z(122), so 122-65+1 = 58
public class Solution {
    public int longestPalindrome(String s) {
        
        int l = s.length();
        int[] hashBucket = new int[58];
        
        for( int i = 0; i < l; i++){
            char c = s.charAt(i);
            hashBucket[ c-'A']++;
        }
        
        int sum = 0;
        for( int i = 0; i < 58; i++){
            sum += hashBucket[i] / 2 * 2;
        }
        
        return l > sum ? sum+1: sum;
    }
}

