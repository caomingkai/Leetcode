
// version 1: two pointer
public class Solution {
    public String reverseString(String s) {
        int l = s.length();
        int left = 0;
        int right = l - 1;
        char[] res = s.toCharArray();
        
        while( left < right ){
            char temp = res[left];
            res[left] = res[right];
            res[right] = temp;
            left++;
            right--;
        }
        return String.valueOf(res);
        
    }
}


/*
// version 2: stack
public class Solution {
    public String reverseString(String s) {
        
        Stack<Character> res = new Stack<>();
        int l = s.length();
        int i = 0;
        while( i < l ){
            res.push( s.charAt(i++) );
        }
        
        StringBuilder r = new StringBuilder();
        while( !res.empty() ){
            r.append( res.pop() );
        }
        
        return r.toString();
        
    }
}