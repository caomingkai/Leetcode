/*
// version 1: HashMap - in fact, no need for a Map<>
// Because, this is a special mapping-relation, like '[' and ']', not random mapping.
public class Solution {
    public boolean isValid(String s) {
        
        Map<Character, Character> dic = new HashMap<>();
        dic.put( ')', '(');
        dic.put( ']', '[');
        dic.put( '}', '{');
        Stack<Character> stk = new Stack<>();
        
        int l = s.length();
        for( int i = 0; i < l; i++ ){
            char c = s.charAt(i);
            if( c == '(' || c == '[' || c == '{' ){
                stk.push(c);
            }else{
                if( stk.empty() ) return false;
                if( dic.get(c) != stk.pop()  ) return false;
            }
        }
        return stk.empty();
    }
}
*/

// version 2: no need for a Map
// Stack.push() --- push its counterpart,  next time encouter its counterpart, see if they are the same
public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else{
                if (stack.isEmpty() )
                    return false;
                char buddy = stack.pop();
                if (buddy != c) 
                    return false;
            } 
        }
        return stack.isEmpty();
    }
}