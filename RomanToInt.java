/*
// 怎么算 罗马数字？？
// 关键： 某个字符唯一性的代表一个数字，问题是该数字是“加到总和里”还是“从总和里减去”
//       “加上” or “减去”，主要看当前字符与其右边字符的关系：
//       - 若 curLetter < rightLetter, 则 应减去该字符所代表的数字
//       - 若 curLetter > rightLetter, 则 应加上该字符所代表的数字

public class RomanToInt {
    public int romanToInt(String s) {
        int[] d = new int[26];
        
        XLVII
        
        d['I' - 'A'] = 1;
        d['V' - 'A'] = 5;
        d['X' - 'A'] = 10;
        d['L' - 'A'] = 50;
        d['C' - 'A'] = 100;
        d['D' - 'A'] = 500;
        d['M' - 'A'] = 1000;
        
        int sum = 0;
        int i = 0;
        int l = s.length();
        while( i < l-1 ){
            int one = d[ s.charAt(i) - 'A'];
            int two = d[ s.charAt(i+1) - 'A'];
            sum += one < two ? -one : one;
            i++;
        }
        sum += d[ s.charAt(i) - 'A'];
        return sum;
    }
}