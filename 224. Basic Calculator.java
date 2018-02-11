/*
        1 + ( 2- ( 3-4 + ( 5+6 ) + 7 ) + 8 ) - 9 
        |   |    |       |__4 _|     |     |   |
        |   |    |_________3_________|     |   |
        |   |                              |   |
        |   |_____________2________________|   |
        |                                      |
        |__________________1___________________|
        
     上表达式有 4 层
     从头开始左累加
     
     - 遇到 数字 --- 将其累加到num上 (num 处理多位数的情况，如: 45+3*3 ),
                 等遇到下一个与数字右侧紧贴的符号 +、-、)、时，根据其前的正负进行加减，并重置num(sign存储数字前的正负)
                 遇到与数字左侧紧贴的符号 +、-、(、时， 对sign重新置零，供下一个数字使用
                   
     - 遇到 +/-     根据sign对num进行当前层累加、累减到curSum上
                   并更新num、以及sign， 供 '+／-' 后的数字使用
     
     - 遇到 '(' --- 将上一层目前得到的结果curSum、sign压栈；开始对本层进行累加累减
                                1 + | 2 - | -1 + 
                   并更新num、以及sign，供 '(' 后的数字使用
            
     - 遇到 ')' --- 首先将本层的计算做完（因为它紧贴数字右侧，因此需要根据sign、num将本层最后一步运算做完）
                    并将num置零
                    然后stack.pop()出上一层的lastSum，根据当时压入的sign，对该层的值进行加减
                    
     - 最后一个字符，如果不是')',而是数字的话，那么还需要手动检查、并加上该值（因为数字的右侧没有字符，该数字不会计入）
*/
/*
class Solution {
    public int calculate(String s) {
        
        if( s == null || s.length() == 0 ) return 0;
            
        Deque<Integer> stack = new LinkedList<>();
        
        int res = 0;      // 最终结果
        int curSum = 0;   // 当前层的在括号前的累加值
        int sign = 1;     // 表明压入栈的 一个操作数、一个操作符( 1: ‘+’    -1：‘-’)
        int num = 0;      // 处理多位构成数字的情况： 45+2
        char[] arr = s.toCharArray();
        int len = arr.length;
        
        for( int i = 0; i < len; i++ ){
            char c = arr[i];
            if( '0'<=c && c<='9' ){
                num =  num*10 + c-'0';
            }else if( c=='+' ){
                curSum += num*sign;
                num = 0;
                sign = 1;
            }else if( c == '-' ){
                curSum += num*sign;
                num = 0;
                sign = -1;
            }else if( c == '(' ){
                stack.push( curSum );
                stack.push( sign );
                curSum = 0;           // 将上一层的curSum 与sign保存起来，之后curSum重置为0，供本层使用
                sign = 1;             // 对下一层的第一个数前加 ‘ + ’
            }else if( c == ')' ){
                curSum += num*sign;
                num = 0;
                int lastSign = stack.pop();
                int lastSum = stack.pop();
                curSum = lastSum + curSum*lastSign;
            }
        }
        if(num != 0) curSum += sign * num;
        return curSum;
    }
}
*/


/*
    version 2 : recursion version
    1 - 递归函数： 计算括号之间的数值 -- calculate value between parentheses
    2 - 对原string外侧加一对括号，使用recursion function
    3 - 用一个长度仅为一的数组对象int[] p，实现recursion calls的全局变量共享
*/
class Solution {

    public int calculate(String s) {
        if (s.length() == 0) return 0;
        s = "(" + s + ")";
        int[] p = {0};
        return eval(s, p);
    }
    
    // calculate value between parentheses
    private int eval(String s, int[] p){
        int val = 0;
        int i = p[0]; 
        int oper = 1; //1:+ -1:-
        int num = 0;
        while(i < s.length()){
            char c = s.charAt(i);
            switch(c){
                            // end of number and set operator
                case '+': val = val + oper * num; num = 0; oper = 1; i++; break;
                            // end of number and set operator
                case '-': val = val + oper * num; num = 0; oper = -1; i++; break;
                            // start a new eval
                case '(': p[0] = i + 1; val = val + oper * eval(s, p); i = p[0]; break; 
                            // end current eval and return. Note that we need to deal with the last num
                case ')': p[0] = i + 1; return val + oper * num; 

                case ' ': i++; continue;

                default : num = num * 10 + c - '0'; i++;
            }
        }
        return val;
    }   
}