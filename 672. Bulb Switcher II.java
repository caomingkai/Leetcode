
/*  
   1.
        观察数组，我们可以发现以6个为1组，都是重复的pattern，那么实际上我们可以把重复的pattern去掉而且并不会影响结果。
        如果n大于6，我们则对其取余再加上6，新的n跟使用原来的n会得到同样的结果，但这样降低了我们的计算量。
    
    2. 分情况讨论：
        当灯泡数n>=3，操作次数>= 3时，灯泡状态至多可能为8种：
        （偶数编号灯开关和奇数编号灯开关作用等效）

        全亮
        全亮，3k + 1
        奇数亮
        奇数亮，3k + 1
        偶数亮
        偶数亮，3k + 1
        全灭
        全灭， 3k + 1

*/

class Solution {
    public int flipLights(int n, int m) {
        if (m == 0) return 1;
        if (n <= 0 || m < 0) return 0;
        
        if (n == 1) 
            return 2;
        else if (n == 2) 
            return (m == 1) ? 3 : 4;
        else 
            return (m == 1) ? 4 : ((m == 2) ? 7 : 8);
    }
}