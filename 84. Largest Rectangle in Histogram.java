
/*
http://blog.csdn.net/abcbc/article/details/8943485
http://www.cnblogs.com/higerzhang/p/4107268.html

首先，最容易想到的是暴力法：
    将直方图的数组记做a[0…size-1]；
    最原始的方法是由a[i]为高度，向两边找比a[i]高度高的矩形。
    优化：向两边找-->向一边找，如下：
    计算以方柱a[i]为右边界的直方图中，遍历a[0…i]，依次计算可能的高度和面积，取最大者；
    i从0遍历到size-1；
    时间复杂度为O(N2)。

第一次优化：
    显然，若a[i+1]≥a[i]，则以a[i]为右边界的矩形Rect(width,height)，
    总可以添加a[i+1]带来的矩形Rect(1,height)，使得面积增大
    只有当a[i+1]＜a[i]时，才计算a[i]为右边界的矩形面积。

    优化后的算法思路：
    从前向后遍历a[0…size](末尾添加了0)，若a[i]>a[i-1]，则将a[i]放入缓冲区；
    若a[i]≤a[i-1]，则计算缓冲区中能够得到的最大矩形面积。

    对优化后算法的分析：
    从a[i]>a[i-1]可以得出：
    缓冲区中放入的值是递增的
    每次只从缓冲区取出最后元素和a[i]比较——因此，想到用栈来存储这些元素。

第二次优化：
    以2、7、5、6、4为例：
    假设当前待分析的元素是4，由刚才的分析得知，栈内元素是2,5,6(为啥没有7呢？
    因为栈内一定是升序的，7虽然曾经入栈，但遇到后面的5之后，7就被弹出去了)，其中，6是栈顶。
    此时，栈顶元素6＞4，则6出栈
    出栈后，新的栈顶元素为5，5和4的横向距离差为1：以6为高度，1为宽度的矩形面积是6*1=6
    此时，栈顶元素5＞4，则5出栈
    出栈后，新的栈顶元素为2，2和4的横向距离差为3：以5为高度，3为宽度的矩形面积是5*3=15
    此时，栈顶元素2≤4，则将4压栈，i++，同样的方法继续考察直方图后面的值。

    第二次优化算法的说明：
    显然，为了能够方便的计算“横向距离”，压入栈的是方柱的索引，而非方柱的高度本身。
    这种trick在实践中经常使用。
    同时，因为每个方柱最多只计算一次——只有压栈的方柱才会计算面积，并且每次计算一次即可完成。所以，它的时间复杂度是O(N) 。
*/


public class Solution {
    public int largestRectangleArea(int[] heights){
    
        if ( heights == null || heights.length == 0 )
            return 0;
        
        Deque<Integer> stack = new LinkedList<Integer>();   //store index
        int max = 0;
        
        for ( int i = 0; i <= heights.length; i++ ){
            //如果i到array的外面了，curVal＝0，否则就为当前index装的高度
            int curVal = (i == heights.length) ? 0 : heights[i];   
            
            //curVal 小于heights[stack.peekLast()]，说明cur是最顶端一点的右边界
            //如果 curVal 大于stack.peekLast()，说明这是cur的一个左边界                                                   //stack里每个元素都是后一个元素的左边界，停止add的时候说明碰到了右边界 
            while ( !stack.isEmpty() && heights[stack.peekLast()] >= curVal ){      
                               
                //pollLast get height(index) AND REMOVE index from stack   
                int height = heights[stack.pollLast()];
                
                //注意 why +1: since pollLast() removed
                int leftBound = stack.isEmpty() ? 0 : (stack.peekLast() + 1);       
                int rightBound = i;
                max = Math.max( max, height * (rightBound - leftBound) );  //用公式计算面积
            }       
            
            //stack=[1,4]时，说明 1处比4处值小，但是2处被弹出去了，说明4处曾经是2处的右边界，4处相当于凹心
            stack.addLast(i);                           
            
        }
        
        
        return max;
    }
}


/*
public class Solution {
    public int largestRectangleArea(int[] height) {
        if (height==null) return 0;//Should throw exception
        if (height.length==0) return 0;

        Stack<Integer> index= new Stack<Integer>();
        index.push(-1);
        int max=0;

        for  (int i=0;i<height.length;i++){
                //Start calculate the max value
            while (index.peek()>-1)
                if (height[index.peek()]>height[i]){
                    int top=index.pop();                    
                    max=Math.max(max,height[top]*(i-1-index.peek()));  
                }else break;

            index.push(i);
        }
        while(index.peek()!=-1){
            int top=index.pop();
            max=Math.max(max,height[top]*(height.length-1-index.peek()));
        }        
        return max;
    }
}
*/