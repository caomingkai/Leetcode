public class Solution {
    public String reverseVowels(String s) {
        
        // Set<Character> dict = new HashSet<Character>();
        // dict.add('a'); dict.add('A');
        // dict.add('e'); dict.add('E');
        // dict.add('i'); dict.add('I');
        // dict.add('o'); dict.add('O');
        // dict.add('u'); dict.add('U');
        String dict = "aeiouAEIOU";
        
        int l = s.length(); 
        char[] str = s.toCharArray();
        int left = 0;
        int right = l - 1;
        
        while( left < right ){
            boolean fl = dict.contains( str[left] +"");
            boolean fr = dict.contains( str[right] +"");
            if( fl && fr ){
                char temp = str[left];
                str[left] = str[right];
                str[right] = temp;
                left++;
                right--;
            }else if( !fl && fr ){
                left++;
            }else if( fl &&!fr ){
                right--;
            }else{
                left++;
                right--;
            }
        }
        
        return String.valueOf(str);
    }
}