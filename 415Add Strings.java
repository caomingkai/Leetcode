
// Version 1: my solution 99% :)
public class Solution {
    public String addStrings(String num1, String num2) {
        
        int l1 = num1.length();
        int l2 = num2.length();
        if( l1 == 0 || num1 == "0" ) return num2;
        if( l2 == 0 || num2 == "0" ) return num1;
        
        int l = l1 > l2 ? l1 : l2; // get min length
        int d = Math.abs(l1-l2);
        char[] res = new char[l];
        char[] n1 = num1.toCharArray();
        char[] n2 = num2.toCharArray();
        
        int crr = 0; // carry bit 0/1
        for(int i = l-1; i >= 0 ; i--){
            int sum = 0;
            if(i-d >= 0){
                sum = l1 > l2 ? (n1[i] + n2[i-d] - 96 + crr) : (n1[i-d] + n2[i] - 96 + crr);
                res[i] = (char)(sum%10 + 48);
                crr = sum > 9 ? 1 : 0;
            }else{
                sum = l1 > l2 ? (n1[i] - 48 + crr) : (n2[i] - 48 + crr);
                res[i] = (char)(sum%10 + 48);
                crr = sum > 9 ? 1 : 0;
            }
        }
        return crr == 1 ? "1" + new String(res) : new String(res);
    }
}

/*
// Version 2: StringBuilder()
public class Solution {
    public String addStrings(String num1, String num2) {
        int carry=0;
        StringBuilder sb=new StringBuilder();
        for(int i=num1.length()-1, j=num2.length()-1; i>=0||j>=0||carry==1; i--,j--){
            int x = i<0 ? 0:num1.charAt(i)-'0';
            int y = j<0 ? 0:num2.charAt(j)-'0';
            sb.append((x + y + carry) % 10);
            carry=(x+y+carry)/10;
        }
        return sb.reverse().toString();
    }
}
*/