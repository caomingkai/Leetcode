/*
    version 1: extend palindrome
*/
/*
public class Solution {
    int count = 0;
    
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        
        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }
        
        return count;
    }
    
    private void extendPalindrome(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++; left--; right++;
        }
    }
}

*/

/*
    version 2: DP
*/
public class Solution {
    public int countSubstrings(String s) {
        
        int sLen = s.length();
        char[] cArr = s.toCharArray();
        
        int totalPallindromes = 0;
        
        boolean[][] dp = new boolean[sLen][sLen];
        
        // Single length pallindroms
        for (int i = 0; i < sLen; i++) {
            dp[i][i] = true;
            totalPallindromes++;
        }
        
        // 2 length pallindromes
        for (int i = 0; i < sLen - 1; i++) {
            if (cArr[i] == cArr[i + 1]) {
                dp[i][i + 1] = true;
                totalPallindromes++;
            }
        }

        // Lengths > 3
        
        for (int subLen = 2; subLen < sLen; subLen++) {
            
            for (int i = 0; i < sLen - subLen; i++) {
                
                int j = i + subLen;
                
                if (dp[i + 1][j - 1] && cArr[i] == cArr[j]) {
                    dp[i][j] = true;
                    totalPallindromes++;
                }
            }
        }        
        return totalPallindromes;
        
    }
}