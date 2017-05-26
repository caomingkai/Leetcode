
// version 1:   
// Queue(Linked List) substitudes For-Loop
// 1. for-loop: for{ for{ for{ x[i][j][k] } } } --- loop from right to left  adg,adh,adi,aeg,aeh,aei...
// 2.  queue  : loop from left to right ---  a,b,c => ad,ae,af,bd,be,bf,cd,ce,cf...
import java.util.LinkedList;

public class Solution {
    public List<String> letterCombinations(String digits) {
        String[][] dict = {{"a","b","c"},{"d","e","f"},{"g","h","i"},{"j","k","l"},
                            {"m","n","o"},{"p","q","r","s" },{"t","u","v"},{"w","x","y","z"}};
        LinkedList<String> res = new LinkedList<>();
        if(digits == null || digits.length() == 0 ) return res;
        
        
        res.add("");  // initiate for following handle
        int l = digits.length();
        for( int i = 0; i < l; i++){   // handle each digit position, from left to right
            int index = digits.charAt(i)-'0';
            if( index == 0 || index == 1 ){
                return new LinkedList<String>();
            }else{
                String[] numStr = dict[index-2]; // obtain the string array for the corresponding digit number
                // KEY POINT: check length of head item, use length to indicate whether or not removing and reshaping the head
                // if length is equal to digit index, implying need remove it, concat it with next letter, and append it at end
                String h = res.peek();   
                while( h.length() == i ){
                    String head = res.remove();
                    for( String letter : numStr ){
                        res.add( head + letter );
                    }
                    h = res.peek();
                }
            }
        }
        
        return res;
    }
}


/*

// version 1: Others Queue Version 
public class Solution {
    public List<String> letterCombinations(String digits) {
    LinkedList<String> ans = new LinkedList<String>();
    String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    ans.add("");
    for(int i =0; i<digits.length();i++){
        int x = Character.getNumericValue(digits.charAt(i));
        while(ans.peek().length()==i){
            String t = ans.remove();
            for(char s : mapping[x].toCharArray())
                ans.add(t+s);
        }
    }
    return ans;
    }
}
*/

/*
// Version 2: ( basically same idea with version 1:  loop from left to right)
//  use two Linked list to store current result, and next handle result
// list: temperory list to record last concat result
// result: concat each item in list with character from next digit string
public class Solution {
    public List<String> letterCombinations(String digits) {
        Map<Character,List<String>> map = new HashMap<Character,List<String>>();
        map.put('2', Arrays.asList(new String[]{"a","b","c"}));
        map.put('3', Arrays.asList(new String[]{"d","e","f"}));
        map.put('4', Arrays.asList(new String[]{"g","h","i"}));
        map.put('5', Arrays.asList(new String[]{"j","k","l"}));
        map.put('6', Arrays.asList(new String[]{"m","n","o"}));
        map.put('7', Arrays.asList(new String[]{"p","q","r","s"}));
        map.put('8', Arrays.asList(new String[]{"t","u","v"}));
        map.put('9', Arrays.asList(new String[]{"w","x","y","z"}));
        map.put('0', Arrays.asList(new String[]{" "}));
        List<String> list = new ArrayList<String>();
        List<String> result = new ArrayList<String>();
        for(int i=0; i < digits.length(); i++) {
            List<String> cList = map.get(digits.charAt(i));
            if(list.isEmpty()) {
                for(String c : cList) {
                    list.add(c);
                    result.add(c);
                }
            } else {
                result.clear();
                for(String c : cList) {
                    Iterator<String> iter = list.iterator();
                    while(iter.hasNext()) {
                        String s = iter.next();
                        String r = s  + c;
                        result.add(r);
                    }
                }
                list.clear();
                list.addAll(result);
            }
        }
        return result;
    }
   
}

*/
